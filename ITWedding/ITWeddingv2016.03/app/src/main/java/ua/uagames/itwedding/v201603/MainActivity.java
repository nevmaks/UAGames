package ua.uagames.itwedding.v201603;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {


    private AdView banner;
    private WebView viewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewer = (WebView) findViewById(R.id.viewer);
        viewer.getSettings().setJavaScriptEnabled(true);
        viewer.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        viewer.loadUrl("https://drive.google.com/file/d/0B3qWNFQol8lIdE5oSDU2WnhNT1E/view?usp=sharing");

        MobileAds.initialize(getApplicationContext(), getString(R.string.banner_ad_app_id));
        banner = (AdView) findViewById(R.id.banner);
        AdRequest adRequest = new AdRequest.Builder().build();
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        banner.loadAd(adRequest);
    }

    @Override
    protected void onDestroy() {
        if (banner != null)
            banner.destroy();
        if (viewer != null)
            viewer.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (banner != null)
            banner.pause();
        if (viewer != null)
            viewer.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (banner != null)
            banner.resume();
        if (viewer != null)
            viewer.onResume();
    }
}
