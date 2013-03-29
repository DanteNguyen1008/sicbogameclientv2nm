package com.example.sicbogameexample;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo.components.UserComponent;
import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterScreen extends BaseActivity implements OnClickListener {
	EditText edtUsername;
	EditText edtPassword;
	EditText edtConfirmPassword;
	EditText edtEmail, edtFullName;
	Button btnRegister;
	ImageButton imgBack;
	ConnectionHandler connectionHandler;
	String fb_email = "";
	String fb_username = "";
	String fb_fullname = "";
	boolean is_facebook_account = false;
	boolean isValidPassword=true;
	boolean isValidEmail=false;
	boolean isMissTyping =false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_screen);
		connectionHandler = new ConnectionHandler();
		edtUsername = (EditText) findViewById(R.id.edt_username);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		
		edtPassword.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg1==true)
				{
					//Toast.makeText(getApplicationContext(), "Password must at least 6 character", Toast.LENGTH_LONG).show();
				}
				else
				{
					if(edtPassword.getText().toString().length()<6)
	            	{
	            		
	            		isValidPassword=false;
	            	}
	            	else
	            	{
	            		isValidPassword=true;
	            	}
				}
			}
		});
		edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
edtConfirmPassword.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(edtPassword.getText().toString().length()<6)
				{   
					
					Toast.makeText(getApplicationContext(), "Password must at least 6 character", Toast.LENGTH_LONG).show();
					isValidPassword=false;
				}
				else 
				{
					isValidPassword=true;
				}
			}
		});
		edtEmail = (EditText) findViewById(R.id.edt_email);
		final String email = edtEmail.getText().toString().trim();

		final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		edtEmail.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus==false)
				{
					if(validateEmailAddress(edtEmail.getText().toString())==false)
					{
						Toast.makeText(getApplicationContext(), "InValid email", Toast.LENGTH_LONG).show();
					   isValidEmail=false;
					}
					else
					{
						isValidEmail=true;
					}
					
				}
			}
		});
		
		edtFullName = (EditText) findViewById(R.id.edt_full_name);
		btnRegister = (Button) findViewById(R.id.btn_register);
		imgBack = (ImageButton) findViewById(R.id.btn_back);
		imgBack.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

		Intent intent = getIntent();
		if (intent.getStringExtra("email") != null) {
			fb_email = intent.getStringExtra("email");
			edtEmail.setText(fb_email);
			is_facebook_account = true;
		}

		if (intent.getStringExtra("username") != null) {
			fb_username = intent.getStringExtra("username");
			edtUsername.setText(fb_username);
		}

		if (intent.getStringExtra("fullname") != null) {
			fb_fullname = intent.getStringExtra("fullname");
			edtFullName.setText(fb_fullname);
		}
	}
     
	private boolean validateEmailAddress(String emailAddress){
	    String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";  
	       CharSequence inputStr = emailAddress;  
	       Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
	       Matcher matcher = pattern.matcher(inputStr); 
	       boolean a=matcher.matches();
	       return matcher.matches();
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

		switch (v.getId()) {
		case R.id.btn_register:
			 checkValidPassword();
			 
			
			 
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
				// dataList = connectionHandler.parseData(responseName);
				JSONObject result = connectionHandler.getResult();
				// Create user and move to game scene
                if(result!=null)
                {
				if (result.getBoolean("is_success")) {
					GameEntity.getInstance().userComponent = new UserComponent(
							edtUsername.getText().toString(), edtEmail
									.getText().toString(),
							result.getDouble("balance"));
					progressDialog.dismiss();
					if (!result.getBoolean("is_facebook_account")) {
						createDialog();
					} else {
						Intent intent = new Intent(activity,
								SicBoGameActivity.class);
						activity.startActivity(intent);
						activity.finish();
					}

				} else {
					Toast.makeText(activity, result.getString("message"),
							Toast.LENGTH_LONG).show();
					progressDialog.dismiss();
				}
                }
                else
                {
                	Toast.makeText(getApplicationContext(),"Error network!Please try again", Toast.LENGTH_LONG).show();
                	progressDialog.dismiss();
                }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
   void checkValidPassword()
   {
	   
	   if(edtConfirmPassword.length()==0||
			   edtEmail.length()==0||
			   edtFullName.length()==0||
			   edtUsername.length()==0||
			   edtPassword.length()==0)
	   {
		   Toast.makeText(getApplicationContext(), "Miss Typing", Toast.LENGTH_LONG).show();
		   isValidPassword=false;
		  
	   }
	   else
	   {   
		   if(edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())==true)
		   {  
			   if(isValidPassword==true)
			   {
				   if(isValidEmail==true)
				   {
			   ConnectionAsync connectionAsync = new ConnectionAsync();
				String[] paramsName = { "username", "password", "email",
						"fullname", "is_facebook_account" };
				String[] paramsValue = { edtUsername.getText().toString().trim(),
						edtPassword.getText().toString().trim(),
						edtEmail.getText().toString().trim(),
						edtFullName.getText().toString().trim(),
						is_facebook_account + "" };
				Object[] params = { connectionHandler, this,
						GameEntity.SIGNUP_TASK, paramsName, paramsValue };
				connectionAsync.execute(params);
				   }
				   else
				   {
					   Toast.makeText(getApplicationContext(), "InValid email", Toast.LENGTH_LONG).show();
				   }
			   }
			   else
				   Toast.makeText(getApplicationContext(), "Password at least 6 charaters", Toast.LENGTH_LONG).show();
		   }
		   else
			   Toast.makeText(getApplicationContext(), "Confirm password doesn't match", Toast.LENGTH_LONG).show();
				 
		   
	   }
	   
   }
	@Override
	protected void setHintEditext() {
		// TODO Auto-generated method stub
		super.setHintEditext();
		edtConfirmPassword.setText("");
		edtEmail.setText("");
		edtFullName.setText("");
		edtUsername.setText("");
		edtPassword.setText("");
	}

}
