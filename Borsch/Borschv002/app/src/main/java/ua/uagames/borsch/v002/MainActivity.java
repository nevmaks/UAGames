package ua.uagames.borsch.v002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
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

        viewer.loadUrl("https://drive.google.com/file/d/0B3qWNFQol8lILVlzQlJ3V3VHTTg/view?usp=sharing");

        bundle.putString("app_name", "Borsch v002.");
        bundle.putString("app_event", "Create app and load content.");
        analytics.logEvent("app_statistics", bundle);
    }
}
