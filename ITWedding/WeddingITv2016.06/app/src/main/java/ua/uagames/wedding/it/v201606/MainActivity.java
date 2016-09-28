package ua.uagames.wedding.it.v201606;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    private static final String WEB_BUNDLE = "web_bundle";

    private WebView viewer;
    private FirebaseAnalytics analytics;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        analytics = FirebaseAnalytics.getInstance(this);
        bundle = new Bundle();

        viewer = (WebView) findViewById(R.id.viewer);
        viewer.getSettings().setJavaScriptEnabled(true);
        viewer.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        if (savedInstanceState != null)
            viewer.restoreState(savedInstanceState.getBundle(WEB_BUNDLE));
        else
            viewer.loadUrl("https://drive.google.com/file/d/0B3qWNFQol8lISURZaC1BN190OFE/view?usp=sharing");

        bundle.putString("app_name", "Wedding: IT v2016.06.");
        bundle.putString("app_event", "Create app and load content.");
        analytics.logEvent("app_statistics", bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Bundle web = new Bundle();
        viewer.saveState(web);
        outState.putBundle(WEB_BUNDLE, web);
        super.onSaveInstanceState(outState);
    }
}
