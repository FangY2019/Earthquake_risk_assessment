package cas.XB3.earthquake.display;
import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.search.SearchEarthquakeInCircle;
import cas.XB3.earthquake.sort.SortEarthquakeListByMagnitude;

public class DispalyByMagnitude implements DisplayInterface{
	
	public void display(PointT location, double radius) {
		ArrayList<EarthquakeT> earthquakeList = new SearchEarthquakeInCircle().searchEarthquakeInCircle(location, radius);
		SortEarthquakeListByMagnitude.sort(earthquakeList);
		for(EarthquakeT eq: earthquakeList) {
			System.out.println("" + eq.getPlace() + eq.getMag());
		}
		
		
	}

}
