package Lab1;
import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.BufferedWriter;

public class RandomGraphTraversal {
    private Map<String, Map<String, Integer>> graph = new HashMap<>();

    public RandomGraphTraversal(DirectedGraph graphGraph) {
        this.graph = graphGraph.getGraph();
    }

    public void randomWalk() {
        // 随机选择起点
        List<String> nodes = new ArrayList<>(graph.keySet());
        String startNode = nodes.get(new Random().nextInt(nodes.size()));
        System.out.println("随机游走的起点：" + startNode);

        // 遍历图
        List<String> visitedEdges = new ArrayList<>();
        String currentNode = startNode;
        boolean stop = false; // 用户停止标志
        List<String> path = new ArrayList<>(); // 记录遍历的路径
        while (!stop && graph.containsKey(currentNode) && !graph.get(currentNode).isEmpty()) {
            Map<String, Integer> edges = graph.get(currentNode);
            List<String> adjacentNodes = new ArrayList<>(edges.keySet());
            String nextNode = adjacentNodes.get(new Random().nextInt(adjacentNodes.size()));
            path.add(currentNode);
            String edge = currentNode + "→" + nextNode;
            if (visitedEdges.contains(edge)) {
                break; // 如果边已经被访问过，停止遍历
            }

            visitedEdges.add(edge);
            currentNode = nextNode;
            // 例如，可以通过检查用户输入的特定字符来停止遍历
            Scanner scanner = new Scanner(System.in);
            System.out.println("当前节点: " + currentNode + ". 输入'#'以结束遍历:");
            if (scanner.nextLine().equals("#")) {
                stop = true;
            }
        }
        path.add(currentNode); // 记录最后一个节点
        // 输出遍历的边
        System.out.println("遍历的节点: " + String.join(" ", path));
        // 将遍历的边写入文件
        writeToFile(path);
    }

    private static void writeToFile(List<String> path) {
        String filePath = "src/output/random_walk.txt";
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("文件创建成功: " + filePath);
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String line : path) {
                    writer.write(line+" ");// 添加新行
                }
                System.out.println("内容已写入文件");
            }
        } catch (IOException e) {
            System.out.println("发生错误: " + e.getMessage());
        }
    }
}

