package com.example.sicbogameexample;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebviewHelpPage extends Activity {

	WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_help);
		
		mWebView = (WebView) findViewById(R.id.webbiew_help);
		mWebView.getSettings().setSupportZoom(true); // Zoom Control on web (You
		// if ROM supports Multi-Touch
		mWebView.getSettings().setBuiltInZoomControls(true); 
		mWebView.loadUrl("http://www.google.com.vn");
	}

}
