package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.search.Search;

public class DisplayByDistance implements DisplayInterface {

	@Override
	public void display(PointT location, double radius) {
		ArrayList<EarthquakeT> earthquakeList = Search.searchEarthquakeInCircle(location, radius);
		sortByDistance(location, earthquakeList);
		for(EarthquakeT eq: earthquakeList) {
			System.out.println("" + location.distanceTo(eq.getPointT()) +", "+ eq.getMag() +", "+ eq.getDate() +", " +eq.getPlace());
		}		
		
	}
	
	private void sortByDistance(PointT location, ArrayList<EarthquakeT> earthquakeList) {
//		double distance= location.distanceTo(earthquakeList.get(0).getPointT());
		for(int i = 0 ; i < earthquakeList.size(); i++) {
			for(int j = i; j > 0; j -- ) {
				if(location.distanceTo(earthquakeList.get(j - 1).getPointT()) < location.distanceTo(earthquakeList.get(j).getPointT())) {
					swap(earthquakeList, j, j - 1);
				}
			}
		}		
	}
	
	private static void swap(ArrayList<EarthquakeT> eqList, int i, int j) {
		EarthquakeT temp = eqList.get(i);
		eqList.set(i,  eqList.get(j));
		eqList.set(j, temp);
	}	

}
