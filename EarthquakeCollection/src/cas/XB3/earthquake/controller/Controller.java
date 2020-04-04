package cas.XB3.earthquake.controller;

import java.util.ArrayList;

import cas.XB3.earthquake.ADT.CityPostT;
import cas.XB3.earthquake.ADT.EarthquakeT;
import cas.XB3.earthquake.ADT.PointT;
import cas.XB3.earthquake.Graph.CityGraph;
import cas.XB3.earthquake.reader.CSVreader;
import cas.XB3.earthquake.search.GeoCollection;
import cas.XB3.earthquake.search.RedBlackBST;
import cas.XB3.earthquake.search.SearchEarthquakes;
import cas.XB3.earthquake.view.ViewList;
import cas.XB3.earthquake.view.ViewRisk;

public class Controller {
	PointT location;
	ArrayList<EarthquakeT> eqList;
	
	public void init(RedBlackBST<Double, EarthquakeT> earthquakeTree, GeoCollection GeoCollection, ArrayList<CityPostT> cityPostList) {
		CSVreader.readEarthquakesBST("./eqarchive-en.csv",earthquakeTree);
		CSVreader.readPopulation("./T301EN.CSV", GeoCollection);
		CSVreader.readCityPosition("./City_Coordinates.CSV", cityPostList);	
	}

	
	public void search(RedBlackBST<Double, EarthquakeT> bst,PointT location, double radius){
		eqList = SearchEarthquakes.searchEarthquakeInCircle(bst, location, radius);
		this.location = location;

	}
	
	
	public void updateViewOfList(ViewList dispalyByOption) {
		dispalyByOption.display(eqList, location);
	}

	public void updateViewOfRisk(RedBlackBST<Double, EarthquakeT> earthquakeTree, PointT location,
			ArrayList<CityPostT> cityPostList, CityGraph graph) {
		ViewRisk.showRisk(earthquakeTree, location, cityPostList, graph);
	}
		

}
