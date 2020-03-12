package cas.XB3.earthquake.ADT;

public class CityT {
	private String cityName;
	private String province;
	private double popDensity;
	
	public CityT(String cityName, String province, double popDensity) {
		this.cityName = cityName;
		this.province = province;
		this.popDensity = popDensity;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getProvince() {
		return province;
	}
	
	public double getPopDensity() {
		return popDensity;
	}
}
