package graph.minimumSpanningTree;

import graph.util.Edge;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MSTTest {

    @ParameterizedTest
    @MethodSource("provideGraphMSTs")
    public void testPrim(ArrayList<ArrayList<Edge>> graph,int expectedtMSTWeight) {
        assertEquals(expectedtMSTWeight, Arrays.stream(Prim.getMST(0, graph)).mapToInt(edge-> edge.distance).sum());
    }
    @ParameterizedTest
    @MethodSource("provideGraphMSTs")
    public void testKruskal(ArrayList<ArrayList<Edge>> graph,int expectedtMSTWeight) {
        Edge[] edgesNoDupes = extractEdges(graph, false);
        Edge[] edgesWithDupes = extractEdges(graph, true);
        assertEquals(edgesNoDupes.length * 2, edgesWithDupes.length);
        assertEquals(expectedtMSTWeight, Kruskal.minCost(graph.size(), edgesNoDupes));
        assertEquals(expectedtMSTWeight, Kruskal.minCost(graph.size(), edgesWithDupes));
    }

    @ParameterizedTest
    @MethodSource("provideGraphMSTs")
    public void testPoorManKruskal(ArrayList<ArrayList<Edge>> graph,int expectedtMSTWeight) {
        Edge[] edgesNoDupes = extractEdges(graph, false);
        Edge[] edgesWithDupes = extractEdges(graph, true);
        assertEquals(edgesNoDupes.length * 2, edgesWithDupes.length);
        assertEquals(expectedtMSTWeight, Kruskal.minCostImprovedUnionFind(graph.size(), edgesNoDupes));
        assertEquals(expectedtMSTWeight, Kruskal.minCostImprovedUnionFind(graph.size(), edgesWithDupes));
    }

    private Edge[] extractEdges(ArrayList<ArrayList<Edge>> graph, boolean duplicates){
        ArrayList<Edge> edges = new ArrayList<>();
        for (ArrayList<Edge> edgeArrayList : graph) {
            for (Edge curEdge : edgeArrayList) {
                if (duplicates || curEdge.fromNode < curEdge.toNode) {
                    edges.add(curEdge);
                }
            }
        }

      return edges.toArray(Edge[]::new);
    }

    private static Stream<Arguments> provideGraphMSTs() {
        ArrayList<ArrayList<Edge>> graph1 = new ArrayList<>();

        graph1.add(createArrayList(new Edge(0, 1, 10), new Edge(0, 2, 6), new Edge(0, 3, 5)));
        graph1.add(createArrayList(new Edge(1, 0, 10), new Edge(1, 3, 15)));
        graph1.add(createArrayList(new Edge(2, 0, 6), new Edge(2, 3, 4)));
        graph1.add(createArrayList(new Edge(3, 0, 5), new Edge(3, 1, 15), new Edge(3, 2, 4)));

        ArrayList<ArrayList<Edge>> graph2= new ArrayList<>();
        graph2.add(createArrayList(new Edge(0, 1, 2), new Edge(0, 3, 6)));
        graph2.add(createArrayList(new Edge(1, 0, 2), new Edge(1, 2, 3), new Edge(1, 3, 8), new Edge(1, 4, 5)));
        graph2.add(createArrayList(new Edge(2, 1, 3), new Edge(2, 4, 7)));
        graph2.add(createArrayList(new Edge(3, 0, 6), new Edge(3, 1, 8), new Edge(3, 4, 9)));
        graph2.add(createArrayList(new Edge(4, 1, 5), new Edge(4, 2, 7), new Edge(4, 3, 9)));


        ArrayList<ArrayList<Edge>> graph3 = new ArrayList<>();
        graph3.add(createArrayList(new Edge(0, 1, 1), new Edge(0, 2, 4), new Edge(0, 3, 3)));
        graph3.add(createArrayList(new Edge(1, 0, 1), new Edge(1, 3, 2), new Edge(1, 4, 7)));
        graph3.add(createArrayList(new Edge(2, 0, 4), new Edge(2, 3, 5), new Edge(2, 5, 6)));
        graph3.add(createArrayList(new Edge(3, 0, 3), new Edge(3, 1, 2), new Edge(3, 2, 5), new Edge(3, 5, 8)));
        graph3.add(createArrayList(new Edge(4, 1, 7), new Edge(4, 5, 4)));
        graph3.add(createArrayList(new Edge(5, 2, 6), new Edge(5, 3, 8), new Edge(5, 4, 4)));

        return Stream.of(
                Arguments.of(graph1, 19),
                Arguments.of(graph2, 16),
                Arguments.of(graph3, 17)
        );
    }

    private static ArrayList<Edge> createArrayList(Edge... edges){
        ArrayList<Edge> edgeList = new ArrayList<>();
        Collections.addAll(edgeList, edges);
        return edgeList;
    }
}
