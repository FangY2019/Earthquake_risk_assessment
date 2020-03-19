package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.sort.Sort;


public class DisplayByDistance implements DisplayInterface {

	@Override
	public void display(ArrayList<EarthquakeT> earthquakeList, PointT location) {
		Sort.sortByDistance(location, earthquakeList);
		if (earthquakeList.size() == 0) System.out.println("There's no earthquake!");
		else {
		System.out.println("Distance     Magnitude         Date                 City");
		for(EarthquakeT eq: earthquakeList) {
			System.out.println("" + Math.round(location.distanceTo(eq.getPointT())* 10)/10.0 +", "+ eq.getMag() +", "+ eq.getDate().getYear() +", " +eq.getPlace());
		}	
		}
		
	}
	
	

}
