package cas.XB3.earthquake.Graph;

public class Edge {
	private String v;
	private String w;
	private int weight;

	public Edge(String v, String w, int weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public void setWeight(int weight) {
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

	public String toString() {
		return String.format("%s->%s %d", v, w, weight);
	}


}
