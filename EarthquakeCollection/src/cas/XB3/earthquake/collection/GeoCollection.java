package cas.XB3.earthquake.collection;

import cas.XB3.earthquake.ADT.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GeoCollection {
	private static HashMap<String, ArrayList<CityT>> cityHashMap = new HashMap<>();

    public static void add(String province, CityT city){
    	ArrayList<CityT> temp = new ArrayList<>();
    	if(cityHashMap.get(province) == null) {
    		temp.add(city);
    		cityHashMap.put(province, temp);
    	}else {
    		temp = (ArrayList<CityT>) cityHashMap.get(province).clone();
    		temp.add(city);
    		cityHashMap.put(province, temp);
    	}
    }

    public static boolean isEmpty(){
        return cityHashMap.isEmpty();
    }
    
    public static ArrayList<CityT> getCityArrayList(String province) {
    	return cityHashMap.get(province);
    }
}
