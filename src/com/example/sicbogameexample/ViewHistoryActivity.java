package com.example.sicbogameexample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import sicbo.components.HistoryComponent;



import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewHistoryActivity extends Activity implements OnClickListener {

	
   TableLayout tblHistory;
   int size;
   List<HistoryComponent> historyGame;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_history);
		tblHistory=(TableLayout)findViewById(R.id.table_history);
		historyGame=GameEntity.getInstance().userComponent.historyList;
		size=historyGame.size();
		initializeHeaderRow(tblHistory);
		fillRow();
	}
		

  private void fillRow()
  {
	  int textColor = Color.BLACK;
      float textSize = 10f;
      String result;
	  for (int i = 0; i < size; i++) {
		  if (historyGame.get(i).isWin)
				result="Win";
			else
				result="lose";
		    TableRow headerRow = new TableRow(this);
		    addTextToRowWithValues(headerRow, convertSecondsToDate(Long.parseLong(historyGame.get(i).betDate), "dd/MM/yyyy hh:mm:ss"), textColor, textSize);
		    addTextToRowWithValues(headerRow, result, textColor, textSize);
		    addTextToRowWithValues(headerRow, String.valueOf(historyGame.get(i).balance), textColor, textSize);
	        
	        tblHistory.addView(headerRow);
	  }
  }
	 private void initializeHeaderRow(TableLayout scoreTable) {
	        // Create the Table header row
	        TableRow headerRow = new TableRow(this);

	        int textColor = Color.WHITE;
	        float textSize = 10f;

	        headerRow.setBackgroundColor(Color.BLUE);
	        addTextToRowWithValues(headerRow, "Date Time", textColor, textSize);
	        addTextToRowWithValues(headerRow, "Result", textColor, textSize);
	        addTextToRowWithValues(headerRow, "Balance", textColor, textSize);
	        scoreTable.addView(headerRow);
	    }
	 private void addTextToRowWithValues(final TableRow tableRow, String text, int textColor, float textSize) {
	        TextView textView = new TextView(this);
	        textView.setTextSize(textSize);
	        textView.setTextColor(textColor);
	        textView.setText(text);
	        tableRow.addView(textView);
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
		
		//getMenuInflater().inflate(R.menu.activity_view_history, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		/*if (arg0.equals(btnBack)) {
			GameEntity.userComponent.historyList.clear();
=======
		if (arg0.equals(btnBack)) {
			GameEntity.getInstance().userComponent.historyList.clear();
>>>>>>> upstream/master
			Intent intent = new Intent(this, SicBoGameActivity.class);
			this.startActivity(intent);
			finish();
		}*/
	}

}
