package cas.XB3.earthquake.collection;

public class GeoLoc {
    String geocode;
    String geoname;
    String CSDType;
    String province;
    int population;

    public GeoLoc(String geocode, String geoname, String CSDType, String province, int popul){
        this.geocode = geocode;
        this.geoname = geoname;
        this.CSDType = CSDType;
        this.province = province;
        this.population = popul;
    }

    public String getGeoname() {
        return geoname;
    }
}
