package graph.util;

import java.util.*;

public class UnionFind {

	ZHK[] repZHK;

	public UnionFind(int nodes) {
		repZHK = new ZHK[nodes];
		make(nodes);

		// for boruvka
		diffZHKs = nodes;
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

		// for boruvka
		diffZHKs--;
	}

	private void make(int size) {
		for (int i = 0; i < size; i++) {
			repZHK[i] = new ZHK(i);
		}
	}

	// helperVariable for Boruvka
	private int diffZHKs;

	// helperMethod for Boruvka
	public List<ZHK> getAllDiffZHKs(){
		return Arrays.stream(repZHK).distinct().toList();
	}

	// helperMethod for Boruvka
	public int getDiffZHKs(){
		return diffZHKs;
	}
}
