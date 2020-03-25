
import java.util.ArrayList;

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

public class TestRiskAssessment {
	private static final RedBlackBST<Double, EarthquakeT> earthquakeTree = new RedBlackBST<Double, EarthquakeT>();
	private static final GeoCollection GeoCollection = new GeoCollection();
	private static final ArrayList<CityPostT> cityPostList = new ArrayList<>();
	private static final CityGraph graph = new CityGraph();
	public static void main(String[] args) {

		CSVreader.readEarthquakesBST("./eqarchive-en.csv", earthquakeTree);
		CSVreader.readPopulation("./T301EN.CSV", GeoCollection);
		CSVreader.readCityPosition("./City_Coordinates.CSV", cityPostList);
		
		//print the population density for city Victoria
		System.out.println("population density of Victoria in the  GeoCollection:");
		for(CityT city: GeoCollection.getCityArrayList("V")) {
			if(city.getCityName().equals("Victoria")) {
				System.out.println("Province: " + city.getProvince() + " population density: " + city.getPopDensity() );				
			}			
		}
		System.out.println();	
		

		
		
//		PointT location = new PointT(48.5, -123.1);   //SAN JUAN ISLAND
//		PointT location = new PointT(48.506, -123.471);  //victoria
//		PointT location = new PointT(100, -130);  //
//		PointT location = new PointT(50, -121);		
//		PointT location = new PointT(43.65107, -79.347015);  //Toronto
//		PointT location = new PointT(43.837208, -79.508278); //Vaughan
//		PointT location = new PointT(45.612499, -73.707092); // Montreal
		
		
		PointT location = new PointT(48.016, -122.593);  //victoria line 10		
		
		ArrayList<EarthquakeT> eqList = SearchEarthquakes.searchEarthquakeInCircle(earthquakeTree, location, 100);
		
		RiskAssessment riskAssessment = new RiskAssessment(earthquakeTree, location);

	
		System.out.println("city name: "+ riskAssessment.getCity());
		System.out.println("frequency: "+ riskAssessment.getFrequency());
		System.out.println("average mag: "+ riskAssessment.getMag());
		System.out.println("population density: "+ riskAssessment.getPoplationDensity());
		System.out.println("risk " + riskAssessment.getRisk());	
		

		
		System.out.println("population rating = " + riskAssessment.populationdensityRating(riskAssessment.getPoplationDensity()));
		System.out.println("magnitude rating = " + riskAssessment.magnitudeRating(riskAssessment.getMag()));
		System.out.println("frequency rating= " + riskAssessment.magnitudeRating(riskAssessment.getFrequency()));	
		


	}

}
