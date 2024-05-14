package Lab1;

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 假设文件路径是固定的，或者你可以从用户那里获取它
        String filePath = "D:\\desktop\\Java\\test\\data\\text.txt"; // 替换为你的文件路径
        String finalText = "";
       try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder processedText = new StringBuilder();
            String line;
            // 逐行读取文件
            while ((line = reader.readLine()) != null) {
                // 使用正则表达式替换非字母字符（包括标点符号和换行符）为空格
                String processedLine = line.replaceAll("[^A-Za-z]+", " ").toLowerCase();
                // 移除行首和行尾可能存在的多余空格，并追加到StringBuilder中
                processedText.append(processedLine.trim()).append(" ");
            }
            // 移除StringBuilder末尾的额外空格
            finalText = processedText.toString().trim();
        } catch (IOException e) {
            System.err.println("无法读取文件: " + e.getMessage());
        }
        DirectedGraph graph = new DirectedGraph();
        graph.createGraphFromText(finalText);

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("选择你要的功能");
            System.out.println("1.打印有向图");
            System.out.println("2.查询桥接词");
            System.out.println("3.桥接词生成文本");
            System.out.println("4.计算最短路径");
            System.out.println("5.随机游走");
            System.out.println("6.退出");
            int choice = input.nextInt();
            switch (choice) {
                case 1: //功能1
                    graph.printGraph();
                    break;
                case 2: //功能2
                    break;
                case 3: //功能3
                    break;
                case 4: //功能4
                    DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("请输入起点单词:");
                    String start = scanner.nextLine();
                    System.out.println("请输入终点单词:");
                    String end = scanner.nextLine();
                    if (end.equals("#")) {
                        Map<String, String> allShortestPaths = dijkstra.findAllShortestPaths(start);
                        allShortestPaths.forEach((endpoint, path) -> {
                            if (path.equals("不可达")) {
                                System.out.println(start + " 到 " + endpoint + " " + "不可达");
                            } else {
                                System.out.println("从 " + start + " 到 " + endpoint + " 的最短路径为: " + path);
                            }
                        });
                    } else {
                        String shortestPath = dijkstra.findShortestPath(start, end);
                        System.out.println(shortestPath.equals("不可达") ? "不可达" : "最短路径为: " + shortestPath);
                    }
                    break;
                case 5: //功能5

                    break;
                case 6: //功能6
                    return;
                default: //其他功能
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }
}