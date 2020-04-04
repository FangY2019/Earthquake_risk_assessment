package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.EarthquakeT;
import cas.XB3.earthquake.ADT.PointT;

public interface ViewList {
	public void display(ArrayList<EarthquakeT> earthquakeList, PointT location);

}
