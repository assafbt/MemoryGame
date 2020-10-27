/*
 * *
 *  * Created by Assaf Biton
 *
 */

package com.assafbt.projects.memorygame;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url;
        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        url = "https://www.bigbrothers.org.il/";
        webView.loadUrl(url);


    }//onCreate


}