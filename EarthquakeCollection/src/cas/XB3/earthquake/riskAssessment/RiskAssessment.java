package cas.XB3.earthquake.riskAssessment;
/**
 * Assuming the future earthquake risk is determined by three factors:past earthquake frequency, magnitude and population density 
 * 
 */

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.CityT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeBag;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.GeoCollection;
import cas.XB3.earthquake.search.SearchEarthquakes;

public class RiskAssessment {

	public static int calculateRiskRating(EarthquakeBag <EarthquakeT> Earthquakebag, PointT location) {
		String cityName;
		int rating;
		
		//search earthquake in two circles of different range
		ArrayList<ArrayList<EarthquakeT>> earthquakeLists = new ArrayList<>();		
		earthquakeLists.add(SearchEarthquakes.searchEarthquakeInCircle(Earthquakebag,location, 100));
		earthquakeLists.add(SearchEarthquakes.searchEarthquakeInCircle(Earthquakebag,location, 200));
		
		//find the nearest city name in the smallest circle
		cityName = getCityName(location, earthquakeLists.get(0));
		
		int frequency1  = Frequency(earthquakeLists.get(0));
//		int frequency2  = Frequency(earthquakeLists.get(1));
		double averagerMag1  = AverageMangenitude(earthquakeLists.get(0));
//		double averagerMag2  = AverageMangenitude(earthquakeLists.get(1));
		
		double populationdensity  =getPopulation(cityName);
		
		rating = OverallRating(frequency1, averagerMag1, populationdensity);
		
		return rating;
	}
	
	//get the city form the nearest EarthquakeT
	public static String getCityName(PointT location, ArrayList<EarthquakeT> earthquakeList) {
		double minDistance = Double.MAX_VALUE;
		EarthquakeT nearestEarthquake = null;
		for(EarthquakeT earthquake: earthquakeList ) {
			if(location.distanceTo(earthquake.getPointT()) < minDistance) {
				minDistance = location.distanceTo(earthquake.getPointT());
				nearestEarthquake = earthquake;				
			}
		}
		if(minDistance == Double.MAX_VALUE)
			return null;
		return nearestEarthquake.getPlace();	
	}
	
	public static double getPopulation(String cityName) {
		double populationDensity = 0;
		GeoCollection GeoCollection = new GeoCollection();
		CSVreader.readPopulation("./T301EN.CSV", GeoCollection);
		ArrayList<CityT> listOfCity = GeoCollection.getCityArrayList(String.valueOf(cityName.charAt(0)));
		for(CityT city: listOfCity) {
			if(cityName.contains(city.getCityName())) {
				populationDensity = city.getPopDensity();
				break;
			}
		}
		return populationDensity;		
	}
	
	public static int Frequency(ArrayList<EarthquakeT> earthquakeList) {
		return earthquakeList.size();
	}
	
	public static double AverageMangenitude(ArrayList<EarthquakeT> earthquakeList) {
		double sum = 0;
		for(EarthquakeT earthquake: earthquakeList) {
			sum += earthquake.getMag();
		}
		return (double) (sum/(double)Frequency(earthquakeList));
	}
	
	private static int OverallRating(int frequency, double averagerMag, double populationdensity ) {
		return frequencyRating(frequency) +  magnitudeRating(averagerMag) + populationdensityRating(populationdensity);
		
	}
	
	public static int frequencyRating(int frequency) {
		int risk_frequency = 0;
		if(frequency < 1) risk_frequency = 0;
		else if(1 <= frequency && frequency < 10) risk_frequency = 1;
		else if(10 <= frequency && frequency < 100) risk_frequency = 2;
		else if(100 <= frequency && frequency < 1000) risk_frequency = 3;
		else risk_frequency = 4;		
		return risk_frequency;		
	}
	
	public static int magnitudeRating(double averagerMag) {
		int risk_averagerMag = 0;
		if(averagerMag < 1) risk_averagerMag = 0;
		else if(1 <= averagerMag && averagerMag < 2) risk_averagerMag = 1;
		else if(4 <= averagerMag && averagerMag < 6) risk_averagerMag = 2;
		else if(6 <= averagerMag && averagerMag < 7) risk_averagerMag = 3;
		else risk_averagerMag = 4;		
		return risk_averagerMag;		
	}
	
	public static int populationdensityRating(double populationdensity) {
		int risk_population = 0;
		if(populationdensity < 1000) risk_population = 0;
		else if(1000 <= populationdensity && populationdensity < 5000) risk_population = 1;
		else if(5000 <= populationdensity) risk_population = 2;
		return risk_population;
		
	}

	

}
