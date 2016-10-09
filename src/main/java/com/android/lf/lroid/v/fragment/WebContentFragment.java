package com.android.lf.lroid.v.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.lf.lroid.R;

import butterknife.BindView;

/**
 * Created by feng on 2016/9/13.
 */

public class WebContentFragment extends LroidBaseFragment {

    public static final String WEB_LOAD_URL = "web_load_url";

    @BindView(R.id.id_pb_fragment_web_progress)
    ProgressBar mProgress;

    @BindView(R.id.id_wb_fragment_web_content)
    WebView mWebView;

    public static WebContentFragment newInstance(Bundle args) {
        WebContentFragment fragment = new WebContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_web_content_layout;
    }

    @Override
    protected void initView(View view) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgress.setProgress(newProgress);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgress.setVisibility(View.GONE);
            }
        });
        String url = getArguments().getString(WEB_LOAD_URL);
        if(url!=null && (url.startsWith("http://")|| url.startsWith("https://"))) {
            mWebView.loadUrl(url);
        }else {
            mWebView.loadData(url,"text/html; charset=UTF-8",null);
        }
    }

    @Override
    protected void setComponent() {
    }
}
