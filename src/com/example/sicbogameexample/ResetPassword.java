package com.example.sicbogameexample;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ResetPassword extends BaseActivity implements OnClickListener {

	EditText edtEmail;
	Button btnResetPassword;
	ConnectionAsync connectionAsync;
	AlertDialog.Builder alertDialog;

	ImageButton imgBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);

		
		edtEmail=(EditText)findViewById(R.id.edt_email_reset);
		btnResetPassword=(Button)findViewById(R.id.btn_reset);
		imgBack=(ImageButton)findViewById(R.id.btn_back);
		imgBack.setOnClickListener(this);

		btnResetPassword.setOnClickListener(this);

	}

	/*void createDialog() {
		alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("new password sent");
		alertDialog.setMessage("Please check your email");
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.addCategory(Intent.CATEGORY_HOME);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
						finish();
					}
				});

		alertDialog.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						dialog.cancel();
					}
				});
		alertDialog.show();
	}*/

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
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(activity,
								"Connection time out, data corrupted!",
								Toast.LENGTH_LONG).show();
						finish();
					}
				});
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Integer value) {
			try {

				JSONObject result = connectionHandler.getResult();
				progressDialog.dismiss();
				// Create user and move to game scene
				if (result != null) {
					if (result.getBoolean("is_success")) {

						createDialog();
						progressDialog.dismiss();

					} else {
						progressDialog.dismiss();
						Toast.makeText(activity, result.getString("message"),
								Toast.LENGTH_LONG).show();
					}
				} else
					Toast.makeText(ResetPassword.this,
							"Error network,try again", Toast.LENGTH_LONG)
							.show();
				           progressDialog.dismiss();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch(arg0.getId())
		{
		case R.id.btn_reset:
		 connectionAsync = new ConnectionAsync();
			String[] paramsName = {"email"};
			String[] paramsValue =  {edtEmail.getText().toString().trim()};
					
			Object[] params = { GameEntity.getInstance().connectionHandler, this,
					GameEntity.FORGOT_PASSWORD_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);
			break;
		case R.id.btn_back:
			this.finish();
			break;
		}
	}

	@Override
	protected void setHintEditext() {
		// TODO Auto-generated method stub
		super.setHintEditext();
		edtEmail.setText("");
	}

}
