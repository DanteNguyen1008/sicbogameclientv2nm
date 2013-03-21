package com.example.sicbogameexample;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo.components.UserComponent;
import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends Activity implements OnClickListener {
	EditText edt_username;
	EditText edt_password;
	Button btn_sign_in;
	ImageButton btnBack;
	TextView txtCreatAccount;
	TextView txtForgotPassword;
	List<Object> dataList;
	String[] responseName = { "username", "password" };
	ConnectionAsync connectionAsync;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);

		GameEntity.getInstance().connectionHandler = new ConnectionHandler();

		edt_username = (EditText) findViewById(R.id.edt_username);
		edt_password = (EditText) findViewById(R.id.edt_password);
		btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
		btnBack = (ImageButton) findViewById(R.id.btn_back);
		btn_sign_in.setOnClickListener(this);
		txtCreatAccount = (TextView) findViewById(R.id.txt_create_account);
		txtForgotPassword = (TextView) findViewById(R.id.txt_forgot_password);
		edt_username.setOnClickListener(this);
		txtForgotPassword.setOnClickListener(this);
		txtCreatAccount.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu_option, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		case R.id.menu_help:
			Intent intent = new Intent(LoginScreen.this, WebviewHelpPage.class);
			startActivity(intent);
			return true;
		case R.id.menu_profile:
			Intent intent1 = new Intent(LoginScreen.this, ProfileActivity.class);
			startActivity(intent1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId())

		{
		case R.id.txt_create_account:
			Intent intent = new Intent(this, RegisterScreen.class);
			this.startActivity(intent);
			finish();
			break;
		case R.id.btn_sign_in:
			connectionAsync = new ConnectionAsync();
			String[] paramsName = { "username", "password" };
			String[] paramsValue = { edt_username.getText().toString().trim(),
					edt_password.getText().toString().trim() };
			Object[] params = { GameEntity.getInstance().connectionHandler,
					this, GameEntity.SIGNIN_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);
			break;
		case R.id.txt_forgot_password:

			Intent itent = new Intent(LoginScreen.this, ResetPassword.class);
			startActivity(itent);
			break;
			
		case R.id.btn_back:
			break;
		}

	}

	class ConnectionAsync extends AsyncTask<Object, String, Integer> {
		ConnectionHandler connectionHandler;
		Activity activity;

		@Override
		protected Integer doInBackground(Object... params) {
			// TODO Auto-generated method stub
			connectionHandler = (ConnectionHandler) params[0];
			activity = (Activity) params[1];
			try {
				connectionHandler.requestToServer((String) params[2],
						(String[]) params[3], (Object[]) params[4]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Integer value) {
			try {
				// dataList = connectionHandler.parseData(responseName);
				JSONObject result = connectionHandler.getResult();

				// Create user and move to game scene
				if (result.getBoolean("is_success")) {
					Intent intent = new Intent(activity,
							SicBoGameActivity.class);

					GameEntity.getInstance().userComponent = new UserComponent(
							edt_username.getText().toString(),

							(String) result.get("email"),
							result.getDouble("balance"));
					activity.startActivity(intent);
					activity.finish();
				} else {
					Toast.makeText(activity, "Login fail, please try again",
							Toast.LENGTH_LONG).show();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
