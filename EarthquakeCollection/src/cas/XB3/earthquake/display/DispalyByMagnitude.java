package cas.XB3.earthquake.display;
import java.util.ArrayList;

public class DispalyByMagnitude extends BaseDisplay{
	public void display(PointT location, double radius) {
		ArrayList<EarthquakeT> earthquakeList = new SearchEarthquakeInCircle().searchEarthquakeInCircle(location, radius);
		SortEarthquakeListByMagnitude.sort(earthquakeList);
		for(EarthquakeT eq: earthquakeList) {
			System.out.println("" + eq.getEarthquakePlace() + eq.getMag());
		}
		
		
	}

}
