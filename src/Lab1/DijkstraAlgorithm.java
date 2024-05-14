package Lab1;

import java.util.*;

public class DijkstraAlgorithm {

    private static final String UNREACHABLE = "不可达";
    private Map<String, Map<String, Integer>> graph = new HashMap<>();
    public DijkstraAlgorithm (DirectedGraph graphGraph){
        this.graph = graphGraph.getGraph();
    }

    public  String calcShortestPath(String start, String end) {
        Set<String> settledNodes = new HashSet<>();
        Set<String> unsettledNodes = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();

        distances.put(start, 0);
        unsettledNodes.add(start);

        while (unsettledNodes.size() != 0) {
            String currentNode = getLowestDistanceNode(unsettledNodes, distances);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<String, Integer> adjacencyPair : graph.get(currentNode).entrySet()) {
                String adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode, distances, predecessors);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        return reconstructPath(predecessors, start, end);
    }

    public  Map<String, String> findAllShortestPaths( String start) {
        Set<String> settledNodes_one = new HashSet<>();
        Set<String> unsettledNodes_one = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> predecessors = new HashMap<>();
        Map<String, String> shortestPaths = new HashMap<>();

        distances.put(start, 0);
        unsettledNodes_one.add(start);

        while (unsettledNodes_one.size() != 0) {
            String currentNode = getLowestDistanceNode(unsettledNodes_one, distances);
            unsettledNodes_one.remove(currentNode);
            for (Map.Entry<String, Integer> adjacencyPair : graph.get(currentNode).entrySet()) {
                String adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();

                if (!settledNodes_one.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode, distances, predecessors);
                    unsettledNodes_one.add(adjacentNode);
                }
            }
            settledNodes_one.add(currentNode);
        }

        for (String node : graph.keySet()) {
            shortestPaths.put(node, reconstructPath(predecessors, start, node));
        }

        return shortestPaths;
    }

    private static String getLowestDistanceNode(Set<String> unsettledNodes, Map<String, Integer> distances) {
        String lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (String node : unsettledNodes) {
            int nodeDistance = distances.getOrDefault(node, Integer.MAX_VALUE);
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private static void calculateMinimumDistance(String evaluationNode, Integer edgeWeigh, String sourceNode,
                                                 Map<String, Integer> distances, Map<String, String> predecessors) {
        Integer sourceDistance = distances.get(sourceNode);
        if (sourceDistance + edgeWeigh < distances.getOrDefault(evaluationNode, Integer.MAX_VALUE)) {
            distances.put(evaluationNode, sourceDistance + edgeWeigh);
            predecessors.put(evaluationNode, sourceNode);
        }
    }

    private static String reconstructPath(Map<String, String> predecessors, String start, String end) {
        LinkedList<String> path = new LinkedList<>();
        String step = end;
        if (predecessors.get(step) == null) {
            return UNREACHABLE;
        }
        path.add(step);
        while (predecessors.containsKey(step)) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return String.join("→", path);
    }
}

