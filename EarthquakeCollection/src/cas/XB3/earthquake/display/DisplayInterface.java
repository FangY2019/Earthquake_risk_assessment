package cas.XB3.earthquake.display;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.collection.EarthquakeT;

public interface DisplayInterface {
	public void display(ArrayList<EarthquakeT> earthquakeList, PointT location);

}
