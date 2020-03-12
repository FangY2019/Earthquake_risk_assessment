package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;
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

}
