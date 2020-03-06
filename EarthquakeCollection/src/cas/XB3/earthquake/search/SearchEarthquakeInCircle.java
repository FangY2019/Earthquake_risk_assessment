package cas.XB3.earthquake.search;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;

public class SearchEarthquakeInCircle {
	private ArrayList<EarthquakeT> earthquakeList = new ArrayList<>();
	
	//load the earthquake database
	EarthquakeBag <EarthquakeT> Earthquakebag = new EarthquakeBag<EarthquakeT>();
	//readEarthquakes("./eqarchive-en.csv", Earthquakebag);	
	
	//get the list of earthquake which are in the circle of the given PointT and radius
	public ArrayList<EarthquakeT> searchEarthquakeInCircle(PointT location, double radius){
		for(EarthquakeT earthquake: Earthquakebag) {
			if(location.distanceTo(earthquake.getPointT()) <= radius) {
				earthquakeList.add(earthquake);
			}
			
		}
	}


}
