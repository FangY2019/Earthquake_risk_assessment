/**
 *  
 * 
 */
package cas.XB3.earthquake.riskAssessment;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.CityPostT;
import cas.XB3.earthquake.ADT.CityT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.Graph.CityGraph;
import cas.XB3.earthquake.Graph.Edge;
import cas.XB3.earthquake.collection.CSVreader;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.GeoCollection;
import cas.XB3.earthquake.collection.RedBlackBST;
import cas.XB3.earthquake.search.SearchEarthquakes;

public class RiskAssessment {
	private RedBlackBST<Double, EarthquakeT> earthquakeTree;
	private String cityName;
	private int frequency;
	private double averageMag;
	private double populationDensity;
	private int rating;

	/**
	 * 
	 * Assuming the future earthquake risk for a specific location is determined by
	 * three factors: historical earthquake frequency, magnitude and population
	 * density
	 * 
	 * @param earthquakeTree
	 * @param location
	 */
	public RiskAssessment(RedBlackBST<Double, EarthquakeT> earthquakeTree, PointT location) {
		this.earthquakeTree = earthquakeTree;
		// search earthquake in two circles of different range
		ArrayList<EarthquakeT> earthquakeLists = SearchEarthquakes.searchEarthquakeInCircle(earthquakeTree,
				location, 100);
//		System.out.println("The size of the list assessing location: " + earthquakeLists.size());

		// find the nearest city name in the smallest circle
		this.cityName = getCityName(location, earthquakeLists); // place string with other information
		this.frequency = Frequency(earthquakeLists);
		this.averageMag = AverageMagnitude(earthquakeLists);
		this.populationDensity = getPopulation();
		// if no earthquake in range
		if (earthquakeLists.size() == 0)
			this.rating = 0;
		else
			this.rating = OverallRating(this.frequency, this.averageMag, this.populationDensity);
//		System.out.println("frequency1: " + frequency1);
//		System.out.println("averagerMag1: " + averagerMag1);
//		System.out.println("populationdensity: " + populationdensity);
//		System.out.println("this city1 :" +this.cityName);
	}

	/**
	 * Gets the risk rating of the assessed location
	 * 
	 * @return The risk rating of the assessed location
	 */
	public int getRisk() {
		return this.rating;
	}

	/**
	 * Gets the city name of the nearest earthquake from the the assessing location
	 * 
	 * @return The city name of the nearest earthquake from the the assessing
	 *         location
	 */
	public String getCity() {
		return this.cityName;
	}

	/**
	 * Gets the earthquake frequency for this risk assessment
	 * 
	 * @return The earthquake frequency
	 */
	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * Gets the average magnitude for this risk assessment
	 * 
	 * @return The earthquake frequency
	 */
	public double getMag() {
		return this.averageMag;
	}

	/**
	 * Gets the population density in the nearest city from the given location
	 * 
	 * @return The population density in the nearest city from the given location
	 */
	public double getPoplationDensity() {
		return this.populationDensity;
	}

	/**
	 * Finds the nearest city in the range of 200 kilometers, where the risk rating
	 * is lower than this location *
	 * 
	 * @param graph A cityGraph which represents a connected graph between this
	 *              location and all the city in the range of 200 km in the city
	 *              coordinates dataset
	 * @return The nearest city whose risk rating is lower than this location
	 */
	public String nearestLowerRiskCity(CityGraph graph) {
		String nearestSfaterCity = null;
		int minDistance = Integer.MAX_VALUE;
		if (graph.adj(this.cityName) != null) {
			for (Edge city : graph.adj(this.cityName)) {
				if (new RiskAssessment(this.earthquakeTree, getLocation(city.to())).getRisk() < this.rating
						&& city.weight() < minDistance) {
					minDistance = city.weight();
					nearestSfaterCity = city.to();
				}
			}
		}
		if (minDistance == Integer.MAX_VALUE)
			nearestSfaterCity = null;
		return nearestSfaterCity;
	}

	// Gets the coordinate of the given city from the city coordinates list
	private PointT getLocation(String city) {
		PointT location = null;
		ArrayList<CityPostT> cityPostList = new ArrayList<>();
		CSVreader.readCityPosition("./City_Coordinates.CSV", cityPostList);
		for (CityPostT cityPost : cityPostList) {
			if (cityPost.getCityName().equals(city))
				location = cityPost.getPoint();
		}
		return location;
	}

