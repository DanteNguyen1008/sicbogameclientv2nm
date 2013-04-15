package com.example.sicbogameexample;

import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebviewHelpPage extends Activity implements OnClickListener{

	WebView mWebView;
	Bundle b;
	Button btnBack;
	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.webview_help);
		btnBack=(Button)findViewById(R.id.btn_back_webview);
		btnBack.setOnClickListener(this);
		b = getIntent().getExtras();
		if (b != null) {
			id = b.getInt("idButton");
		}
		mWebView = (WebView)findViewById(R.id.webbiew_help);
		loadWebView(id);
		mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
            	// TODO Auto-generated method stub
            	super.onPageFinished(view, url);
            	if(id==0)
            	{
            	btnBack.setVisibility(View.VISIBLE);
            	}
            	
            }
        });
       
 
        
    }
 
    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

	void loadWebView(int id) {
		switch (id) {
		case 0:
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			mWebView.loadUrl(ConnectionHandler.SERVER_ROOT_URL + "tutorial");
			break;
		case 1:
			mWebView.loadUrl(ConnectionHandler.SERVER_ROOT_URL + "help");
			break;
		case 2:
			break;
		case 3:
			mWebView.loadUrl(ConnectionHandler.SERVER_ROOT_URL + "Policy");
			break;
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		this.finish();
	}

}
