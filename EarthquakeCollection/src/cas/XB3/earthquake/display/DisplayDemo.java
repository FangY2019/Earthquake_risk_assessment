package cas.XB3.earthquake.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cas.XB3.earthquake.ADT.CityPostT;
import cas.XB3.earthquake.ADT.CityT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.Graph.CityGraph;
import cas.XB3.earthquake.Graph.Edge;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.GeoCollection;
import cas.XB3.earthquake.riskAssessment.RiskAssessment;
import cas.XB3.earthquake.search.SearchEarthquakes;

public class DisplayDemo {
	
    private static final EarthquakeBag<EarthquakeT> EarthquakeBag = new EarthquakeBag<EarthquakeT>();
    private static final GeoCollection GeoCollection = new GeoCollection();
    private static final ArrayList<CityPostT> cityPostList = new ArrayList<>();
    private static final CityGraph  graph = new CityGraph();
 
    public static void init(){
        CSVreader.readEarthquakes("./eqarchive-en.csv", EarthquakeBag);
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
		
//		ArrayList<CityT> list = GeoCollection.getCityArrayList("2");
//		System.out.println(list.size());
//		System.out.println(GeoCollection.getCityHashMap().keySet().contains("2"));

		
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter the latidude");
		double lat = input.nextDouble();
		System.out.println("Please enter the longitude:");
		double longi = input.nextDouble();
		System.out.println("Please enter the radius:");
		int radius = input.nextInt();
		System.out.println("Please choose the display option:");
		int choice;

		PointT location = new PointT(lat, longi);
		ArrayList<EarthquakeT> eqList = SearchEarthquakes.searchEarthquakeInCircle(EarthquakeBag, location, radius);
		DisplayManager displaymanager = new DisplayManager(eqList, location);
		do {
			System.out.println("Display by Magnitude, choose 1; Display by time, choose 2; Display risk rating, choose 3; exist, choose 0");
			choice = input.nextInt();
			if (choice == 1) {
				displaymanager.display(new DispalyByMagnitude());
			} else if (choice == 2) {
				displaymanager.display(new DisplayByDistance());
			}else if (choice == 3) {
				RiskAssessment riskAssessment = new RiskAssessment(EarthquakeBag, location);
				int rating = riskAssessment.getRisk();
				for (CityPostT cityFrom : cityPostList) {
					if(cityFrom.getCityName().equals(riskAssessment.getCity())) {
						for (CityPostT cityTo : cityPostList) {
							if (!cityFrom.getCityName().equals(cityTo.getCityName())) {
								int distance = (int) cityFrom.getPoint().distanceTo(cityTo.getPoint());
								if(distance < 200) {
									Edge e = new Edge(cityFrom.getCityName(), cityTo.getCityName(), distance);
									graph.addEdge(e);
								}								
							}
						}
					}
					
				}
				System.out.printf("The risk rating for the location (%.2f , %.2f) is : % d\n" , location.getLat(), location.getLong(), rating);
				if(riskAssessment.nearestLowerRiskCity(graph) != null) {
					System.out.printf("The nearest lower risk city is: %s\n\n\n", riskAssessment.nearestLowerRiskCity(graph));
				}else {
					System.out.printf("There is no lower risk city in the range of 200 kilometers\n\n\n");
				}
				
			}
			
		}while(choice != 0);
		
		input.close();
	}
	
  
	
}

