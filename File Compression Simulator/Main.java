import java.io.*;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("=== File Compression Simulator (RLE) ===");
            System.out.println("1. Compress File");
            System.out.println("2. Decompress File");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    compressFile("input.txt", "compressed.txt");
                    break;
                case 2:
                    decompressFile("compressed.txt", "decompressed.txt");
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println(); // blank line for readability
        }
    }

    // =========================
    // 🔹 Compression Method
    // =========================
    public static void compressFile(String inputFile, String outputFile) {  
        try {
            // Read entire content of input file
            String content = readFile(inputFile);

            if (content.isEmpty()) {
                System.out.println("Input file is empty.");
                return;
            }

            StringBuilder compressed = new StringBuilder();

            int count = 1;  
            for (int i = 0; i < content.length(); i++) {
                if (i + 1 < content.length() && content.charAt(i) == content.charAt(i + 1)) {
                    count++;
                } else {
                    // append character + count
                    compressed.append(content.charAt(i)).append(count);
                    count = 1;
                }
            }

            // Write the compressed content to output file
            writeFile(outputFile, compressed.toString());

            System.out.println("Compression successful!");
            System.out.println("Output saved to " + outputFile);
            System.out.println("Compressed Content: " + compressed.toString());

        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

    // =========================
    // 🔹 Decompression Method
    // =========================
    public static void decompressFile(String inputFile, String outputFile) {
        try {
            String compressedContent = readFile(inputFile);

            if (compressedContent.isEmpty()) {
                System.out.println("Compressed file is empty.");
                return;
            }

            StringBuilder decompressed = new StringBuilder();

            for (int i = 0; i < compressedContent.length(); i += 2) {
                char ch = compressedContent.charAt(i);
                // Validate next character is a digit
                if (i + 1 >= compressedContent.length() || !Character.isDigit(compressedContent.charAt(i + 1))) {
                    System.out.println("Invalid RLE format. Stopping decompression.");
                    return;
                }

                int count = Character.getNumericValue(compressedContent.charAt(i + 1));

                for (int j = 0; j < count; j++) {
                    decompressed.append(ch);
                }
            }

            writeFile(outputFile, decompressed.toString());

            System.out.println("Decompression successful!");
            System.out.println("Output saved to " + outputFile);
            System.out.println("Decompressed Content: " + decompressed.toString());

        } catch (IOException e) {
            System.out.println("Error reading/writing file: " + e.getMessage());
        }
    }

    // =========================
    // 🔹 Helper Functions
    // =========================

    // Read text from file
    private static String readFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println(fileName + " not found!");
            return "";
        }

        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }

    // Write text to file
    private static void writeFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(data);
        writer.close();
    }
}
