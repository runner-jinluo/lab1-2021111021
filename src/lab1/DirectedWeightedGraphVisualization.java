package lab1;
/*
import org.jgrapht.graph.*;

import org.jgrapht.nio.*;
import org.jgrapht.visualization.*;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class DirectedWeightedGraphVisualization {
    private DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

    public DirectedWeightedGraphVisualization(Map<String, Map<String, Integer>> data) {
        // Create a directed weighted graph
        graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // Add vertices
        for (String vertex : data.keySet()) {
            graph.addVertex(vertex);
        }

        // Add edges with weights
        for (String source : data.keySet()) {
            Map<String, Integer> targets = data.get(source);
            for (Map.Entry<String, Integer> entry : targets.entrySet()) {
                String target = entry.getKey();
                Integer weight = entry.getValue();
                DefaultWeightedEdge edge = graph.addEdge(source, target);
                graph.setEdgeWeight(edge, weight);
            }
        }
    }

    public void visualize() {
        // Create a visualization component for the graph
        VisualizationViewer<String, DefaultWeightedEdge> vv =
                new VisualizationViewer<>(new DefaultStaticLayout<>(graph));

        // Configure the visualization component
        vv.setPreferredSize(new Dimension(600, 400));

        // Create a frame to hold the visualization
        JFrame frame = new JFrame("Directed Weighted Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

}
*/