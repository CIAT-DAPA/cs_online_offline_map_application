package org.cgiar.ciat.hybridmapsandroid.views.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.cgiar.ciat.hybridmapsandroid.R;
import org.cgiar.ciat.hybridmapsandroid.views.fragments.PointsListFragment;

public class PointsActivity extends ActionBarActivity {

    Fragment fgm_points_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);

        try{
            fgm_points_list=new PointsListFragment();
            if(savedInstanceState == null){
                getSupportFragmentManager().beginTransaction().add(R.id.act_points_container, fgm_points_list,"points" ).commit();
            }
        }catch (Exception e){
            Log.d("ACT", e.getMessage());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_points, menu);
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
        if (id == R.id.action_maps) {
            startActivity(new Intent(this,MapsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
