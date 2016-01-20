package org.cgiar.ciat.hybridmapsandroid.models.maps.source;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public class Tiles {

    public static final String TABLE_NAME = "tiles";

    /*Members Class*/
    private long id;
    private int zoom_level;
    private long tile_column;
    private long tile_row;
    private String tile_data;

    /*Properties*/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getZoom_level() {
        return zoom_level;
    }

    public void setZoom_level(int zoom_level) {
        this.zoom_level = zoom_level;
    }

    public long getTile_column() {
        return tile_column;
    }

    public void setTile_column(long tile_column) {
        this.tile_column = tile_column;
    }

    public long getTile_row() {
        return tile_row;
    }

    public void setTile_row(long tile_row) {
        this.tile_row = tile_row;
    }

    public String getTile_data() {
        return tile_data;
    }

    public void setTile_data(String tile_data) {
        this.tile_data = tile_data;
    }

    /* Methods */

    /**
     * Method Construct
     */
    public Tiles() {
    }

    /**
     * Method Construct
     * @param id
     * @param zoom_level
     * @param tile_column
     * @param tile_row
     * @param tile_data
     */
    public Tiles(long id, int zoom_level, long tile_column, long tile_row, String tile_data) {
        this.id = id;
        this.zoom_level = zoom_level;
        this.tile_column = tile_column;
        this.tile_row = tile_row;
        this.tile_data = tile_data;
    }
}
