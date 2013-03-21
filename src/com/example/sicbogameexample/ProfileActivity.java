package com.example.sicbogameexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProfileActivity extends Activity implements OnClickListener{

	Button btnChangePasswod,btnBitcoin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		btnChangePasswod=(Button)findViewById(R.id.btn_change_password);
		btnBitcoin=(Button)findViewById(R.id.btn_bitcoin);
		btnChangePasswod.setOnClickListener(this);
		btnBitcoin.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_change_password:
		  Intent intent1=new Intent(ProfileActivity.this,ChangePassword.class);
		  startActivity(intent1);
		  break;
		case R.id.btn_bitcoin:
			break;
		}
	
	}



}
