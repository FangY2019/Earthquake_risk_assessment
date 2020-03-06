package cas.XB3.earthquake.display;

import cas.XB3.earthquake.ADT.PointT;

//manage the display option
public class DisplayManager {
	PointT location;
	double radius;
	
	public DisplayManager(PointT location, double radius) {
		this.location = location;
		this.radius = radius;
	}
	
	public void display(DisplayInterface dispalyByOption) {		
		dispalyByOption.display(location, radius);
	}

}
