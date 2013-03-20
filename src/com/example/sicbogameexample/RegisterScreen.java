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

public class RegisterScreen extends BaseActivity implements OnClickListener {
	EditText edtUsername;
	EditText edtPassword;
	EditText edtConfirmPassword;
	EditText edtEmail, edtFullName;
	Button btnRegister;
	ConnectionHandler connectionHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_screen);
		connectionHandler = new ConnectionHandler();
		edtUsername = (EditText) findViewById(R.id.edt_username);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
		edtEmail = (EditText) findViewById(R.id.edt_email);
		edtFullName = (EditText) findViewById(R.id.edt_full_name);
		btnRegister = (Button) findViewById(R.id.btn_register);
		btnRegister.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_register_screen, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.btn_register) {
			ConnectionAsync connectionAsync = new ConnectionAsync();
			String[] paramsName = { "username", "password", "email", "fullname" };
			String[] paramsValue = { edtUsername.getText().toString().trim(),
					edtPassword.getText().toString().trim(),
					edtEmail.getText().toString().trim(),
					edtFullName.getText().toString().trim() };
			Object[] params = { connectionHandler, this,
					GameEntity.SIGNUP_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);
		}
	}

	class ConnectionAsync extends AsyncTask<Object, String, Integer> {
		ConnectionHandler connectionHandler;
		Activity activity;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			createProgressDialog();

		}

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
					GameEntity.userComponent = new UserComponent(edtUsername
							.getText().toString(), edtEmail.getText()
							.toString(), 0);
					progressDialog.dismiss();
					createDialog();

				} else {
					Toast.makeText(activity, result.getString("message"),
							Toast.LENGTH_LONG).show();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
