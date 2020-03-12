package cas.XB3.earthquake.ADT;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointTTest {
	private double x;
	private double y;
	private PointT p;
	private double y2 = 198;
	private double x2 = -103;
	private PointT p2;

	@Before
	public void setUp() throws Exception {
		p = new PointT(x,y);
		p2 = new PointT(x2, y2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPointT() {
	}

	@Test
	public void testDistanceToCase1() {
		assertEquals(p.distanceTo(new PointT(38.2, -79.32)), 9076, 1);
	}
	
	@Test
	public void testDistanceToCase2() {
		assertEquals(p2.distanceTo(new PointT(38.2, -79.32)), 14840, 1);
	}

	@Test
	public void testLatFilterCase1Smaller() {
		assertEquals(p.latFilter(80)[0], -0.7195, 0.001);
	}
	
	@Test
	public void testLatFilterCase1Larger() {
		assertEquals(p.latFilter(80)[1], 0.7195, 0.001);
	}

	@Test
	public void testEqualsPointT() {
		assertFalse(p.equals(new PointT(0.00000001,0.00000001)));
	}
	
	@Test
	public void testEqualsPointTCase2() {
		assertTrue(p2.equals(new PointT(77,168)));
	}

	@Test
	public void testGetLat() {
		assertEquals(p.getLat(), 0, 0.000001);
	}

	@Test
	public void testGetLong() {
		assertEquals(p.getLong(), 0, 0.000001);
	}

}

