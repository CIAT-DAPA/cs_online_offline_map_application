package org.cgiar.ciat.hybridmapsandroid.models;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public abstract class DBBase extends SQLiteOpenHelper {

    /* Constants */
    // database version

    /* Member class */
    private  String dbName;
    private Context context;
    private int dbVersion;

    /*Properties*/

    public int getDbVersion() {
        return dbVersion;
    }

    public String getDbName() {
        return dbName;
    }

    public Context getContext() {
        return context;
    }


    /* Methods */

    /**
     * Method Construct
     *
     * @param context
     */
    public DBBase(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
        this.context=context;
        this.dbName=dbName;
        this.dbVersion=dbVersion;
        try {
            createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Creates an empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = databaseExists();

        if (dbExist) {
            // do nothing , database already exists
        } else {

            /**
             * By calling this method and empty database will be created into
             * the default system path of your application so we are gonna be
             * able to overwrite that database with our database.
             */
            this.getReadableDatabase();

            // release all opened database objects
            // if you omit this line you will get a "no such table exception"
            // and
            // the application will crash ONLY in the first run.
            this.close();

            try {

                copyDataBase();
            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean databaseExists() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = getDatabasePath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database does not exist yet.
            Log.d("DB", e.getMessage());
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transferring bytestream.
     */
    private void copyDataBase() throws IOException {
        Log.d("DB", "copyDatabase()");

        // Open your local db as the input stream
        InputStream myInput = getContext().getAssets().open(getDbName());

        // Path to the just created empty db
        String outFileName = getDatabasePath();

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        SQLiteDatabase checkDB = null; // get a reference to the db.

        try {

            checkDB = SQLiteDatabase.openDatabase(getDatabasePath(), null,
                    SQLiteDatabase.OPEN_READWRITE);

            // once the db has been copied, set the new version..
            checkDB.setVersion(dbVersion);
            checkDB.close();
        } catch (SQLiteException e) {
            // database does?t exist yet.
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    /**
     * Get absolute path to database file. The Android's default system path of
     * your application database is /data/data/&ltpackage
     * name&gt/databases/&ltdatabase name&gt
     *
     * @return path to database file
     */
    private String getDatabasePath() {
        // The Android's default system path of your application database.
        // /data/data/<package name>/databases/<databasename>
        return getContext().getFilesDir().getParentFile().getAbsolutePath() + "/databases/" +  getDbName();
    }
}
