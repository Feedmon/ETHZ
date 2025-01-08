package graph.util;

public class Node {
	public int key;
	public int distance;

	public Node(int key) {
		this.key = key;
		this.distance = Integer.MAX_VALUE;
	}

	public Node(int key, int initDist) {
		this.key = key;
		this.distance = initDist;
	}
}
