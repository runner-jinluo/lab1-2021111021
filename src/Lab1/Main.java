package Lab1;

import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.util.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Main {

    public static void main(String[] args) {
        // 假设文件路径是固定的，或者你可以从用户那里获取它
        String filePath = "src/data/data.txt";
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
        BridgeWordsFinder bridgeWordsFinder = new BridgeWordsFinder(graph);
        Scanner input = new Scanner(System.in);
        //graph.CheckSwing();

        while(true) {
            System.out.println("选择你要的功能");
            System.out.println("1.打印有向图");
            System.out.println("2.查询桥接词");
            System.out.println("3.桥接词生成文本");
            System.out.println("4.计算最短路径");
            System.out.println("5.随机游走");
            System.out.println("6.退出");
            //Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            //Scanner.close();
            switch (choice) {
                case 1: //功能1
                    /*graph.createGraphPictureFromText();
                    graph.visualizeGraph();*/
                    graph.printGraph();
                    //DirectedWeightedGraphVisualization visualization = new DirectedWeightedGraphVisualization(graph.graph);
                    //visualization.visualize();
                    break;
                case 2: //功能2
                    bridgeWordsFinder.WordsFinder();
                    break;
                case 3: //功能3
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("请输入文本：");
                    String inputText = scanner.nextLine();
                    System.out.println("您输入的文本是：" + inputText);
                    scanner.close();
                    String outputText = bridgeWordsFinder.generateBridgeWords(inputText);
                    System.out.println("Input Text: " + inputText);
                    System.out.println("Output Text: " + outputText);
                    break;
                case 4: //功能4
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