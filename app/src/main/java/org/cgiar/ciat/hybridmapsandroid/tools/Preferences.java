package org.cgiar.ciat.hybridmapsandroid.tools;

import android.content.SharedPreferences;

/**
 * Created by HSOTELO on 4/6/2016.
 */
public class Preferences {

    // Constants
    public static final String NAME = "hybrid_map";
    public static final String WORK_MODE = "pref_work_mode";
    public static final String ONLINE_TILE_SERVER = "pref_online_tile_server";
    public static final String OFFLINE_TILE_SERVER = "pref_offline_tile_server";

    // Instance
    private static Preferences _instance;

    // Members Class
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    /**
     * Get the singleton cla
     * @return
     */
    public static Preferences getInstance(){
        if(_instance == null)
            _instance=new Preferences();
        return _instance;
    }

    /**
     * Get if the application is working online
     * @return True is online, false is offline
     */
    public boolean getWorkMode(){
        return sharedPref.getBoolean(Preferences.WORK_MODE, false);
    }

    /**
     * Set if the application is working online
     * @param value
     */
    public void setWorkMode(boolean value){
        editor.putBoolean(Preferences.WORK_MODE, value);
    }

    /**
     * Get the url of the tile server to work online
     * @return
     */
    public String getOnlineTileServer(){
        return sharedPref.getString(Preferences.ONLINE_TILE_SERVER, null);
    }

    /**
     * Set the url of the tile server to work online
     * @param value
     */
    public void setOnlineTileServer(String value){
        editor.putString(Preferences.ONLINE_TILE_SERVER, value);
    }

    /**
     * Get the url of the tile server to work offline
     * @return
     */
    public String getOfflineTileServer(){
        return sharedPref.getString(Preferences.OFFLINE_TILE_SERVER, null);
    }

    /**
     * Set the url of the tile server to work offline
     * @param value
     */
    public void setOfflineTileServer(String value){
        editor.putString(Preferences.OFFLINE_TILE_SERVER, value);
    }

    /**
     * Method Construct
     */
    public Preferences(){
    }

    /**
     * Method that init the instance
     * @param sp
     */
    public static void init(SharedPreferences sp){
        sharedPref = sp;
        editor = sharedPref.edit();
        if(!isSetted())
            initConfig();
    }

    /**
     * Init the values to configure the application
     */
    private static void initConfig(){
        editor.putBoolean(Preferences.WORK_MODE, false);
        editor.putString(Preferences.ONLINE_TILE_SERVER, "http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png");
        editor.putString(Preferences.OFFLINE_TILE_SERVER, "");
        editor.commit();
    }

    /**
     * Establish if the preferences were initializate
     * @return
     */
    private static boolean isSetted(){
        return sharedPref.getString(Preferences.ONLINE_TILE_SERVER, null) != null;
    }

    /**
     * Method that saved the preferences
     */
    public static void save(){
        editor.commit();
    }


}
