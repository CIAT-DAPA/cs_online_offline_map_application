package org.cgiar.ciat.hybridmapsandroid.models.maps.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.cgiar.ciat.hybridmapsandroid.models.DBBase;

import java.util.LinkedList;
import java.util.List;
/**
 * Created by HSOTELO on 1/20/2016.
 */
public class MapDB  extends DBBase {

    /* Constants */
    // name of database
    private static final String DB_NAME = "points.gpkg";
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

    public class PointsEntity {
        public List all() {
            List points = new LinkedList();
            String query = "SELECT fid, id, name, lat, lon FROM " + Points.TABLE_NAME;
            try {

                SQLiteDatabase db = getWritableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                Points point = null;
                if (cursor.moveToFirst()) {
                    do {
                        point = new Points(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4));
                        points.add(point);
                    } while (cursor.moveToNext());
                }
                return points;
            }catch (Exception e){
                Log.d("DB_POINTS_POINTS",e.getMessage());
                return null;
            }
        }
    }
}

