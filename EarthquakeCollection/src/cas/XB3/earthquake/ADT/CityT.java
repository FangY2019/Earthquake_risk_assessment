package cas.XB3.earthquake.ADT;

public class CityT {
	private String cityName;
	private String province;
	private int population;
	
	public CityT(String cityName, String province, int population) {
		this.cityName = cityName;
		this.province = province;
		this.population = population;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getProvince() {
		return province;
	}
	
	public int getPopulation() {
		return population;
	}
}
