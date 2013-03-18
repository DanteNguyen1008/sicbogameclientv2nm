package com.example.sicbogameexample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ViewHistoryActivity extends Activity implements OnClickListener {

	LinearLayout llHistoryList;
	Button btnBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_view_history);

		llHistoryList = (LinearLayout) findViewById(R.id.llHistoryList);
		btnBack = (Button) findViewById(R.id.btnBack);
		
		btnBack.setOnClickListener(this);

		// load history list
		// Create linear each line
		LinearLayout llhistoryTitle = new LinearLayout(this);
		LinearLayout.LayoutParams llhistoryParamsTitle = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		llhistoryParamsTitle.topMargin = 20;
		// llhistoryParams.leftMargin = 20;
		llhistoryParamsTitle.rightMargin = 20;
		llhistoryTitle.setOrientation(LinearLayout.HORIZONTAL);
		llhistoryTitle.setBackgroundColor(Color.BLUE);
		// Add content for line
		// Win or lose
		TextView tvIsWinTitle = new TextView(this);
		tvIsWinTitle.setText("Result");
		tvIsWinTitle.setTextColor(Color.WHITE);

		// Balance
		TextView tvBalanceTitle = new TextView(this);
		tvBalanceTitle.setText("Balance");
		tvBalanceTitle.setTextColor(Color.WHITE);
		
		// Date Create
		TextView tvDateCreateTitle = new TextView(this);
		tvDateCreateTitle.setText("Date time");
		tvDateCreateTitle.setTextColor(Color.WHITE);
		
		LinearLayout.LayoutParams componentParamsTitle = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		componentParamsTitle.leftMargin = 10;

		// add view for the line
		llhistoryTitle.addView(tvIsWinTitle, componentParamsTitle);
		llhistoryTitle.addView(tvBalanceTitle, componentParamsTitle);
		llhistoryTitle.addView(tvDateCreateTitle, componentParamsTitle);
		llHistoryList.addView(llhistoryTitle, llhistoryParamsTitle);
		for (int i = 0; i < GameEntity.userComponent.historyList.size(); i++) {
			// Create linear each line
			LinearLayout llhistory = new LinearLayout(this);
			if(i%2 == 0)
			{
				llhistory.setBackgroundColor(Color.GRAY);
			}
			LinearLayout.LayoutParams llhistoryParams = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			llhistoryParams.topMargin = 20;
			// llhistoryParams.leftMargin = 20;
			llhistoryParams.rightMargin = 20;
			llhistory.setOrientation(LinearLayout.HORIZONTAL);

			// Add content for line
			// Win or lose
			TextView tvIsWin = new TextView(this);
			if (GameEntity.userComponent.historyList.get(i).isWin)
				tvIsWin.setText("Win");
			else
				tvIsWin.setText("Lose");

			// Balance
			TextView tvBalance = new TextView(this);
			tvBalance.setText("" + GameEntity.userComponent.historyList.get(i).balance);

			// Date Create
			TextView tvDateCreate = new TextView(this);
			/*
			Timestamp timestamp = new Timestamp(
					Long.parseLong(ConfigClass.user.historyList.get(i).betDate));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"MM/dd/yyyy' 'HH:MM");
					*/
			
			tvDateCreate.setText(convertSecondsToDate(Long.parseLong(GameEntity.userComponent.historyList.get(i).betDate), "dd/MM/yyyy hh:mm:ss"));

			LinearLayout.LayoutParams componentParams = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

			componentParams.leftMargin = 10;

			// add view for the line
			llhistory.addView(tvIsWin, componentParams);
			llhistory.addView(tvBalance, componentParams);
			llhistory.addView(tvDateCreate, componentParams);
			llHistoryList.addView(llhistory, llhistoryParams);
		}
	}
	
	public String convertSecondsToDate(long seconds, String dateFormat)
	{
		long dateInMillis = seconds * 1000;
 
		// Create a DateFormatter object for displaying date in specified format.
		DateFormat formatter = new SimpleDateFormat(dateFormat);
 
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateInMillis);
 
		return formatter.format(calendar.getTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.activity_view_history, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.equals(btnBack)) {
			GameEntity.userComponent.historyList.clear();
			Intent intent = new Intent(this, SicBoGameActivity.class);
			this.startActivity(intent);
			finish();
		}
	}

}
