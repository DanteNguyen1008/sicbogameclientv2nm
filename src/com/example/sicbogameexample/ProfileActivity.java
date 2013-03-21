package com.example.sicbogameexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ProfileActivity extends Activity implements OnClickListener{

	Button btnChangePasswod,btnBitcoin;
	ImageButton imgBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		btnChangePasswod=(Button)findViewById(R.id.btn_change_password);
		imgBack=(ImageButton)findViewById(R.id.btn_back);
		btnBitcoin=(Button)findViewById(R.id.btn_bitcoin);
		btnChangePasswod.setOnClickListener(this);
		imgBack.setOnClickListener(this);
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
		case R.id.btn_back:
			this.finish();
			break;
		}
	
	}



}
