package graph.util;

public class UnionFind {

	ZHK[] repZHK;

	public UnionFind(int nodes) {
		repZHK = new ZHK[nodes];
		make(nodes);
	}

	public boolean same(int firstNode, int secondNode) {
		return repZHK[firstNode].rep == repZHK[secondNode].rep;
	}

	public void union(int firstNode, int secondNode) {
		ZHK lhsZHK = repZHK[firstNode];
		ZHK rhsZHK = repZHK[secondNode];

		if (lhsZHK.members.size() < rhsZHK.members.size()) {
			lhsZHK = rhsZHK;
			rhsZHK = repZHK[firstNode];
		}

		for (Integer member : rhsZHK.members) {
			repZHK[member] = lhsZHK;
			lhsZHK.addMember(member);
		}
	}

	private void make(int size) {
		for (int i = 0; i < size; i++) {
			repZHK[i] = new ZHK(i);
		}
	}
}
