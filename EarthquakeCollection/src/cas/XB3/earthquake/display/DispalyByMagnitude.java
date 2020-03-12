package cas.XB3.earthquake.display;
import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.search.Search;
import cas.XB3.earthquake.sort.SortByMagnitude;


public class DispalyByMagnitude implements DisplayInterface{
	
	public void display(PointT location, double radius) {
		ArrayList<EarthquakeT> earthquakeList = Search.searchEarthquakeInCircle(location, radius);
		SortByMagnitude.mergeSort(earthquakeList);
		for(EarthquakeT eq: earthquakeList) {
			System.out.println("" + eq.getMag() +", "+ eq.getDate() +", " +eq.getPlace());
		}
		
		
	}

}
