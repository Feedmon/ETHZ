package graph.util;

public class PoorManUnionFind {
	private int[] repZHK;
	private int[] ZHKSize;

	public PoorManUnionFind(int nodes) {
		make(nodes);
	}

	private void make(int nodes) {
		repZHK = new int[nodes];
		ZHKSize = new int[nodes];

		for (int i = 0; i < nodes; i++) {
			repZHK[i] = i;
			ZHKSize[i] = 1;
		}
	}

	public boolean union(int firstNode, int secondNode) {
		int repFN = findRep(firstNode);
		int repSN = findRep(secondNode);

		if (same(repFN, repSN)) {
			return false;
		}

		if (ZHKSize[repFN] > ZHKSize[repSN]) {
			repZHK[repSN] = repFN;
			ZHKSize[repSN] = ZHKSize[repSN] + ZHKSize[repFN];
		} else {
			repZHK[repFN] = repSN;
			ZHKSize[repFN] = ZHKSize[repFN] + ZHKSize[repSN];
		}

		return true;
	}

	private boolean same(int firstNode, int secondNode) {
		return firstNode == secondNode;
	}

	private int findRep(int node) {
		if (repZHK[node] == node) {
			return node;
		}
		return findRep(repZHK[node]);
	}
}
