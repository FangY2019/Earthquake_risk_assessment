package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.CityPostT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.Graph.CityGraph;
import cas.XB3.earthquake.Graph.Edge;
import cas.XB3.earthquake.collection.EarthquakeT;
import cas.XB3.earthquake.collection.RedBlackBST;
import cas.XB3.earthquake.riskAssessment.RiskAssessment;

//manage the display option
public class DisplayManager {
	PointT location;
	ArrayList<EarthquakeT> earthquakeList;

	public DisplayManager(ArrayList<EarthquakeT> earthquakeList, PointT location) {
		this.location = location;
		this.earthquakeList = earthquakeList;
	}

	public void display(DisplayInterface dispalyByOption) {
		dispalyByOption.display(earthquakeList, location);
	}

	public void showRisk(RedBlackBST<Double, EarthquakeT> earthquakeTree, PointT location, ArrayList<CityPostT> cityPostList,
			CityGraph graph) {
		RiskAssessment riskAssessment = new RiskAssessment(earthquakeTree, location);
		int rating = riskAssessment.getRisk();
		for (CityPostT cityFrom : cityPostList) {
			if (cityFrom.getCityName().equals(riskAssessment.getCity())) {
				for (CityPostT cityTo : cityPostList) {
					if (!cityFrom.getCityName().equals(cityTo.getCityName())) {
						int distance = (int) cityFrom.getPoint().distanceTo(cityTo.getPoint());
						if (distance < 200) {
							Edge e = new Edge(cityFrom.getCityName(), cityTo.getCityName(), distance);
							graph.addEdge(e);
						}
					}
				}
			}
		}
		System.out.printf("%-56s%-5d%s\n", "The number of earthquakes within 100 km is :",riskAssessment.getFrequency(), " times");
		System.out.printf("%-56s%-5.1f\n", "The average magnitude of earthquakes within 100 km is :",riskAssessment.getMag());
		System.out.printf("%-56s%-5.1f%s\n", "The population density in the nearest city is :",riskAssessment.getPoplationDensity(), " per square kilometre");
		System.out.printf("The risk rating for the location (%.2f , %.2f) is : % d\n", location.getLat(),
				location.getLong(), rating);
		if (riskAssessment.nearestLowerRiskCity(graph) != null) {
			System.out.printf("The nearest lower risk city is: %s\n\n\n", riskAssessment.nearestLowerRiskCity(graph));
		} else {
			System.out.printf("There is no lower risk city in the range of 200 kilometers\n\n\n");
		}
	}

}
