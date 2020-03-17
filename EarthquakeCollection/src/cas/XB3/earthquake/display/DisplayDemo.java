package cas.XB3.earthquake.display;

import java.util.ArrayList;
import java.util.Scanner;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.GeoCollection;
import cas.XB3.earthquake.riskAssessment.RiskAssessment;
import cas.XB3.earthquake.search.SearchEarthquakes;

public class DisplayDemo {
	
    public static final EarthquakeBag<EarthquakeT> EarthquakeBag = new EarthquakeBag<EarthquakeT>();
    public static final GeoCollection GeoCollection = new GeoCollection();
    
	public static void main(String[] args) {
		init();
		
		
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
				int rating = RiskAssessment.calculateRiskRating(EarthquakeBag, location);
				System.out.printf("The risk for the location (%.2f , %.2f) is : % d\n" , location.getLat(), location.getLong(), rating);
			}
			
		}while(choice != 0);
		
		input.close();
	}
	
    public static void init(){
        CSVreader.readEarthquakes("./eqarchive-en.csv", EarthquakeBag);
        CSVreader.readPopulation("./T301EN.CSV", GeoCollection);

    }   
	
}

