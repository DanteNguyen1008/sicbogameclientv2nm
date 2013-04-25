package kibow.games.casinohills.sicbo.Acitivty;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	AlertDialog.Builder alertDialog;
	ProgressDialog progressDialog;
	String getClassName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getClassName = this.getClass().getSimpleName();
	}

	protected void createProgressDialog() {
		progressDialog = new ProgressDialog(this);
		String s = null;

		if (getClassName.equals("RegisterScreen")) {
			s = "Processing data! Please wait...!";
		} else if (getClassName.equals("ChangePassword")) {
			s = "Changing password! Please wait...!";
		} else if (getClassName.equals("ResetPassword")) {
			s = "Reset password! Please wait...!";
		} else if (getClassName.equals("ViewHistoryActivity")) {
			s = "Loading data! Please wait...!";
		}
		progressDialog.setMessage(s);
		progressDialog.show();
	}

	protected void createDialog(String content) {
		alertDialog = new AlertDialog.Builder(this);
		if (getClassName.equals("ChangePassword")) {
			alertDialog.setTitle("Change Password");
		} else {
			alertDialog.setTitle("Active account");
		}
		alertDialog.setMessage(content);
		alertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						setHintEditext();
					}
				});

		alertDialog.show();
	}

	protected void setHintEditext() {

	}
}
