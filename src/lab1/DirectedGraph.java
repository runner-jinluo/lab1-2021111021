package lab1;
import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
//import org.graphstream.ui.view.Viewer;
//import org.graphstream.ui.view.ViewerPipe;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class DirectedGraph {
    @SuppressWarnings("checkstyle:Indentation")
    private final Graph graphPicture = new SingleGraph("DirectedGraph");
    @SuppressWarnings("checkstyle:Indentation")
    private Map<String, Map<String, Integer>> graph = new HashMap<>();
    //没有节点名称的有向图
    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:EmptyLineSeparator"})
    public void createGraphPictureFromText() {

        for (String node : graph.keySet()) {
            graphPicture.addNode(node);
        }
        for (Node node : graphPicture) {
            node.setAttribute("ui.label", node.getId());
        }

        for (String node : graph.keySet()) {
            Map<String, Integer> neighbors = graph.get(node);
            for (Map.Entry<String, Integer> entry : neighbors.entrySet()) {
                String neighborNode = entry.getKey();
                int weight = entry.getValue();
                Edge edge = graphPicture.addEdge(node + neighborNode, node, neighborNode, true);
                edge.setAttribute("weight", weight);
                edge.setAttribute("ui.label", String.valueOf(weight));
            }
        }
    }
    // 可视化有向图
    @SuppressWarnings({"checkstyle:Indentation", "checkstyle:OperatorWrap", "checkstyle:WhitespaceAround"})
    public void visualizeGraph() {
        Graph graph = new SingleGraph("MyGraph");
        System.setProperty("org.graphstream.ui", "swing");
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        String stylesheet =
                "node {" +
                        "   text-mode: normal;" +
                        "   text-background-mode: plain;" +
                        "   text-background-color: rgba(255,255,255,0);" +
                        "   text-color: red;" +
                        "   fill-color: rgba(255,255,255,0);"+
                        "   text-size: 10;" +
                        "}"+
                "edge {" +
                        "   text-size: 10;" +
                        "   text-color: black;" +
                        "}";

        graphPicture.setAttribute("ui.stylesheet", stylesheet);
        graphPicture.display();

    }

    // 将文本转化为有向图
    @SuppressWarnings("checkstyle:Indentation")
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
    @SuppressWarnings("checkstyle:Indentation")
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

    @SuppressWarnings("checkstyle:Indentation")
    public  Map<String, Map<String, Integer>> getGraph () {
        return graph;
    }
}
