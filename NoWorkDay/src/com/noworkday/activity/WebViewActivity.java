package com.noworkday.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.noworkday.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		webView = (WebView)findViewById(R.id.webview);
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);//设置可以运行JS脚本
//		settings.setTextZoom(120);//Sets the text zoom of the page in percent. The default is 100.
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
//		settings.setUseWideViewPort(true); //打开页面时， 自适应屏幕 
//		settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕 
		settings.setSupportZoom(false);// 用于设置webview放大
		settings.setBuiltInZoomControls(false);
		webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){

                    return false;
                    
            }
		});
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				BufferedReader bufferedReader = null;
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				String data = null;
				try {
					request.setURI(new URI(arg0[0]));
					HttpResponse response = client.execute(request);
					bufferedReader = 
							new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					StringBuffer sBuffer = new StringBuffer();
					String line = "";
					while ((line = bufferedReader.readLine()) != null) {
						sBuffer.append(line);
					}
					bufferedReader.close();
					data = sBuffer.toString();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return data;
			}
			@Override
			protected void onPostExecute(String result) {
				webView.loadDataWithBaseURL(null, result, "text/html", "utf-8", null);
				
			}
		}.execute("http://baidu.com");
	}
}
