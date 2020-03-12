package cas.XB3.earthquake.collection;

import cas.XB3.earthquake.ADT.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GeoCollection {
	private HashMap<String, ArrayList<CityT>> cityHashMap = new HashMap<>();

    public void add(String province, CityT city){
    	ArrayList<CityT> temp = new ArrayList<>();
    	if(cityHashMap.get(province) == null) {
    		temp.add(city);
    		cityHashMap.put(province, temp);
    	}else {
    		temp = (ArrayList<CityT>) cityHashMap.get(province).clone();
    		temp.remove(city); // avoid potential duplicated cites
    		temp.add(city);
    		cityHashMap.put(province, temp);
    	}
    }

    public boolean isEmpty(){
        return cityHashMap.isEmpty();
    }
    
    public ArrayList<CityT> getCityArrayList(String province) {
    	return cityHashMap.get(province);
    }
    
    public HashMap<String, ArrayList<CityT>> getCityHashMap(){
    	return cityHashMap;
    }
}
