package cas.XB3.earthquake.search;

import cas.XB3.earthquake.ADT.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GeoCollection {
	private HashMap<String, ArrayList<CityT>> cityHashMap = new HashMap<>();

    public void add(CityT city){
    	ArrayList<CityT> temp = new ArrayList<>();
    	String firstCityLetter = getFirstCityLetter(city);
    	if(cityHashMap.get(firstCityLetter) == null) {
    		temp.add(city);
    		cityHashMap.put(firstCityLetter, temp);
    	}else {
    		temp = (ArrayList<CityT>) cityHashMap.get(firstCityLetter).clone();
    		temp.remove(city); // avoid potential duplicated cites
    		temp.add(city);
    		cityHashMap.put(firstCityLetter, temp);
    	}
    }

    public boolean isEmpty(){
        return cityHashMap.isEmpty();
    }
    
    public ArrayList<CityT> getCityArrayList(String firstLetter) {
    	return cityHashMap.get(firstLetter);
    }
    
    public HashMap<String, ArrayList<CityT>> getCityHashMap(){
    	return cityHashMap;
    }
    
    private String getFirstCityLetter(CityT city) {
    	return city.getCityName().substring(0,1);
    }
}
