package lab1;

import java.util.*;

@SuppressWarnings({"checkstyle:Indentation", "checkstyle:MissingJavadocType"})
public class BridgeWordsFinder {
    private static Map<String, Map<String, Integer>> graph = new HashMap<>();
    public BridgeWordsFinder(DirectedGraph graphGraph) {
        this.graph = graphGraph.getGraph();
    }
    public void WordsFinder() {
        // 创建图
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter word1: ");
        String word1 = scanner.next().toLowerCase();
        System.out.print("Enter word2: ");
        String word2 = scanner.next().toLowerCase();

        // 检查输入的word1和word2是否存在于图中
        if (!graph.containsKey(word1) || !graph.containsKey(word2)) {
            System.out.println("No " + (graph.containsKey(word1) ? "word2" : "word1") + " in the graph!");
            return;
        }

        // 寻找桥接词
        List<String> bridgeWords = queryBridgeWords( word1, word2);

        // 输出结果
        if (bridgeWords.isEmpty()) {
            System.out.println("No bridge words from " + word1 + " to " + word2 + "!");
        } else {
            System.out.println("The bridge words from " + word1 + " to " + word2 + " are: " + String.join(", ", bridgeWords) + ".");
        }
    }

    // 添加边到图中
    private static void addEdge(Map<String, Map<String, Integer>> graph, String source, String destination, int weight) {
        graph.computeIfAbsent(source, k -> new HashMap<>()).put(destination, weight);
    }

    // 寻找桥接词
    public static List<String> queryBridgeWords(String word1, String word2) {
        List<String> bridgeWords = new ArrayList<>();
        // 遍历word1的所有邻居节点，查找共同的邻居作为桥接词
        Map<String, Integer> neighborsOfWord1 = graph.get(word1);
        if (neighborsOfWord1 != null) {
            for (String neighbor : neighborsOfWord1.keySet()) {
                if (graph.containsKey(neighbor) && graph.get(neighbor).containsKey(word2)) {
                    bridgeWords.add(neighbor);
                }
            }
        }
        return bridgeWords;
    }

    public String generateNewText(String text) {
        StringBuilder result = new StringBuilder();
        String[] words = text.split("\\s+");

        Random rand = new Random();

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            result.append(word1).append(" ");

            // Check if there are bridge words between word1 and word2
            List<String> bridgeWords = queryBridgeWords(word1, word2);
            if (!bridgeWords.isEmpty()) {
                // Randomly select one bridge word to insert between word1 and word2
                String bridge = bridgeWords.get(rand.nextInt(bridgeWords.size()));
                result.append(bridge).append(" ");
            }
        }

        result.append(words[words.length - 1]); // Append the last word
        return result.toString();
    }
}

