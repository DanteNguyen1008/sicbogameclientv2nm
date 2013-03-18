package com.example.sicbogameexample;

import java.io.IOException;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends Activity implements OnClickListener {
	EditText txtUsername;
	EditText txtPassword;
	EditText txtConfirmPassword;
	EditText txtEmail;
	Button btnSubmit;
	Button btnLogin;
	ConnectionHandler connectionHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_screen);
		connectionHandler = new ConnectionHandler();
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnLogin = (Button)findViewById(R.id.btnLogin);

		btnSubmit.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_register_screen, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.equals(btnSubmit)) {
			ConnectionAsync connectionAsync = new ConnectionAsync();
			String[] paramsName = { "username", "password", "email" };
			String[] paramsValue = { txtUsername.getText().toString().trim(),
					txtPassword.getText().toString().trim(),
					txtEmail.getText().toString().trim() };
			Object[] params = { connectionHandler, this,
					GameEntity.SIGNUP_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);
		}else if(v.equals(btnLogin))
		{
			Intent intent = new Intent(this, LoginScreen.class);
			this.startActivity(intent);
			finish();
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
					GameEntity.userComponent = new UserComponent(txtUsername.getText().toString(),
							txtEmail.getText().toString(),
							0);
					activity.startActivity(intent);
					activity.finish();
				} else {
					Toast.makeText(activity,result.getString("message"),
							Toast.LENGTH_LONG).show();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
