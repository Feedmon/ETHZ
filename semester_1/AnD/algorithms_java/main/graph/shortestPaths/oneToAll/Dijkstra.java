package graph.shortestPaths.oneToAll;

import java.util.ArrayList;
import java.util.PriorityQueue;

import graph.util.Edge;
import graph.util.Node;
import graph.util.Tuple;

public class Dijkstra {

	public static int getMinDistance(ArrayList<ArrayList<Edge>> adjList, int startNode, int targetNode) {
		Node[] nodes = new Node[adjList.size()];

		for (int i = 0; i < adjList.size(); i++) {
			if (i == startNode) {
				nodes[i] = new Node(i, 0);
			} else {
				nodes[i] = new Node(i);
			}
		}

		executeDijkstraForNode(nodes, startNode, adjList);

		return nodes[targetNode].distance;
	}

	private static Node[] executeDijkstraForNode(Node[] nodes, int startNode, ArrayList<ArrayList<Edge>> adjList) {
		PriorityQueue<Tuple> pQueue = new PriorityQueue<>();
		
		for(int i = 0; i < adjList.get(startNode).size(); i++) {
			Edge curEdge = adjList.get(startNode).get(i);
			pQueue.offer(new Tuple(curEdge.distance, curEdge.toNode));
		}
		
		while(!pQueue.isEmpty()) {
			Tuple curPosition = pQueue.poll();
			
			if (nodes[curPosition.toNode].distance <= curPosition.curDistance) {
				continue;
			}

			nodes[curPosition.toNode].distance = curPosition.curDistance;

			for (int i = 0; i < adjList.get(curPosition.toNode).size(); i++) {
				Edge edge = adjList.get(curPosition.toNode).get(i);
				if (curPosition.curDistance + edge.distance < nodes[edge.toNode].distance) {
					pQueue.offer(new Tuple(curPosition.curDistance + edge.distance, edge.toNode));
				}
			}
			
		}
		
		return nodes;
	}
}
