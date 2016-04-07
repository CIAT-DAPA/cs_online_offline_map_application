package org.cgiar.ciat.hybridmapsandroid.tools.leaflet;


import android.content.Context;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.cgiar.ciat.hybridmapsandroid.tools.Preferences;
import org.cgiar.ciat.hybridmapsandroid.tools.map.Marker;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public class L extends WebChromeClient {

    /* Constants */
    public static final String CONTAINER="map";

    /* Members Class */
    private Context context;
    private WebView browser;
    private List markers;

    public List getMarkers() {
        return markers;
    }

    public void addMarker(Marker marker) {
        this.markers.add(marker);
    }

    /* Methods */

    /**
     * Method Construct
     * @param context
     * @param browser
     */
    public L(Context context, WebView browser) {
        this.context = context;
        this.browser = browser;
        this.markers = new LinkedList();
    }

    /**
     * Method that set the browser
     */
    public void init(){
        browser.setWebChromeClient(new WebChromeClient() {
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                Log.d("MapCiat", message + " -- From line " + lineNumber + " of " + sourceID);
            }
        });
        WebSettings browser_settings = browser.getSettings();
        browser_settings.setJavaScriptEnabled(true);
        browser.loadUrl("file:///android_asset/www/map.html");
    }

    /**
     * Method that add a new marker in the map
     */
    public void showMarkers(){
        browser.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(browser, url);
                boolean work_mode = Preferences.getInstance().getWorkMode();
                String url_online = Preferences.getInstance().getOnlineTileServer();
                browser.loadUrl("javascript:init_map(" + (work_mode ? "1" : "0") + ",'" + url_online + "')");
                List<Marker> _markers=markers;
                for(int i=0;i<_markers.size();i++)
                    browser.loadUrl("javascript:addMarker(" + _markers.get(i).getPosition().toString()  + ",'" + _markers.get(i).getTitle() +"')");
            }
        });
    }

}
