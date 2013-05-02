package kibow.games.casinohills.sicbo.Acitivty;

import java.util.ArrayList;
import java.util.List;

import kibow.games.casinohills.sicbo.components.UserComponent;
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

public class LoginScreen extends Activity implements OnClickListener,
		IOnNetworkHandle {
	EditText edt_username;
	EditText edt_password;
	Button btn_sign_in;
	ImageButton btnBack;
	TextView txtCreatAccount;
	TextView txtForgotPassword;
	List<Object> dataList;
	String[] responseName = { "username", "password" };
	public boolean isLoginWaiting = false;
	public boolean isAutoLogin = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameEntity.getInstance().connectionHandler = new ConnectionHandler();

		isAutoLogin = false;
		setContentView(R.layout.activity_login_screen);
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

	private void normalLogin(ArrayList<Object> paramsValue) {
		AsyncNetworkHandler networkHandler = new AsyncNetworkHandler();
		ArrayList<String> paramsName = new ArrayList<String>();
		paramsName.add("username");
		paramsName.add("password");
		paramsName.add("game_id");
		Object[] params = { GameEntity.getInstance().connectionHandler, this,
				GameEntity.SIGNIN_TASK, paramsName, paramsValue, this };
		// connectionAsync.execute(params);
		networkHandler.execute(params);
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
			if (edt_password.length() != 0 && edt_username.length() != 0) {

				ArrayList<Object> paramsValues = new ArrayList<Object>();
				paramsValues.add(edt_username.getText().toString().trim());
				paramsValues.add(edt_password.getText().toString().trim());
				paramsValues.add(GameEntity.GAME_ID);
				normalLogin(paramsValues);
			} else {
				Toast.makeText(getApplicationContext(), "Miss Typing",
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.txt_forgot_password:

			Intent itent = new Intent(LoginScreen.this, ResetPassword.class);
			startActivity(itent);
			break;

		case R.id.btn_back:
			break;

		}

	}

	@Override
	public void onNetwokrHandle(JSONObject result,
			ConnectionHandler connectionHandler, Activity activity)
			throws JSONException {
		if (connectionHandler.status) {

			JSONObject loginData = result.getJSONObject("login");
			String token = result.getString("token");
			if (loginData.has("user")) {

				Intent intent = new Intent(activity, SicBoGameActivity.class);

				JSONObject userJson = loginData.getJSONObject("user");
				JSONObject creditJson = loginData.getJSONObject("credit");

				GameEntity.getInstance().userComponent = new UserComponent(
						userJson.getString("id"), userJson.getInt("status"),
						userJson.getString("nickname"),
						userJson.getString("language"),
						userJson.getString("created"),
						userJson.getString("modified"),
						userJson.getString("email"),
						creditJson.getDouble("balance"), token);
				activity.startActivity(intent);
				activity.finish();
			} else {
				Toast.makeText(activity, "Login fail, please try again",
						Toast.LENGTH_LONG).show();
			}
		} else {

			Toast.makeText(activity, "Login fail, please try again",
					Toast.LENGTH_LONG).show();

		}

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

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
