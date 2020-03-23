package cas.XB3.earthquake.ADT;

public class CityPostT {
	private String cityName;
	private PointT point;

	public CityPostT(String cityName, double lat, double lon) {
		this.cityName = cityName;
		this.point = new PointT(lat, lon);
	}

	public PointT getPoint() {
		return this.point;
	}

	public String getCityName() {
		return this.cityName;
	}

}
