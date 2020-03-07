package cas.XB3.earthquake.sort;



import java.util.ArrayList;

import cas.XB3.earthquake.collection.EarthquakeT;

public class SortEarthquakeListByMagnitude {
	private static ArrayList<EarthquakeT> aux;
	
	
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
	public static void InsertionSort(ArrayList<EarthquakeT> eqList) {
		for(int i = 1; i < eqList.size(); i ++) {
			for(int j = i; j > 0; j --) {
				if(less(eqList.get(j), eqList.get(j - 1))) {
					swap(eqList, j, j - 1);
				}
			}			
		}	
	
	}
	
	
	//sort a list of Earthquake using mergesort
	public static void mergeSort(ArrayList<EarthquakeT> eqList) {
		aux = new ArrayList<>(); // Allocate space just once.
		mergeSort(eqList, 0, eqList.size() - 1);
	}

	private static void mergeSort(ArrayList<EarthquakeT> eqList, int lo, int hi) { // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(eqList, lo, mid); // Sort left half.
		mergeSort(eqList, mid + 1, hi); // Sort right half.
		merge(eqList, lo, mid, hi); // Merge results (code on page 271).
	}
	
	static private void merge(ArrayList<EarthquakeT> eqList, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
			aux.add(eqList.get(i));
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid)
				eqList.add(k, aux.get(j++));
			else if (j > hi)
				eqList.add(k, aux.get(i++));
			else if (less(aux.get(j),aux.get(i)))
				eqList.add(k, aux.get(j++));		
			else
				eqList.add(k, aux.get(i++));

	}
	
	
	//sort a list of Earthquake using quick sort
	public static void quickSort(ArrayList<EarthquakeT> eqList) {
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
			if (less(eqList.get(j), key)) {
				i++;
				swap(eqList, i, j);
			}
		}
		i++;
		swap(eqList, high, i);
		return i;
	}
}
