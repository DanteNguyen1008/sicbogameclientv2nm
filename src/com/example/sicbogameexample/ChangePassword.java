package com.example.sicbogameexample;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.sicbogameexample.LoginScreen.ConnectionAsync;

import sicbo.components.UserComponent;
import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity implements OnClickListener{
	
	EditText edtCurrentPassword,edtNewPassword,edtConfirmPassword;
	Button btnOk,btnCancel;
	ConnectionAsync connectionAsync;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		edtCurrentPassword=(EditText)findViewById(R.id.edt_current_password);
		edtNewPassword=(EditText)findViewById(R.id.edt_new_password);
		edtConfirmPassword=(EditText)findViewById(R.id.edt_confirm_password);
		btnOk=(Button)findViewById(R.id.btn_ok);
		btnCancel=(Button)findViewById(R.id.btn_cancel);
		btnOk.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_ok:
			if(edtConfirmPassword.getText().equals(edtNewPassword))
				Toast.makeText(this, "Please type again", Toast.LENGTH_LONG).show();
			else
			{
			connectionAsync = new ConnectionAsync();
			String[] paramsName = { "old_pass", "new_pass" };
			String[] paramsValue = { edtCurrentPassword.getText().toString().trim(),
					edtNewPassword.getText().toString().trim()};
			Object[] params = { GameEntity.connectionHandler, this,
					GameEntity.SIGNIN_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);
			}
			break;
		case R.id.btn_cancel:
			this.finish();
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
					Toast.makeText(ChangePassword.this, "Change password succesful", Toast.LENGTH_LONG).show();
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
