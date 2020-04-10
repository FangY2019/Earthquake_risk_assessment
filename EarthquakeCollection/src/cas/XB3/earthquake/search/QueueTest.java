package cas.XB3.earthquake.search;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueTest {
	private Queue q;

	@Before
	public void setUp() throws Exception {
		q = new Queue<Integer>();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(q.isEmpty());
	}
	
	@Test
	public void testEnqueue() {
		q.enqueue(0);
		assertFalse(q.isEmpty());
	}

}
