package cas.XB3.earthquake.search;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;

public class Search {
	
	//get the list of earthquake which are in the circle of the given PointT and radius
	public static ArrayList<EarthquakeT> searchEarthquakeInCircle(PointT location, double radius){
		EarthquakeBag <EarthquakeT> Earthquakebag = new EarthquakeBag<EarthquakeT>();
		CSVreader.readEarthquakes("./eqarchive-en.csv", Earthquakebag);
		ArrayList<EarthquakeT> earthquakeList = new ArrayList<>();
		for(EarthquakeT earthquake: Earthquakebag) {
			if(location.distanceTo(earthquake.getPointT()) <= radius) {
				earthquakeList.add(earthquake);
			}			
		}
		return earthquakeList;
	}
	
	public static int getFrequency(ArrayList<EarthquakeT> earthquakeList) {
		return earthquakeList.size();
	}
	
	public static double getAverageMangenitude(ArrayList<EarthquakeT> earthquakeList) {
		double sum = 0;
		for(EarthquakeT earthquake: earthquakeList) {
			sum += earthquake.getMag();
		}
		return (double) (sum/(double)getFrequency(earthquakeList));
	}	
}
