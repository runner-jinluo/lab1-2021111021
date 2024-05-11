import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // 假设文件路径是固定的，或者你可以从用户那里获取它
        String filePath = "D:\\desktop\\Java\\test\\data\\text.txt"; // 替换为你的文件路径

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
            String finalText = processedText.toString().trim();
            // 打印处理后的文本
            System.out.println(finalText);

        } catch (IOException e) {
            System.err.println("无法读取文件: " + e.getMessage());
        }
    }
}