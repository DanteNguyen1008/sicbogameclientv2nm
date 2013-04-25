package kibow.games.casinohills.sicbo.Acitivty;

import com.example.sicbogameexample.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ProfileActivity extends Activity implements OnClickListener {

	Button btnChangePasswod, btnBitcoin, btnSendDeposit;
	ImageButton imgBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		btnChangePasswod = (Button) findViewById(R.id.btn_change_password);
		btnSendDeposit = (Button) findViewById(R.id.btn_sent_deposit);
		imgBack = (ImageButton) findViewById(R.id.btn_back);
		btnBitcoin = (Button) findViewById(R.id.btn_bitcoin);
		btnChangePasswod.setOnClickListener(this);
		btnSendDeposit.setOnClickListener(this);
		imgBack.setOnClickListener(this);
		btnBitcoin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_change_password:
			Intent intent1 = new Intent(ProfileActivity.this,
					ChangePassword.class);
			startActivity(intent1);
			break;
		case R.id.btn_bitcoin:
			Intent a=new Intent(this,WebviewHelpPage.class);
			a.putExtra("idButton", 4);
			startActivity(a);
			break;
		case R.id.btn_sent_deposit:
			Intent b=new Intent(this,WebviewHelpPage.class);
			b.putExtra("idButton", 5);
			startActivity(b);
			break;
		case R.id.btn_back:
			this.finish();
			break;
		}

	}

}
