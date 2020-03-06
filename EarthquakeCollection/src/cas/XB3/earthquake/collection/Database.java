package cas.XB3.earthquake.collection;

public class Database {
    public static final EarthquakeBag<EarthquakeT> EarthquakeBag = new EarthquakeBag<EarthquakeT>();
    public static final GeoCollection<GeoLoc> GeoCollection = new GeoCollection<GeoLoc>();

    public static void init(){
        CSVreader.readEarthquakes("./eqarchive-en.csv", EarthquakeBag);
        CSVreader.readPopulation("./T301EN.CSV", GeoCollection);
    }
}
