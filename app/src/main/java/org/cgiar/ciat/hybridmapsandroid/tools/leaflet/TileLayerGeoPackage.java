package org.cgiar.ciat.hybridmapsandroid.tools.leaflet;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import org.cgiar.ciat.hybridmapsandroid.models.maps.repositories.MapDB;
import org.cgiar.ciat.hybridmapsandroid.models.maps.source.Tiles;

import java.util.List;

/**
 * Created by HSOTELO on 1/21/2016.
 */
public class TileLayerGeoPackage {

    Context mContext;

    /** Instantiate the interface and set the context */
    public TileLayerGeoPackage(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public String getTiles(long x, long y, int z) {
        String value;
        List<Tiles> tiles;
        try{
            MapDB  db=new MapDB(mContext);
            MapDB.TilesEntity entity=db.new TilesEntity();
            tiles=entity.getByCoordinates(x, y, z);
            if(tiles != null  && tiles.size() > 0)
                value = tiles.get(0).getTile_dataBase64();
            else
                value = null;
        }
        catch(Exception ex){
            value=null;
        }
        return value;
    }

}
