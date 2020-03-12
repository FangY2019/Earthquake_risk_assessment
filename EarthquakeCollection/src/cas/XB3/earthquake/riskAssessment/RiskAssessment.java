package cas.XB3.earthquake.riskAssessment;
/**
 * Assuming the future earthquake risk is determined by three factors:past earthquake frequency, magnitude and population density 
 * 
 */

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.CityT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.search.Search;
import cas.XB3.earthquake.search.SearchEarthquakeInCircle;

public class RiskAssessment {
	
	private int rate;
	
	public RiskAssessment(PointT location) {
		this.rate = calculateRiskRating(location);
	}
	
	private int calculateRiskRating(PointT location) {
		String cityName;
		int rating;

		ArrayList<ArrayList<EarthquakeT>> earthquakeLists = new ArrayList<>();
		earthquakeLists.add(Search.searchEarthquakeInCircle(location, 100000));
		earthquakeLists.add(Search.searchEarthquakeInCircle(location, 200000));
		earthquakeLists.add(Search.searchEarthquakeInCircle(location, 300000));
		
		

		for(int i = 0;i < earthquakeLists.size(); i ++) {
			cityName = getCityName(location, earthquakeLists.get(i));
			if(cityName != null)
				break;
		}
		
		int frequency1  = Search.getFrequency(earthquakeLists.get(0));
		int frequency2  = Search.getFrequency(earthquakeLists.get(1));
		int frequency3  = Search.getFrequency(earthquakeLists.get(2));
		double averagerMag1  = Search.getAverageMangenitude(earthquakeLists.get(0));
		double averagerMag2  = Search.getAverageMangenitude(earthquakeLists.get(1));
		double averagerMag3  = Search.getAverageMangenitude(earthquakeLists.get(2));
		
		CityT city = new CityT(cityName);
		int populationdensity  = city.getPopulation();
		
		rating = (int) Math.round(0.5 * (frequency1 + Math.pow(averagerMag1, 2)) + 0.3 * (frequency2 + Math.pow(averagerMag2, 2)) + 2 * populationdensity/ 1000)
		
		
	}
	
	
	private String getCityName(PointT location, ArrayList<EarthquakeT> earthquakeList) {
		double minDistance = Double.MAX_VALUE;
		EarthquakeT nearestEarthquake;
		for(EarthquakeT earthquake: earthquakeList ) {
			if(location.distanceTo(earthquake.getPointT()) < minDistance) {
				minDistance = location.distanceTo(earthquake.getPointT());
				nearestEarthquake = earthquake;
				
			}
		}
		if(minDistance == Double.MAX_VALUE)
			return null;
		return nearestEarthquake.getCity();
			

		
	}

	

}
