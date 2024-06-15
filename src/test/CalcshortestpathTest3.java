package test;

import Lab1.DijkstraAlgorithm;
import Lab1.DirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class CalcshortestpathTest3 {
    private DijkstraAlgorithm dijkstraAlgorithm;

    @BeforeEach
    public void setUp() {
        DirectedGraph directedGraph = new DirectedGraph();
        String text = "space the final frontier these are the voyages of the starship enterprise";
        directedGraph.createGraphFromText(text);
        dijkstraAlgorithm =new DijkstraAlgorithm(directedGraph);
    }
    @Test
    public void calcShortestPath() {
        String CalcShortestPath = dijkstraAlgorithm.calcShortestPath("voyages", "enterprise");
        String expected = "voyages→of→the→starship→enterprise";
        assertEquals(expected, CalcShortestPath);
    }
}