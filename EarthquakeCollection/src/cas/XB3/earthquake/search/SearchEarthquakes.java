package cas.XB3.earthquake.search;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;

public class SearchEarthquakes {
	
	//get the list of earthquake which are in the circle of the given PointT and radius
	public static ArrayList<EarthquakeT> searchEarthquakeInCircle(EarthquakeBag <EarthquakeT> Earthquakebag,PointT location, double radius){

		ArrayList<EarthquakeT> earthquakeList = new ArrayList<>();
		for(EarthquakeT earthquake: Earthquakebag) {
			if(location.distanceTo(earthquake.getPointT()) <= radius) {
				earthquakeList.add(earthquake);
			}			
		}
		return earthquakeList;
	}
	
	
}
