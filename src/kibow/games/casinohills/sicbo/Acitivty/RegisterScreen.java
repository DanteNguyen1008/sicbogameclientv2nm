package kibow.games.casinohills.sicbo.Acitivty;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kibow.games.casinohills.sicbo.networks.AsyncNetworkHandler;
import kibow.games.casinohills.sicbo.networks.ConnectionHandler;
import kibow.games.casinohills.sicbo.networks.IOnNetworkHandle;
import kibow.games.casinohills.sicbo.screen.GameEntity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.sicbogameexample.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterScreen extends BaseActivity implements OnClickListener,
		IOnNetworkHandle {
	EditText edtUsername;
	EditText edtPassword;
	EditText edtConfirmPassword;
	EditText edtEmail, edtFullName;
	TextView txtGamePolicy;
	CheckBox checkBox;
	Button btnRegister;
	ImageButton imgBack;
	ConnectionHandler connectionHandler;
	String fb_email = "";
	String fb_username = "";
	String fb_fullname = "";
	boolean is_facebook_account = false;
	boolean isValidPassword = true;
	boolean isValidEmail = false;
	boolean isMissTyping = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_screen);
		connectionHandler = new ConnectionHandler();
		edtUsername = (EditText) findViewById(R.id.edt_username);
		edtPassword = (EditText) findViewById(R.id.edt_password);
		txtGamePolicy = (TextView) findViewById(R.id.txt_link_policy);
		checkBox = (CheckBox) findViewById(R.id.checkBox1);
		SpannableString content = new SpannableString("Do you agree policy");
		content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
		txtGamePolicy.setText(content);
		txtGamePolicy.setOnClickListener(this);
		edtPassword.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1 == true) {
					// Toast.makeText(getApplicationContext(),
					// "Password must at least 6 character",
					// Toast.LENGTH_LONG).show();
				} else {
					if (edtPassword.getText().toString().length() < 6) {

						isValidPassword = false;
					} else {
						isValidPassword = true;
					}
				}
			}
		});
		edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
		edtConfirmPassword.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
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
				if (edtPassword.getText().toString().length() < 6) {

					Toast.makeText(getApplicationContext(),
							"Password must at least 6 character",
							Toast.LENGTH_LONG).show();
					isValidPassword = false;
				} else {
					isValidPassword = true;
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
				if (hasFocus == false) {
					if (validateEmailAddress(edtEmail.getText().toString()) == false) {
						Toast.makeText(getApplicationContext(),
								"InValid email", Toast.LENGTH_LONG).show();
						isValidEmail = false;
					} else {
						isValidEmail = true;
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

	private boolean validateEmailAddress(String emailAddress) {
		String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = emailAddress;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		boolean a = matcher.matches();
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

			Intent intent2 = new Intent(RegisterScreen.this, LoginScreen.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(intent2);
			break;
		case R.id.txt_link_policy:
			Intent intent = new Intent(RegisterScreen.this,
					WebviewHelpPage.class);
			intent.putExtra("idButton", 3);
			startActivity(intent);
			break;

		}
	}

	/*
	 * {"response":{"task_title":"res_sign_up","data":{"message":
	 * "Your registration is success, please check your email to active your account."
	 * },"status":true}}
	 */

	@Override
	public void onNetwokrHandle(JSONObject result,
			ConnectionHandler connectionHandler, Activity activity)
			throws JSONException {
		// TODO Auto-generated method stub

		if (result != null) {
			if (connectionHandler.status) {
				createDialog(result.getString("message"));
			} else {
				Toast.makeText(activity, result.getString("message"),
						Toast.LENGTH_LONG).show();
			}

		} else {
			Toast.makeText(getApplicationContext(),
					"Error network!Please try again", Toast.LENGTH_LONG).show();
		}
	}

	void checkValidPassword() {

		if (edtConfirmPassword.length() == 0 || edtEmail.length() == 0
				|| edtFullName.length() == 0 || edtUsername.length() == 0
				|| edtPassword.length() == 0) {
			Toast.makeText(getApplicationContext(), "Miss Typing",
					Toast.LENGTH_LONG).show();
			isValidPassword = false;

		} else {
			if (edtPassword.getText().toString()
					.equals(edtConfirmPassword.getText().toString()) == true) {
				if (isValidPassword == true) {
					if (isValidEmail == true) {
						if (checkBox.isChecked() == true) {
							AsyncNetworkHandler networkHandler = new AsyncNetworkHandler();
							ArrayList<String> paramsName = new ArrayList<String>();
							paramsName.add("accountname");
							paramsName.add("password");
							paramsName.add("email");
							paramsName.add("nickname");
							paramsName.add("language");
							paramsName.add("realname");
							paramsName.add("birthday");
							paramsName.add("sex");
							paramsName.add("country");
							paramsName.add("address");
							paramsName.add("phone");
							ArrayList<Object> paramsValue = new ArrayList<Object>();
							paramsValue.add(edtUsername.getText().toString()
									.trim());
							paramsValue.add(edtPassword.getText().toString()
									.trim());
							paramsValue.add(edtEmail.getText().toString()
									.trim());
							paramsValue.add(edtFullName.getText().toString()
									.trim());
							paramsValue.add("EN");
							paramsValue.add(edtFullName.getText().toString()
									.trim());
							paramsValue.add("1990-08-10");
							paramsValue.add("1");
							paramsValue.add("BR");
							paramsValue.add("address address");
							paramsValue.add("0123456789");

							Object[] params = { connectionHandler, this,
									GameEntity.SIGNUP_TASK, paramsName,
									paramsValue, this };
							networkHandler.execute(params);
						} else
							Toast.makeText(getApplicationContext(),
									"You have to agree policy",
									Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(getApplicationContext(),
								"InValid email", Toast.LENGTH_LONG).show();
					}
				} else
					Toast.makeText(getApplicationContext(),
							"Password at least 6 charaters", Toast.LENGTH_LONG)
							.show();
			} else
				Toast.makeText(getApplicationContext(),
						"Confirm password doesn't match", Toast.LENGTH_LONG)
						.show();

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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		super.onBackPressed();
	}

	@Override
	public void onNetworkError() {
		// TODO Auto-generated method stub
		
	}

}
