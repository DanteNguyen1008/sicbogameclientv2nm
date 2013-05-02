package kibow.games.casinohills.sicbo.Acitivty;

import java.util.ArrayList;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePassword extends BaseActivity implements OnClickListener,
		IOnNetworkHandle {

	EditText edtCurrentPassword, edtNewPassword, edtConfirmPassword;
	Button btnOk;
	ImageButton imgBack;
	boolean validtext = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		edtCurrentPassword = (EditText) findViewById(R.id.edt_current_password);
		edtNewPassword = (EditText) findViewById(R.id.edt_new_password);
		edtNewPassword.setOnFocusChangeListener(new OnFocusChangeListener() {

			public void onFocusChange(View v, boolean hasFocus) {
				if (!hasFocus) {
					if (edtNewPassword.getText().toString().length() < 6) {
						Toast.makeText(getApplicationContext(),
								"Password must at least 6 character",
								Toast.LENGTH_LONG).show();
						validtext = false;
					} else {
						validtext = true;
					}

				}

			}
		});
		edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
		btnOk = (Button) findViewById(R.id.btn_ok);
		imgBack = (ImageButton) findViewById(R.id.btn_back);
		imgBack.setOnClickListener(this);
		btnOk.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ok:
			if (edtConfirmPassword.getText().toString()
					.equals(edtNewPassword.getText().toString()) != true)
				Toast.makeText(this, "Confirm password doesn't match",
						Toast.LENGTH_LONG).show();
			else {
				missTyping();
				if (validtext == true) {
					AsyncNetworkHandler networkHandler = new AsyncNetworkHandler();
					ArrayList<String> paramsName = new ArrayList<String>();
					paramsName.add("password_old");
					paramsName.add("password");
					paramsName.add("password2");
					ArrayList<Object> paramsValue = new ArrayList<Object>();
					paramsValue.add(edtCurrentPassword.getText().toString()
							.trim());
					paramsValue.add(edtNewPassword.getText().toString().trim());
					paramsValue.add(edtConfirmPassword.getText().toString()
							.trim());
					Object[] params = {
							GameEntity.getInstance().connectionHandler, this,
							GameEntity.CHANGE_PASSWORD_TASK, paramsName,
							paramsValue, this };
					networkHandler.execute(params);
				}
			}

			break;
		case R.id.btn_back:
			this.finish();
			break;

		}
	}

	@Override
	public void onNetwokrHandle(JSONObject result,
			ConnectionHandler connectionHandler, Activity activity)
			throws JSONException {
		// TODO Auto-generated method stub
		if (connectionHandler.getTaskID().equals("res_session_expire")) {
			onSessionExpire();
		}
		if (result != null) {
			if (connectionHandler.status) {
				createDialog(result.getString("message"));
			} else {
				Toast.makeText(activity, result.getString("message"),
						Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(ChangePassword.this,
					"Error network!Please try again", Toast.LENGTH_LONG).show();
		}
	}

	public void onSessionExpire() {
		Toast.makeText(this, "Your session is expired", Toast.LENGTH_LONG)
				.show();
		Intent intent = new Intent(this, LoginScreen.class);
		startActivity(intent);
		finish();
	}

	void missTyping() {

		if (edtCurrentPassword.length() == 0
				|| edtConfirmPassword.length() == 0
				|| edtNewPassword.length() == 0) {
			validtext = false;
			Toast.makeText(getApplicationContext(), "Miss Typing",
					Toast.LENGTH_LONG).show();
		} else
			validtext = true;

	}

	void checkPasswordLenght() {
		int lenght = edtNewPassword.getText().toString().length();
		if (lenght < 6) {

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

	@Override
	public void onNetworkError() {
		// TODO Auto-generated method stub

	}

}
