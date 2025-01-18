package graph.util;

public class ImprovedUnionFind {
	private final int[] repZHK;
	private final int[] rankZHK;

	public ImprovedUnionFind(int nodes) {
		repZHK = new int[nodes];
		rankZHK = new int[nodes];

		for (int i = 0; i < nodes; i++) {
			repZHK[i] = i;
		}
	}

	public boolean union(int firstNode, int secondNode) {
		int repFN = findRep(firstNode);
		int repSN = findRep(secondNode);

		if (repFN == repSN) {
			return false;
		}

		if (rankZHK[repFN] > rankZHK[repSN]) {
			repZHK[repSN] = repFN;
		} else if(rankZHK[repFN] < rankZHK[repSN]){
			repZHK[repFN] = repSN;
		}else {
			repZHK[repFN] = repSN;
			rankZHK[repSN]++;
		}

		return true;
	}

	// find using path halving (see wikipedia disjoint set datastructure)
	private int findRep(int node) {
		while(repZHK[node] != node){
			repZHK[node] = repZHK[repZHK[node]];
			node = repZHK[node];
		}
		return node;
	}
}
