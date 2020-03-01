package cas.XB3.earthquake.collection;

import java.util.Date;

public class EarthquakeT {
    private String earthquakePlace;
    private Date date;
    private double lat;
    private double lng;
    private double depth;
    private double magnitude;
    private ColorRating color;
    private MagType magnitudeType;

    public EarthquakeT(String place, ColorRating color, double magnitude){
        this.earthquakePlace = place;
        this.color = color;
        this.magnitude = magnitude;
    }

    public String getEarthquakePlace() {
        return earthquakePlace;
    }

    public ColorRating getColor(){
        return color;
    }

    /*
    ColorRating is assign based on 6 earthquake magnitude classes; Magnitude is in brackets below:
    RED: Great (8 or more)
    Major (7 - 7.9)
    Strong (6 - 6.9)
    Moderate (5 - 5.9)
    Light (4 - 4.9)
    Minor (3 - 3.9)
    */
    enum ColorRating{
        PURPLE, BLUE, GREEN, YELLOW, ORANGE, RED
    }
    enum MagType{
        M5, MB, MC, ML, MLSn, MN, MS, Mw, BLANK
    }
}
