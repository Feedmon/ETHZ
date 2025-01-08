package graph.search;

import java.util.ArrayList;
import java.util.Arrays;

public class DepthFirstSearch {

	private static int time;

	// can be done with stack instead of recursive
	// adjList runtime: |V| + |E|
	// adjMatrix runtime: n*n
	public static void runDFS(ArrayList<ArrayList<Integer>> adjList) {
		int[] preTime = new int[adjList.size()];
		int[] postTime = new int[adjList.size()];

		time = 1;

		for (int node = 0; node < adjList.size(); node++) {
			if (preTime[node] == 0) {
				visit(node, adjList, preTime, postTime);
			}
		}

		System.out.println(Arrays.toString(preTime));
		System.out.println(Arrays.toString(postTime));
	}

	private static void visit(int node, ArrayList<ArrayList<Integer>> adjList, int[] preTime, int[] postTime) {
		preTime[node] = time++;

		for (int neighBour : adjList.get(node)) {
			if (preTime[neighBour] == 0) {
				visit(neighBour, adjList, preTime, postTime);
			}
		}

		postTime[node] = time++;
	}
}
