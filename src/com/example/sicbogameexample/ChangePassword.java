package com.example.sicbogameexample;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePassword extends BaseActivity implements OnClickListener{
	
	EditText edtCurrentPassword,edtNewPassword,edtConfirmPassword;
	Button btnOk;
	ImageButton imgBack;
	ConnectionAsync connectionAsync;
	boolean validtext=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		edtCurrentPassword=(EditText)findViewById(R.id.edt_current_password);
		edtNewPassword=(EditText)findViewById(R.id.edt_new_password);
		edtNewPassword.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus)
	            {
	            	if(edtNewPassword.getText().toString().length()<6)
	            	{
	            		Toast.makeText(getApplicationContext(), "Password must at least 6 character", Toast.LENGTH_LONG).show();
	            	    validtext=false;
	            	}
	            	else
	            	{
	            		validtext=true;
	            	}
	            	
	            }

 
	        }
	    });
		edtConfirmPassword=(EditText)findViewById(R.id.edt_confirm_password);
		btnOk=(Button)findViewById(R.id.btn_ok);
		imgBack=(ImageButton)findViewById(R.id.btn_back);
		imgBack.setOnClickListener(this);
		btnOk.setOnClickListener(this);
		
	
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_ok:
			if(edtConfirmPassword.getText().toString().equals(edtNewPassword.getText().toString())!=true)
				Toast.makeText(this, "Confirm password doesn't match", Toast.LENGTH_LONG).show();
			else
			{
			 missTyping();
			 if(validtext==true)
			 {
			connectionAsync = new ConnectionAsync();
			String[] paramsName = { "old_pass", "new_pass" };
			String[] paramsValue = { edtCurrentPassword.getText().toString().trim(),
					edtNewPassword.getText().toString().trim()};
			Object[] params = { GameEntity.getInstance().connectionHandler, this,
					GameEntity.CHANGE_PASSWORD_TASK, paramsName, paramsValue };
			connectionAsync.execute(params);
			}
			}
			
			break;
		case R.id.btn_back:
			this.finish();
		break;
		
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
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Integer value) {
			try {
				// dataList = connectionHandler.parseData(responseName);
				JSONObject result = connectionHandler.getResult();
				Log.d("result from json",result.toString());
                 
				// Create user and move to game scene
				if(result!=null)
				{
				if (result.getBoolean("is_success")) {					
					progressDialog.dismiss();
					createDialog();
					
					
				} else {
					progressDialog.dismiss();
					Toast.makeText(activity,result.getString("message"),
							Toast.LENGTH_LONG).show();
				}
				}
				else
				{
					Toast.makeText(ChangePassword.this,"Error network!Please try again", Toast.LENGTH_LONG).show();
					progressDialog.dismiss();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	void missTyping()
	{   
		
		
		if(edtCurrentPassword.length()==0
				||edtConfirmPassword.length()==0
				||edtNewPassword.length()==0)
		{
			validtext= false;
		    Toast.makeText(getApplicationContext(), "Miss Typing", Toast.LENGTH_LONG).show();
		
		}
		else
			validtext=true;
		
	
		
	}
	void checkPasswordLenght()
	{
		int lenght=edtNewPassword.getText().toString().length();
		if(lenght<6)
		{
			
		}
	}
	@Override
	protected void setHintEditext() {
		// TODO Auto-generated method stub
		super.setHintEditext();
		edtCurrentPassword.setText("");
		edtConfirmPassword.setText("");
		edtNewPassword.setText("");
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.finish();
		super.onBackPressed();
	}
	
	
}
