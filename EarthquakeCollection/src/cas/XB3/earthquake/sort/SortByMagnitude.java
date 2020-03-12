package cas.XB3.earthquake.sort;



import java.util.ArrayList;

import cas.XB3.earthquake.collection.EarthquakeT;

public class SortByMagnitude {
	private static ArrayList<EarthquakeT> aux;
	
	
	// compare by magnitude



	
	
	//sort a list of Earthquake using mergesort
	public static void mergeSort(ArrayList<EarthquakeT> eqList) {
		aux = new ArrayList<>(eqList.size()); // Allocate space just once.
		mergeSort(eqList, 0, eqList.size() - 1);
	}

	private static void mergeSort(ArrayList<EarthquakeT> eqList, int lo, int hi) { // Sort a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		mergeSort(eqList, lo, mid); 
		mergeSort(eqList, mid + 1, hi);
		merge(eqList, lo, mid, hi);
	}
	
	static private void merge(ArrayList<EarthquakeT> eqList, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++)
			aux.add(k, eqList.get(k));
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				eqList.set(k, aux.get(j++));
			else if (j > hi)
				eqList.set(k, aux.get(i++));
			else if (less(aux.get(j),aux.get(i)))
				eqList.set(k, aux.get(i++));		
			else
				eqList.set(k, aux.get(j++));

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

	public static boolean isSorted(ArrayList<EarthquakeT> eqList) {
		for (int i = 10; i < eqList.size() - 1; i++) {
			if (less(eqList.get(i), eqList.get(i - 1))) {
				return false;
			}
		}
		return true;
	}
}
