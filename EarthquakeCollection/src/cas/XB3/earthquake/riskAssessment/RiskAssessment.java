package cas.XB3.earthquake.riskAssessment;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
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
		earthquakeLists.add(SearchEarthquakeInCircle.searchEarthquakeInCircle(location, 100000));
		earthquakeLists.add(SearchEarthquakeInCircle.searchEarthquakeInCircle(location, 200000));
		earthquakeLists.add(SearchEarthquakeInCircle.searchEarthquakeInCircle(location, 300000));
		
		

		for(int i = 0;i < earthquakeLists.size(); i ++) {
			cityName = getCityName(location, earthquakeLists.get(i));
			if(cityName != null)
				break;
		}
		
		int frequency1  = SearchEarthquakeInCircle.getFrequency(earthquakeLists.get(0));
		int frequency2  = SearchEarthquakeInCircle.getFrequency(earthquakeLists.get(1));
		int frequency3  = SearchEarthquakeInCircle.getFrequency(earthquakeLists.get(2));
		int averagerMag1  = SearchEarthquakeInCircle.getAverageMangenitude(earthquakeLists.get(0));
		int averagerMag2  = SearchEarthquakeInCircle.getAverageMangenitude(earthquakeLists.get(1));
		int averagerMag3  = SearchEarthquakeInCircle.getAverageMangenitude(earthquakeLists.get(2));
		
		int populationdensity  = getPopulation(cityName);
		
		rating = (int) Math.round(0.5 * (frequency1 + Math.pow(averagerMag1, 2)) + 0.3 * (frequency2 + Math.pow(averagerMag2, 2)) + 2 * populationdensity/ 1000)
		
		
	}
	
	
	private String getCityName(PointT location, ArrayList<EarthquakeT> earthquakeList) {
		double minDistance = Double.MAX_VALUE;
		EarthquakeT nearestEarthquake;
		for(EarthquakeT earthquake: earthquakeList ) {
			if(location.distanceTo(earthquake.getPoint()) < minDistance) {
				minDistance = location.distanceTo(earthquake.getPoint();
				nearestEarthquake = earthquake;
				
			}
		}
		if(minDistance == Double.MAX_VALUE)
			return null;
		return nearestEarthquake.getCity();
			

		
	}
//	SearchEarthquakeInCircle search = new SearchEarthquakeInCircle();
	

}
