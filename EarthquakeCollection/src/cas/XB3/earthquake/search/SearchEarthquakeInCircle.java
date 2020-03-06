package cas.XB3.earthquake.search;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.Database;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;

public class SearchEarthquakeInCircle {
	
	//get the list of earthquake which are in the circle of the given PointT and radius
	public static ArrayList<EarthquakeT> searchEarthquakeInCircle(PointT location, double radius){
		Database.init(); //load the earthquake database
		ArrayList<EarthquakeT> earthquakeList = new ArrayList<>();
		for(EarthquakeT earthquake: Database.EarthquakeBag) {
			if(location.distanceTo(earthquake.getPointT()) <= radius) {
				earthquakeList.add(earthquake);
			}
			
		}
		return earthquakeList;
	}
	
	public static int getFrequency(ArrayList<EarthquakeT> earthquakeList) {
		return earthquakeList.size();
	}
	
	public static int getAverageMangenitude(ArrayList<EarthquakeT> earthquakeList) {
		double sum = 0;
		for(EarthquakeT earthquake: earthquakeList) {
			sum += earthquake.getMag();
		}
		return (int) Math.round( sum/(double)getFrequency(earthquakeList));
	}
	



}
