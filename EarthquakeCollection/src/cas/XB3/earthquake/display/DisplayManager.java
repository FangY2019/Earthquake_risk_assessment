package cas.XB3.earthquake.display;

//manage the display option
public class DisplayManager {
	PointT location;
	double radius;
	
	public void display(BaseDisplay dispalyByOption) {		
		dispalyByOption.display(location, radius);
	}

}
