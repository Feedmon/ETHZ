package graph.minimumSpanningTree;

import graph.search.DepthFirstSearch;
import graph.util.Edge;
import graph.util.UnionFind;
import graph.util.ZHK;

import java.util.ArrayList;

public class Boruvka {
    // runtime O((|V| + |E|) * log(|V|))
    public static Edge[] getMST(ArrayList<ArrayList<Edge>> graph) {
        UnionFind unionFind = new UnionFind(graph.size());

        ArrayList<Edge> mst = new ArrayList<>(graph.size() - 1);

        while(unionFind.getDiffZHKs() > 1){
            for (ZHK zhk : unionFind.getAllDiffZHKs()) {
                Edge minEdge = DepthFirstSearch.boruvkaMinEdge(zhk, graph);

                if (minEdge != null && !unionFind.same(minEdge.fromNode, minEdge.toNode)) {
                    unionFind.union(minEdge.fromNode, minEdge.toNode);
                    mst.add(minEdge);
                }
            }
        }

        return mst.toArray(Edge[]::new);
    }
}
