package cas.XB3.earthquake.sort;



import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;

public class Sort {

	// compare by magnitude

	public static void sortByDistance(PointT location, ArrayList<EarthquakeT> eqList) {
//		double distance= location.distanceTo(earthquakeList.get(0).getPointT());
		for(int i = 0 ; i < eqList.size(); i++) {
			for(int j = 1; j < i; j ++ ) {
				if(location.distanceTo(eqList.get(j - 1).getPointT()) > location.distanceTo(eqList.get(j).getPointT())) {
					swap(eqList, j, j - 1);
				}
			}
		}		
	}
	
	
	//sort a list of Earthquake using quick sort
	public static void sortByMagnitude(ArrayList<EarthquakeT> eqList) {
		quickSort(eqList, 0, eqList.size() - 1);
	}

	private static void quickSort(ArrayList<EarthquakeT> eqList, int low, int high) {
		if (low >= high) {
			return;
		}
		int pi = partition(eqList, low, high);
		quickSort(eqList, low, pi - 1);
		quickSort(eqList, pi + 1, high);
	}
	
	
    //pick last element as pivot (implemented below)
	private static int partition(ArrayList<EarthquakeT> eqList, int low, int high) {
		EarthquakeT key = eqList.get(high);
		int i = low - 1;
		for (int j = low; j < high; j++) {
			if (less(key, eqList.get(j))) {
				i++;
				swap(eqList, i, j);
			}
		}
		i++;
		swap(eqList, high, i);
		return i;
	}
	
	
	private static boolean less(EarthquakeT a, EarthquakeT b) {
		return a.compareTo(b) < 0;

	}

	private static void swap(ArrayList<EarthquakeT> eqList, int i, int j) {
		EarthquakeT temp = eqList.get(i);
		eqList.set(i,  eqList.get(j));
		eqList.set(j, temp);
	}

//	public static boolean isSorted(ArrayList<EarthquakeT> eqList) {
//		for (int i = 10; i < eqList.size() - 1; i++) {
//			if (less(eqList.get(i), eqList.get(i - 1))) {
//				return false;
//			}
//		}
//		return true;
//	}
}
