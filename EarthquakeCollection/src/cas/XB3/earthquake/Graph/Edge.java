/**
 * The Edge class represents a ADT of weighted edge in an EdgeWeighted Graph
 * @author Ye Fang
 * Revised: March 24, 2020
 */
package cas.XB3.earthquake.Graph;

public class Edge {
	private String v;
	private String w;
	private int weight;

	/**
	 * Initializes an edge object between vertices v and w of the given weight
	 * 
	 * @param v    a start vertex in the edge
	 * @param w    a end vertex in the edge
	 * @param weight a menuT object, its price represents the weight of the edge
	 */
	public Edge(String v, String w, int weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int weight() {
		return this.weight;
	}

	public String from() {
		return v;
	}

	public String to() {
		return w;
	}

}
