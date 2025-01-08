package graph.util;

public class Edge implements Comparable<Edge> {
	public int fromNode;
	public int toNode;
	public int distance;

	public Edge(int fromNode, int toNode, int distance) {
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge other) {
		return this.distance - other.distance;
	}
}
