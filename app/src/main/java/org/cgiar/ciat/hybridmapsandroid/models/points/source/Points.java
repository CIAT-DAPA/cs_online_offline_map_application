package org.cgiar.ciat.hybridmapsandroid.models.points.source;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public class Points {

    public static final String TABLE_NAME = "Points";

    /*Members Class*/
    private int fid;
    private int id;
    private String name;
    private double lat;
    private double lon;


    /*Properties*/

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    /*Methods*/

    /**
     * Method Construct
     */
    public Points(){

    }

    /**
     * Method Construct
     * @param fid
     * @param id
     * @param name
     * @param lat
     * @param lon
     */
    public Points(int fid, int id, String name,  double lat, double lon) {
        this.fid = fid;
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString(){
        return "Point [fid=" + String.valueOf(getFid()) + ", id=" + String.valueOf(getId()) + ", name=" + getName() + ", lat=" + String.valueOf(getLat()) + ", lon=" + String.valueOf(getLon())  + "]";
    }
}
