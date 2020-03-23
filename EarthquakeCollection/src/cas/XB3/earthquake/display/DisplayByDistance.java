package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.sort.Sort;

public class DisplayByDistance implements DisplayInterface {

	@Override
	public void display(ArrayList<EarthquakeT> earthquakeList, PointT location) {
		Sort.sortByDistance(location, earthquakeList);
		if (earthquakeList.size() == 0)
			System.out.println("There's no earthquake!");
		else {
			System.out.printf("%-15s%-15s%-15s%-30s\n","Distance","Magnitude", "Date", "City");
			for (EarthquakeT eq : earthquakeList) {
				System.out.printf("%-15.1f%-15.1f%-15d%-30s\n", location.distanceTo(eq.getPointT()), eq.getMag()
						, eq.getDate().getYear(), eq.getPlace());
			}
		}

	}

}
