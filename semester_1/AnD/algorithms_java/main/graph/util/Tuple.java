package graph.util;

public class Tuple implements Comparable<Tuple> {
	public int curDistance;
	public int toNode;

	public Tuple(int curDistance, int toNode) {
		this.curDistance = curDistance;
		this.toNode = toNode;
	}

	@Override
	public int compareTo(Tuple other) {
		return curDistance - other.curDistance;
	}
}
