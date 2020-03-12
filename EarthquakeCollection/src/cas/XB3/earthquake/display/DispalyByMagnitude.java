package cas.XB3.earthquake.display;
import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.sort.Sort;


public class DispalyByMagnitude implements DisplayInterface{
	
	public void display(ArrayList<EarthquakeT> earthquakeList, PointT location) {		
		Sort.sortByMagnitude(earthquakeList);
		System.out.println("Magnitude         Date                 City");
		for(EarthquakeT eq: earthquakeList) {
			System.out.println("" + eq.getMag() +", "+ eq.getDate().getYear() +", " +eq.getPlace());
		}			
	}

}
