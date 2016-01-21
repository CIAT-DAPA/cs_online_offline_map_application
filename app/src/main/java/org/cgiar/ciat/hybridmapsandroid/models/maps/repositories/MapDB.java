package org.cgiar.ciat.hybridmapsandroid.models.maps.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.cgiar.ciat.hybridmapsandroid.models.DBBase;
import org.cgiar.ciat.hybridmapsandroid.models.maps.source.Tiles;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by HSOTELO on 1/20/2016.
 */
public class MapDB  extends DBBase {

    /* Constants */
    // name of database
    private static final String DB_NAME = "colombia.gpkg";
    private static final int DB_VERSION = 1;

    /**
     * Method Construct
     *
     * @param context
     */
    public MapDB(Context context) {
        super(context,DB_NAME, DB_VERSION);
    }

    /*Entities*/

    public class TilesEntity {
        public List all() {
            List entities = new LinkedList();
            String query = "SELECT id, zoom_level, tile_column, tile_row, tile_data FROM " + Tiles.TABLE_NAME  + " LIMIT 5";
            try {
                SQLiteDatabase db = getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                Tiles entity = null;
                if (cursor.moveToFirst()) {
                    do {
                        entity = new Tiles(cursor.getLong(0),cursor.getInt(1),cursor.getLong(2),cursor.getLong(3),cursor.getBlob(4));
                        entities.add(entity);
                    } while (cursor.moveToNext());
                }
                return entities;
            }catch (Exception e){
                Log.d("DB_MAP_TILES",e.getMessage());
                return null;
            }
        }

        public List getByCoordinates(long x, long y, int z) {
            List entities = new LinkedList();
            String query = "SELECT id, zoom_level, tile_column, tile_row, tile_data FROM " + Tiles.TABLE_NAME + " WHERE zoom_level = " + String.valueOf(z) + " AND tile_column = " + String.valueOf(x) + " AND tile_row = " + String.valueOf(y);
            try {
                SQLiteDatabase db = getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                Tiles entity = null;
                if (cursor.moveToFirst()) {
                    do {
                        entity = new Tiles(cursor.getLong(0),cursor.getInt(1),cursor.getLong(2),cursor.getLong(3),cursor.getBlob(4));
                        entities.add(entity);
                    } while (cursor.moveToNext());
                }
                return entities;
            }catch (Exception e){
                Log.d("DB_MAP_TILES",e.getMessage());
                return null;
            }
        }
    }
}

