package org.cgiar.ciat.hybridmapsandroid.tools.map;

/**
 * Created by HSOTELO on 1/20/2016.
 */
public class Marker {
    /* Members class */
    private LatLon position;
    private String title;
    private String content;

    /* Properties */

    public LatLon getPosition() {
        return position;
    }

    public void setPosition(LatLon position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /* Methods */

    /**
     * Method Constuct
     * @param position
     * @param title
     * @param content
     */
    public Marker(LatLon position, String title, String content) {
        this.position = position;
        this.title = title;
        this.content = content;
    }

    /**
     * Method Construct
     * @param position
     * @param title
     */
    public Marker(LatLon position, String title) {
        this.position = position;
        this.title = title;
    }

    /**
     * Method Construct
     * @param position
     */
    public Marker(LatLon position) {
        this.position = position;
    }

    /**
     * Method that generate a string with the information in format to GeoJson
     * @return
     */
    public String toGeoJsonFeature(){
        return "{type: \"Feature\"," +
                "properties: {" +
                "name: \"" + getTitle() + "\"," +
                "amenity: \"" + getContent() + "\"," +
                "popupContent: \"" + getContent() + "\"" +
                "}," +
                "geometry: {" +
                "type: \"Point\"," +
                "coordinates: [" + getPosition().toString()  + "]" +
                "}" +
                "}";
    }
}
