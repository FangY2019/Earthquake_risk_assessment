package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.EarthquakeT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.sort.Sort;

public class DisplayByDistance implements DisplayInterface {

	@Override
	public void display(ArrayList<EarthquakeT> earthquakeList, PointT location) {
		Sort.sortByDistance(location, earthquakeList);
		if (earthquakeList.size() == 0)
			System.out.println("There's no earthquake!");
		else {
			System.out.println(
					"The earthquakes are displayed in the ascending order based on the distance from the input location:");
			System.out.printf("%-15s%-15s%-15s%-15s%-30s\n", "Distance", "Magnitude", "Earthquake class", "Date", "City");
			for (EarthquakeT eq : earthquakeList) {
				System.out.printf("%-15.1f%-15.1f%-15s%-15d%-30s\n", location.distanceTo(eq.getPointT()), eq.getMag(),eq.getColor(),
						eq.getDate().getYear(), eq.getPlace());
			}
			System.out.println();
		}

	}

}
