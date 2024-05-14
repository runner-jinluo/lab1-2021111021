package Lab1;
import javax.swing.*;
import java.util.*;
/*import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;*/

public class DirectedGraph {
    //private Graph graphPicture = new SingleGraph("DirectedGraph");
<<<<<<< HEAD
    private Map<String, Map<String, Integer>> graph = new HashMap<>();
=======

>>>>>>> refs/remotes/origin/master
    /*没有节点名称的有向图
    public void createGraphPictureFromText() {

        for (String node : graph.keySet()) {
            graphPicture.addNode(node);
        }
        for (String node : graph.keySet()) {
            Map<String, Integer> neighbors = graph.get(node);
            for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                String neighborNode = entry.getKey();
                graphPicture.addEdge(node + neighborNode, node, neighborNode, true);
            }
        }
    }
    // 可视化有向图
    public void visualizeGraph() {
        System.setProperty("org.graphstream.ui", "swing");
        graphPicture.display();
    }
*/
    // 将文本转化为有向图
    public void createGraphFromText(String text) {
        String[] words = text.toLowerCase().split("\\s+"); // 将文本分割成单词数组
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            graph.putIfAbsent(word1, new HashMap<>());
            Map<String, Integer> edges = graph.get(word1);
            edges.put(word2, edges.getOrDefault(word2, 0) + 1);
        }
    }

    // 打印有向图
    public void showDirectedGraph(Map<String, Map<String, Integer>> graph1) {
        for (String word : graph1.keySet()) {
            System.out.print(word + " -> ");
            Map<String, Integer> edges = graph1.get(word);
            for (String adjacentWord : edges.keySet()) {
                System.out.print(adjacentWord + "(" + edges.get(adjacentWord) + ") ");
            }
            System.out.println();
        }
    }
    /*判断swing是否可用*/
    public static void CheckSwing() {
        JFrame frame = new JFrame("Check Swing");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public  Map<String, Map<String, Integer>> getGraph () {
        return graph;
    }
}
