package cas.XB3.earthquake.collection;

import cas.XB3.earthquake.ADT.*;
import java.time.LocalDateTime;

public class EarthquakeT {
    private LocalDateTime date;
    private String place;
    private double lat;
    private double lng;
    private double dph;
    private double mag;
    private MagType magnitudeType;
    private ColorRating color;

    public EarthquakeT(String place, LocalDateTime date, double lat, double lng, double dph, double mag, MagType mgT, ColorRating color){
    	this.lat = lat;
    	this.lng = lng;
    	this.place = place;
    	this.date = date;
        this.dph = dph;
        this.mag = mag;
        this.magnitudeType = mgT;
        this.color = color;
    }

    public String getPlace() {
        return place;
    }

    public PointT getPointT() {
    	return new PointT(lat, lng);
    }

    public double getMag() {
        return mag;
    }
    
    public double dph() {
    	return dph;
    }
    
    public MagType getMagitudeType() {
    	return magnitudeType;
    }
    
    public LocalDateTime getDate() {
    	return date;
    }

    public ColorRating getColor(){
        return color;
    }

    /*
    ColorRating is assigned based on 6 earthquake magnitude classes; The magnitude is in brackets below:
    RED: Great (8 or more)
         Major (7 - 7.9)
         Strong (6 - 6.9)
         Moderate (5 - 5.9)
         Light (4 - 4.9)
    PURPLE: Minor (3 - 3.9)
    */
    enum ColorRating{
        NOCOLOR, ZERO, PURPLE, BLUE, GREEN, YELLOW, ORANGE, RED
    }
    enum MagType{
        M5, mb, MB, Mb, MC, Mc, mc, ML, MLSn, MN, MS, MW, Ms, Mw, BLANK
    }
}
