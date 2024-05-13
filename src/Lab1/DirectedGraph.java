package Lab1;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DirectedGraph {
    private Map<String, Map<String, Integer>> graph = new HashMap<>();

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
    public void printGraph() {
        for (String word : graph.keySet()) {
            System.out.print(word + " -> ");
            Map<String, Integer> edges = graph.get(word);
            for (String adjacentWord : edges.keySet()) {
                System.out.print(adjacentWord + "(" + edges.get(adjacentWord) + ") ");
            }
            System.out.println();
        }
    }
}