package com.example.sicbogameexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity {

	AlertDialog.Builder alertDialog;
	ProgressDialog progressDialog;
	String getClassName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getClassName = this.getClass().getSimpleName();
	}

	void createProgressDialog() {
		progressDialog = new ProgressDialog(this);
		String s = null;
		/*
		 * progressDialog.setIndeterminate(true);
		 * progressDialog.setIndeterminateDrawable
		 * (getResources().getDrawable(R.anim.progress_dialog_anim));
		 * progressDialog.setCancelable(false);
		 */
		if (getClassName.equals("RegisterScreen")) {
			s = "Processing data! Please wait...!";
		} else if (getClassName.equals("ChangePassword")) {
			s = "Changing password! Please wait...!";
		} else if (getClassName.equals("ResetPassword")) {
			s = "Reset password! Please wait...!";
		}
		progressDialog.setMessage(s);
		progressDialog.show();
	}

	void createDialog() {
		alertDialog = new AlertDialog.Builder(this);
		if (getClassName.equals("ChangePassword")) {
			alertDialog.setTitle("new password sent");
			alertDialog.setMessage("Please check your email");
		} else {
			alertDialog.setTitle("Active account");
			alertDialog.setMessage("Please check your email to active");
		}
		alertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						/*
						 * Intent intent = new Intent(Intent.ACTION_MAIN);
						 * intent.addCategory(Intent.CATEGORY_HOME);
						 * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						 */
						Intent intent = new Intent(BaseActivity.this,
								LoginScreen.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
						finish();
					}
				});

		alertDialog.show();
	}

}
