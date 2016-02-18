package com.zeus.bookcase.app.home.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.base.BaseActivity;
import com.zeus.bookcase.app.home.view.LoadingView;
import com.zeus.bookcase.app.user.view.ErrorView;

import java.io.File;

/**
 * Created by zeus_coder on 2016/2/3.
 */
public class PreferenceWebActivity extends BaseActivity {

    private static final String TAG = PreferenceWebActivity.class.getSimpleName();
    private static final String APP_WEB_CACAHE_DIRNAME = "/webcache";
    private String url= "https://github.com/zeuscoder";

    private WebView web;
    //private CircularProgressView progressView;
    private LoadingView progressView;
    private ErrorView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home__activity_preferentail_web);
        initTopButton(R.string.activity_book_recommend, R.mipmap.app__top_bar_arrow_back, 0);
        findWebView();
    }

    private void findWebView() {
        web = (WebView) findViewById(R.id.web_preference);
        //progressView = (CircularProgressView) findViewById(R.id.book_web_progress_view);
        progressView = (LoadingView) findViewById(R.id.book_web_progress_view);
        error = (ErrorView) findViewById(R.id.web_error_view);
        initWebView();
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                Log.i(TAG, "onLoadResource url=" + url);
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webview, String url) {
                Log.i(TAG, "intercept url=" + url);
                webview.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e(TAG, "onPageStarted");
                //startAnimationThreadStuff(100);  // 显示加载界面
                progressView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressView.setVisibility(View.GONE); // 隐藏加载界面
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (errorCode == -2) {
                    progressView.setVisibility(View.GONE); // 隐藏加载界面
                }
                web.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                //setContentView(R.layout.user_fragment_order);
                Toast.makeText(getApplicationContext(), "网络错误",
                        Toast.LENGTH_LONG).show();
            }
        });
        web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e(TAG, "onJsAlert " + message);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Log.e(TAG, "onJsConfirm " + message);
                return super.onJsConfirm(view, url, message, result);
            }
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.e(TAG, "onJsPrompt " + url);
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
        web.loadUrl(url);

        //打开本包内asset目录下的index.html文件
        //web.loadUrl("file:///android_asset/web/prefentence.html");

        //打开本地sd卡内的index.html文件
        //web.loadUrl("content://com.android.htmlfileprovider/sdcard/prefentence.html");

        //打开指定URL的html文件
        //web.loadUrl("http://ironsummitmedia.github.io/startbootstrap-agency/");
        //web.loadUrl("https://github.com/zeuscoder");
    }

    private void initWebView() {
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        //开启 database storage API功能
        web.getSettings().setDomStorageEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_WEB_CACAHE_DIRNAME;
        Log.i(TAG, "webView------------cache=" + cacheDirPath);
        //设置数据库缓存路径
        web.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        web.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        web.getSettings().setAppCacheEnabled(true);
    }

    /**
     * 清除WebView缓存
     */
    public void clearWebViewCache(){
        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath() + APP_WEB_CACAHE_DIRNAME);
        Log.e(TAG, "appCacheDir path="+appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath() + "/webviewCache");
        Log.e(TAG, "webviewCacheDir path=" + webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            deleteFile(appCacheDir);
        }
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {
        Log.i(TAG, "delete file path=" + file.getAbsolutePath());
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {
            Log.e(TAG, "delete file no exists " + file.getAbsolutePath());
        }
    }
}
