package kibow.games.casinohills.sicbo.networks;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import kibow.games.casinohills.sicbo.screen.GameEntity;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionHandler {

	// public static final String SERVER_ROOT_URL =
	// "http://sicbogame.jelastic.servint.net/sicbogame/";
	// public static final String SERVER_ROOT_URL =
	// "http://10.0.1.15:8084/SicbokServer/";

	public static final String SERVER_ROOT_URL = "https://ch.moya.in/_kibow/dantenguyen/mobiles/";
	public static final String SERVER_API_HISTORY_URL = "https://ch.moya.in/_api/sicbo/"
			+ GameEntity.GAME_ID + "/history";
	private static final String REQUEST_KEY = "response";
	private static final String DATA_KEY = "data";
	private static final String TASKID_KEY = "task_title";
	private static final String TYPEOFREQUEST = "type_of_request";
	public static final String SSL_USERNAME = "kibow";
	public static final String SSL_PASSWORD = "xZb53mNp";

	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String taskID;

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	HttpClient httpClient;
	HttpPost httpPost;
	HttpContext httpContext;
	public boolean status;

	// the timeout until a connection is established
	private static final int CONNECTION_TIMEOUT = 15000; /* 5 seconds */

	// the timeout for waiting for data
	private static final int SOCKET_TIMEOUT = 15000; /* 5 seconds */

	// ----------- this is the one I am talking about:
	// the timeout until a ManagedClientConnection is got
	// from ClientConnectionRequest
	private static final long MCC_TIMEOUT = 15000; /* 5 seconds */

	private static void setTimeouts(HttpParams params) {
		params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				CONNECTION_TIMEOUT);
		params.setIntParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIMEOUT);
		params.setLongParameter(ConnManagerPNames.TIMEOUT, MCC_TIMEOUT);
	}

	public ConnectionHandler() {

		httpClient = getNewHttpClient();
		CookieStore cookieStore = new BasicCookieStore();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}

	public void requestToServer(String taskName, ArrayList<String> paramsName,
			ArrayList<Object> paramsValue) throws ClientProtocolException,
			IOException, JSONException, ConnectTimeoutException, Exception {
		httpPost = new HttpPost(SERVER_ROOT_URL + taskName);
		// for basic authentication
		httpPost.addHeader(BasicScheme.authenticate(
				new UsernamePasswordCredentials(SSL_USERNAME, SSL_PASSWORD),
				"UTF-8", false));
		setTimeouts(httpPost.getParams());

		List<NameValuePair> nameValuePair = null;
		if (paramsName != null && paramsValue != null) {
			nameValuePair = new ArrayList<NameValuePair>();
			for (int i = 0; i < paramsName.size(); i++) {
				nameValuePair.add(new BasicNameValuePair(paramsName.get(i),
						paramsValue.get(i) + ""));
			}
		}
		if (nameValuePair != null)
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		String responseBody = httpClient.execute(httpPost, responseHandler,
				httpContext);
		JSONObject json = new JSONObject(responseBody);
		JSONObject request = (JSONObject) json.get(REQUEST_KEY);
		setTaskID(request.getString(TASKID_KEY));
		setResult((JSONObject) request.get(DATA_KEY));
		status = request.getBoolean("status");

	}

	public List<Object> parseData(String[] paramsName) throws JSONException {
		List<Object> dataList = new ArrayList<Object>();
		for (int i = 0; i < paramsName.length; i++) {
			dataList.add(result.get(paramsName[i]));
		}
		return dataList;
	}

	class MySSLSocketFactory extends SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore)
				throws NoSuchAlgorithmException, KeyManagementException,
				KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port,
				boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}

	public DefaultHttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}
}
