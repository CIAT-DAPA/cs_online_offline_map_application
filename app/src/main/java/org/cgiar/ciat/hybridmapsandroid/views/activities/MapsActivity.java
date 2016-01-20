package org.cgiar.ciat.hybridmapsandroid.views.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import org.cgiar.ciat.hybridmapsandroid.R;
import org.cgiar.ciat.hybridmapsandroid.models.points.repositories.PointsDB;
import org.cgiar.ciat.hybridmapsandroid.models.points.source.Points;
import org.cgiar.ciat.hybridmapsandroid.tools.leaflet.L;
import org.cgiar.ciat.hybridmapsandroid.tools.map.LatLon;
import org.cgiar.ciat.hybridmapsandroid.tools.map.Marker;

import java.util.List;

public class MapsActivity extends ActionBarActivity {
    private WebView act_map_web_view;
    private L map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        act_map_web_view=(WebView)findViewById(R.id.act_map_web_view);
        map = new L(this,act_map_web_view);
        map.init();
        loadMarkers();
        map.showMarkers();
    }

    private void loadMarkers(){
        PointsDB db = new PointsDB(this);
        PointsDB.PointsEntity entity = db.new PointsEntity();
        List<Points> points=entity.all();
        for(int i=0;i<points.size();i++)
            map.addMarker(new Marker(new LatLon(points.get(i).getLat(),points.get(i).getLon()),points.get(i).getName(),points.get(i).getName()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_points) {
            startActivity(new Intent(this,PointsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
