package cas.XB3.earthquake.search;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.EarthquakeT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.reader.CSVreader;


public class ExperimentsSearch {
	public static void main(String[] args) {

		EarthquakeBag<EarthquakeT> Earthquakebag = new EarthquakeBag<EarthquakeT>();
		RedBlackBST<Double, EarthquakeT> earthquakeTree = new RedBlackBST<Double, EarthquakeT>();
		CSVreader.readEarthquakes("./eqarchive-en.csv", Earthquakebag);
		CSVreader.readEarthquakesBST("./eqarchive-en.csv", earthquakeTree);
		PointT location = new PointT(46, -70);


		int[] radius = { 10, 100, 200, 300,1000, 4000};
		
		System.out.println("execution time of earthquake bag");
		for (int i = 0; i < radius.length; i++) {
			double startTime = System.currentTimeMillis();
			ArrayList<EarthquakeT> eqList = SearchEarthquakes.searchInEarthquakeBag(Earthquakebag, location, radius[i]);
			double endTime = System.currentTimeMillis();
			System.out.printf("Radius: %d, Total execution time: %.5f, size of the list: %d \n", radius[i], (endTime - startTime) / 1000.0, eqList.size());
		}		

		System.out.println("\n execution time of earthquake tree");
		for (int i = 0; i < radius.length; i++) {
			double startTime = System.currentTimeMillis();
			ArrayList<EarthquakeT> eqList2 = SearchEarthquakes.searchEarthquakeInCircle(earthquakeTree, location, radius[i]);
			double endTime = System.currentTimeMillis();
			System.out.printf("Radius: %d, Total execution time: %.5f , size of the list: %d\n", radius[i], (endTime - startTime) / 1000.0, eqList2.size());
		}		
		
	}	

}
