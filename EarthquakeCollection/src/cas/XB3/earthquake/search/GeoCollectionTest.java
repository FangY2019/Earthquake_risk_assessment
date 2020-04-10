package cas.XB3.earthquake.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cas.XB3.earthquake.ADT.CityT;

public class GeoCollectionTest {
	private GeoCollection gc;

	@Before
	public void setUp() throws Exception {
		gc = new GeoCollection();
	}

	@Test
	public void testAdd() {
		gc.add(new CityT("cityA", "ON", 300));
		assertFalse(gc.isEmpty());
	}
	

	@Test
	public void testIsEmpty() {
		assertTrue(gc.isEmpty());
	}

	@Test
	public void testGetCityArrayList() {
		gc.add(new CityT("cityA", "ON", 300));
		gc.add(new CityT("cityB", "ON", 300));
		assertTrue(gc.getCityArrayList("c").size()==2);
	}

	@Test
	public void testGetCityHashMap() {
		gc.add(new CityT("cityA", "ON", 300));
		gc.add(new CityT("cityB", "ON", 300));
		assertTrue(gc.getCityHashMap().containsKey("c"));
	}

}
