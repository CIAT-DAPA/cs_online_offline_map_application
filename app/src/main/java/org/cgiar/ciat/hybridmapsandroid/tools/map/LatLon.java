package org.cgiar.ciat.hybridmapsandroid.tools.map;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public class LatLon {

    /* Member Class */
    private double lat;
    private double lon;

    /* Properties */
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

    /* Methods */

    /**
     * Method Construct
     * @param lat
     * @param lon
     */
    public LatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString(){
        return String.valueOf(lat).replaceAll(",",".") + "," + String.valueOf(lon).replaceAll(",",".");
    }
}
