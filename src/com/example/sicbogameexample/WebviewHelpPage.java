package com.example.sicbogameexample;

import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewHelpPage extends Activity {

	WebView mWebView;
	Bundle b;
	int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.webview_help);
		b = getIntent().getExtras();
		if (b != null) {
			id = b.getInt("idButton");
		}
		mWebView = new WebView(this);
		loadWebView(id);
		mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
 
        this.setContentView(mWebView);
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
			mWebView.loadUrl(ConnectionHandler.SERVER_ROOT_URL + "policy");
			break;
		case 4:
			mWebView.loadUrl(ConnectionHandler.SERVER_ROOT_URL + "payment/withdrawal.jsp");
			break;
		case 5:
			mWebView.loadUrl(ConnectionHandler.SERVER_ROOT_URL + "payment/SendDeposit.jsp");
			break;
		}
	}

}
