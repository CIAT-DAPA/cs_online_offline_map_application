package org.cgiar.ciat.hybridmapsandroid.views.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.cgiar.ciat.hybridmapsandroid.R;
import org.cgiar.ciat.hybridmapsandroid.tools.Preferences;

public class SettingsActivity extends Activity {

    Switch set_cmd_connection_work;
    EditText set_txt_layer_online;
    EditText set_txt_layer_offline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        try{
            // Load preferences
            Preferences.init(getSharedPreferences(Preferences.NAME,Context.MODE_PRIVATE));
            // Init controls
            set_cmd_connection_work = (Switch)findViewById(R.id.set_cmd_connection_work);
            set_txt_layer_online = (EditText)findViewById(R.id.set_txt_layer_online);
            set_txt_layer_offline = (EditText)findViewById(R.id.set_txt_layer_offline);
            // Load values
            set_cmd_connection_work.setChecked(Preferences.getInstance().getWorkMode());
            set_txt_layer_online.setText(Preferences.getInstance().getOnlineTileServer());
            set_txt_layer_offline.setText(Preferences.getInstance().getOfflineTileServer());
        }catch (Exception e){
            Log.d("ACT", e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_maps) {
            startActivity(new Intent(this,MapsActivity.class));
            return true;
        }
        if (id == R.id.action_points) {
            startActivity(new Intent(this,PointsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Command button action save
     * @param v
     */
    public void save_preferences(View v){
        Preferences.getInstance().setWorkMode(set_cmd_connection_work.isChecked());
        Preferences.getInstance().setOnlineTileServer(set_txt_layer_online.getText().toString());
        Preferences.getInstance().setOfflineTileServer(set_txt_layer_offline.getText().toString());
        Preferences.getInstance().save();
        Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show();
    }

}
