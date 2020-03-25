package cas.XB3.earthquake.search;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.RedBlackBST;

public class SearchEarthquakes {

	// get the list of earthquake which are in the circle of the given PointT and
	// radius
	public static ArrayList<EarthquakeT> searchEarthquakeInCircle(RedBlackBST<Double, EarthquakeT> bst,
			PointT location, double radius) {

		ArrayList<EarthquakeT> earthquakeList = new ArrayList<>();
		// the range of latitude for the given point and radius
		double[] latRange = location.latFilter(radius);
		// gets the keys between min latitude and max latitude and gets the earthquakeT according the keys
		for (Double latitude : bst.keys(latRange[0], latRange[1])) {
			for (EarthquakeT earthquake : bst.get(latitude)) {
				if (location.distanceTo(earthquake.getPointT()) <= radius) {
					earthquakeList.add(earthquake);
				}
			}
		}
		return earthquakeList;
	}

}
