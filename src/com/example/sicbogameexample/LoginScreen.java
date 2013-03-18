package com.example.sicbogameexample;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo.components.UserComponent;
import sicbo_networks.ConnectionHandler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends Activity implements OnClickListener {
	EditText txtUsername;
	EditText txtPassword;
	Button btnSubmit;
	Button btnRegister;
	List<Object> dataList;
	String[] responseName = { "username", "password" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		GameEntity.connectionHandler = new ConnectionHandler();
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(this);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnRegister.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login_screen, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.equals(btnSubmit)) {

			ConnectionAsync connectionAsync = new ConnectionAsync();
			String[] paramsName = { "username", "password" };
			String[] paramsValue = { txtUsername.getText().toString().trim(),
					txtPassword.getText().toString().trim()};
			Object[] params = { GameEntity.connectionHandler, this,
					GameEntity.SIGNIN_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);

		}else if(arg0.equals(btnRegister))
		{
			Intent intent = new Intent(this, RegisterScreen.class);
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
				if (result.getBoolean("signin_success")) {					
					Intent intent = new Intent(activity,
							SicBoGameActivity.class);
					GameEntity.userComponent = new UserComponent(txtUsername.getText().toString(),
							(String) result.get("email"),
							result.getDouble("current_balance"));
					activity.startActivity(intent);
					activity.finish();
				} else {
					Toast.makeText(activity,"Login fail, please try again",
							Toast.LENGTH_LONG).show();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
