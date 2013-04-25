package kibow.games.casinohills.sicbo.Acitivty;

import com.example.sicbogameexample.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ActiveAccountActivity extends Activity{

	TextView txtActiveAccount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_active_account);
		txtActiveAccount=(TextView)findViewById(R.id.txt_active_account);
		txtActiveAccount.setText(Html.fromHtml(getHtmlString()));
		txtActiveAccount.setMovementMethod(LinkMovementMethod.getInstance());
		txtActiveAccount.setLinksClickable(true);
		
	}

  String getHtmlString()
  {
	  Bundle extras=new Bundle();
	  String html = null;
	  if(extras!=null)
	  {
		  html=extras.getString("html");
	  }
	  return html;
  }
	
}
