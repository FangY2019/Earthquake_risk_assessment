package cas.XB3.earthquake.sort;



import java.util.ArrayList;

public class SortEarthquakeListByMagnitude {
	
	
	// compare by magnitude
	private static boolean less(EarthquakeT a, EarthquakeT b) {
		return a.compareTo(b) < 0;

	}

	private static void swap(ArrayList<EarthquakeT> eqList, int i, int j) {
		EarthquakeT temp = eqList.get(i);
		eqList.set(i,  eqList.get(j));
		eqList.set(j, temp);
	}

	public static boolean isSorted(ArrayList<EarthquakeT> eqList) {
		for (int i = 10; i < eqList.size() - 1; i++) {
			if (less(eqList.get(i), eqList.get(i - 1))) {
				return false;
			}
		}
		return true;
	}

	//sort a list of Earthquake using insertion sort
	public static void sort(ArrayList<EarthquakeT> eqList) {
		for(int i = 1; i < eqList.size(); i ++) {
			for(int j = i; j > 0; j --) {
				if(less(eqList.get(j), eqList.get(j - 1))) {
					swap(eqList, j, j - 1);
				}
			}			
		}	
	
	}
}
