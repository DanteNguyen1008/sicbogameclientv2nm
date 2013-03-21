package com.example.sicbogameexample;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HelpActivity extends Activity implements OnClickListener{
   
	TextView txtHelp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		txtHelp=(TextView)findViewById(R.id.txt_help);
		txtHelp.setOnClickListener(this);
	
	}

	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(HelpActivity.this,WebviewHelpPage.class);
		startActivity(intent);
		
	}

	


}