	// get the city name from the nearest EarthquakeT of the given location
	private String getCityName(PointT location, ArrayList<EarthquakeT> earthquakeList) {
		double minDistance = Double.MAX_VALUE;
		EarthquakeT nearestEarthquake = null;
		for (EarthquakeT earthquake : earthquakeList) {
			if (location.distanceTo(earthquake.getPointT()) < minDistance) {
				minDistance = location.distanceTo(earthquake.getPointT());
				nearestEarthquake = earthquake;
			}
		}
//		System.out.println("heihei: " + nearestEarthquake.getPointT().getLat() +", " + nearestEarthquake.getPlace());
		if (minDistance == Double.MAX_VALUE)
			return null;
//		System.out.println("nearestEarthquake :" +nearestEarthquake.getPointT().getLat() + "," +nearestEarthquake.getPointT().getLong());
//		System.out.println("this city2 :" +nearestEarthquake.getPlace());
		return nearestEarthquake.getPlace();
		
	}

	// Gets the population density of the given city name from the population
	// dataset
	private double getPopulation() {
//		System.out.println("place " + String.valueOf(cityName.charAt(0)));
		populationDensity = 0;
		if (this.cityName != null) {
			GeoCollection GeoCollection = new GeoCollection();
			CSVreader.readPopulation("./T301EN.CSV", GeoCollection);
			if (GeoCollection.getCityHashMap().keySet().contains(String.valueOf(this.cityName.charAt(0)))) {
				ArrayList<CityT> listOfCity = GeoCollection.getCityArrayList(String.valueOf(this.cityName.charAt(0)));
//				System.out.println("Fisrt letter of the string " + String.valueOf(cityName.charAt(0)));
//				System.out.println(listOfCity.size());
				if (listOfCity.size() != 0) {
					for (CityT city : listOfCity) {
						if (this.cityName.contains(city.getCityName())) { // place string contains cityname
//							System.out.println("city2:" + this.cityName);
//							System.out.println("city:" +city.getCityName());
							populationDensity = city.getPopDensity();
							break;
						}
					}
				}
			}
		}
//		System.out.println("population:" +populationDensity);
		return populationDensity;
	}

	// Calculates the earthquake frequency of a given earthquake list
	private int Frequency(ArrayList<EarthquakeT> earthquakeList) {
		return earthquakeList.size();
	}

	// Calculates the average magnitude of a given earthquake list
	private double AverageMagnitude(ArrayList<EarthquakeT> earthquakeList) {
		double sum = 0;
		for (EarthquakeT earthquake : earthquakeList) {
			sum += earthquake.getMag();
		}
		int frequency = Frequency(earthquakeList);
		if (frequency == 0)
			return 0;
		return (double) (sum / (double) Frequency(earthquakeList));
	}

	// Calculates the overall risk rating regarding a given earthquake
	// frequency,average magnitude and population density
	public int OverallRating(int frequency, double averagerMag, double populationdensity) {
		return frequencyRating(frequency) + magnitudeRating(averagerMag) + populationdensityRating(populationdensity);

	}

	// Calculates the risk rating of a given frequency based on the assuming
	// criterion
	public int frequencyRating(int frequency) {
//		System.out.println("frequency2: " + frequency);
		int risk_frequency = 0;
		if (frequency < 1)
			risk_frequency = 0;
		else if (1 <= frequency && frequency < 10)
			risk_frequency = 1;
		else if (10 <= frequency && frequency < 100)
			risk_frequency = 2;
		else if (100 <= frequency && frequency < 1000)
			risk_frequency = 3;
		else
			risk_frequency = 4;
		return risk_frequency;
	}

	// Calculates the risk rating of a given average magnitude based on the assuming
	// criterion
	public int magnitudeRating(double averagerMag) {
//		System.out.println("averagerMag 2: " + averagerMag);
		int risk_averagerMag = 0;
		if (averagerMag < 1)
			risk_averagerMag = 0;
		else if (1 <= averagerMag && averagerMag < 4)
			risk_averagerMag = 1;
		else if (4 <= averagerMag && averagerMag < 6)
			risk_averagerMag = 2;
		else if (6 <= averagerMag && averagerMag < 7)
			risk_averagerMag = 3;
		else
			risk_averagerMag = 4;
		return risk_averagerMag;
	}

	// Calculates the risk rating of a given population density based on the
	public int populationdensityRating(double populationdensity) {
//		System.out.println("populationdensity 2: " + populationdensity);
		int risk_population = 0;
		if (populationdensity < 1000)
			risk_population = 0;
		else if (1000 <= populationdensity && populationdensity < 5000)
			risk_population = 1;
		else if (5000 <= populationdensity)
			risk_population = 2;
		return risk_population;

	}

}
