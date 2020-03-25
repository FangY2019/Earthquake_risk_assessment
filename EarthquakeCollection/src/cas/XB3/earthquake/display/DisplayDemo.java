package cas.XB3.earthquake.display;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import cas.XB3.earthquake.ADT.CityPostT;
import cas.XB3.earthquake.ADT.CityT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.Graph.CityGraph;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.GeoCollection;
import cas.XB3.earthquake.collection.RedBlackBST;
import cas.XB3.earthquake.riskAssessment.RiskAssessment;
import cas.XB3.earthquake.search.SearchEarthquakes;

public class DisplayDemo {

	private static final RedBlackBST<Double, EarthquakeT> earthquakeTree = new RedBlackBST<Double, EarthquakeT>();
	private static final GeoCollection GeoCollection = new GeoCollection();
	private static final ArrayList<CityPostT> cityPostList = new ArrayList<>();
	private static final CityGraph graph = new CityGraph();
	

	public static void init() {
		CSVreader.readEarthquakesBST("./eqarchive-en.csv",earthquakeTree);
		CSVreader.readPopulation("./T301EN.CSV", GeoCollection);
		CSVreader.readCityPosition("./City_Coordinates.CSV", cityPostList);
//		for(CityPostT cityFrom: cityPostList) {
//			for(CityPostT cityTo: cityPostList) {
//				if(!cityFrom.getCityName().equals(cityTo.getCityName())) {
//					int distance = (int) cityFrom.getPoint().distanceTo(cityTo.getPoint());
//					Edge e = new Edge(cityFrom.getCityName(), cityTo.getCityName(), distance);
//					graph.addEdge(e);
//				}				
//			}
//		}
	}

	public static void main(String[] args) {
		init();	
		
		try {
			File file = new File("./input.txt");
			Scanner input = new Scanner(file);
//			Scanner input = new Scanner(System.in);

			System.out.println("Please enter the latidude, the number should be between 41.0 to 84.0: \n");
			double lat = input.nextDouble();
			System.out.println("Please enter the longitude, the number should be between -150.0 to -42.0:\n");
			double longi = input.nextDouble();
			System.out.println("Please enter the radius with the unit of kilometer:\n");
			int radius = input.nextInt();			
			int choice = -1;
			

			

			PointT location = new PointT(lat, longi);
			ArrayList<EarthquakeT> eqList = SearchEarthquakes.searchEarthquakeInCircle(earthquakeTree, location, radius);
			DisplayManager displaymanager = new DisplayManager(eqList, location);
			
			while (choice != 0) {
				System.out.println("Please choose the display option:");
				System.out.println(
						"Display by Magnitude, choose 1; Display by time, choose 2; Display risk rating, choose 3; exit, choose 0\n");
				choice = input.nextInt();
				if (choice == 1) {
					displaymanager.display(new DispalyByMagnitude());
				} else if (choice == 2) {
					displaymanager.display(new DisplayByDistance());
				} else if (choice == 3) {
					displaymanager.showRisk(earthquakeTree, location, cityPostList, graph);
				}
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
