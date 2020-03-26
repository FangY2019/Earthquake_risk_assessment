/**
 * Undirected graph data type implemented using a symbol 
 * @author Ye Fang
 * Revised: March 24, 2020
 */
package cas.XB3.earthquake.Graph;

import java.util.HashMap;
import java.util.LinkedList;



public class CityGraph {
	//HashMap: key = string vertex, value = list of neighboring vertices
	private HashMap<String, LinkedList<Edge>> adj;
	//number of edges
	private int E;

	/**
	 * Initializes an empty graph with no vertices or edges
	 */
	public CityGraph() {
		this.adj = new HashMap<>();
	}

	/**
	 * Adds the edge v-w to this graph
	 * @param v one vertex in the edge
	 * @param w another vertex in the edge
	 */
	public void addEdge(Edge e) {
		if(adj.get(e.from()) == null) {
			adj.put(e.from(), new LinkedList<>());
		}
		if(!adj.get(e.from()).contains(e)) {
			adj.get(e.from()).add(e);
			E++;
		}
	}
	
	/**
	 * Gets the list of vertices adjacent to v in this graph
	 * @param v the given vertex
	 * @return the list of vertices adjacent to vertex v
	 */
	public Iterable<Edge> adj(String v){
		return adj.get(v);
	}
	
	/**
	 * Gets the number of edges in this graph
	 * @return the number of edges in this graph
	 */
	public int getNumberOfEdges() {
		return this.E;
	}
	
	/**
	 * Gets the number of vertices in this graph
	 * @return the number of vertices in this graph
	 */
	public int V() {
		return this.adj.size();
	}
	
	public HashMap<String, LinkedList<Edge>> edges(){
		return this.adj;
	}
}
