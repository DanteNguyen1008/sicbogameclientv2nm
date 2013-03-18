package sicbo_networks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectionHandler {

	private static final String SERVER_URL = "http://10.0.1.15:8084/WEB-INF/Portal";
	private static final String REQUEST_KEY = "request";
	private static final String DATA_KEY = "data";
	private static final String TASKID_KEY = "taskID";
	private static final String TYPEOFREQUEST = "type_of_request";

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

	public ConnectionHandler() {
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost(SERVER_URL);
		CookieStore cookieStore = new BasicCookieStore();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}

	public void requestToServer(String taskName, String[] paramsName,
			Object[] paramsValue) throws ClientProtocolException, IOException,
			JSONException {

		if (!taskName.equals("")) {
			List<NameValuePair> nameValuePair;
			if (paramsName != null && paramsValue != null) {
				nameValuePair = new ArrayList<NameValuePair>(
						paramsName.length + 1);
				nameValuePair.add(new BasicNameValuePair(TYPEOFREQUEST,
						taskName));
				for (int i = 0; i < paramsName.length; i++) {
					nameValuePair.add(new BasicNameValuePair(paramsName[i],
							(String) paramsValue[i]));
				}
			} else {
				nameValuePair = new ArrayList<NameValuePair>(1);
				nameValuePair.add(new BasicNameValuePair(TYPEOFREQUEST,
						taskName));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
			ResponseHandler<String> responseHandler = new BasicResponseHandler();

			String responseBody = httpClient.execute(httpPost, responseHandler,
					httpContext);
			JSONObject json = new JSONObject(responseBody);
			JSONObject request = (JSONObject) json.get(REQUEST_KEY);
			setTaskID(request.getString(TASKID_KEY));
			setResult((JSONObject) request.get(DATA_KEY));
		}
	}

	public List<Object> parseData(String[] paramsName) throws JSONException {
		List<Object> dataList = new ArrayList<Object>();
		for (int i = 0; i < paramsName.length; i++) {
			dataList.add(result.get(paramsName[i]));
		}
		return dataList;
	}

}
