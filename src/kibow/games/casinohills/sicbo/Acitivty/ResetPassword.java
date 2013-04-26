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
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ResetPassword extends BaseActivity implements OnClickListener,
		IOnNetworkHandle {

	EditText edtEmail;
	Button btnResetPassword;
	AlertDialog.Builder alertDialog;

	ImageButton imgBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);

		edtEmail = (EditText) findViewById(R.id.edt_email_reset);
		btnResetPassword = (Button) findViewById(R.id.btn_reset);
		imgBack = (ImageButton) findViewById(R.id.btn_back);
		imgBack.setOnClickListener(this);

		btnResetPassword.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.btn_reset:
			if (edtEmail.length() != 0) {
				AsyncNetworkHandler networkHandler = new AsyncNetworkHandler();
				ArrayList<String> paramsName = new ArrayList<String>();
				paramsName.add("email");
				ArrayList<Object> paramsValue = new ArrayList<Object>();
				paramsValue.add(edtEmail.getText().toString().trim());

				Object[] params = { GameEntity.getInstance().connectionHandler,
						this, GameEntity.FORGOT_PASSWORD_TASK, paramsName,
						paramsValue, this };
				networkHandler.execute(params);
				break;
			} else
				Toast.makeText(getApplicationContext(), "Miss Typing",
						Toast.LENGTH_LONG).show();
			break;
		case R.id.btn_back:
			this.finish();
			break;
		}
	}

	void missTyping() {
		if (edtEmail.length() == 0) {

		}
	}

	@Override
	protected void setHintEditext() {
		// TODO Auto-generated method stub
		super.setHintEditext();
		edtEmail.setText("");
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.finish();
		super.onBackPressed();
	}

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
			Toast.makeText(this, "Error network!Please try again",
					Toast.LENGTH_LONG).show();
		}
	}
}
