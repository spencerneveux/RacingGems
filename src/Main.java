import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ArrayList<Node> nodes = new ArrayList<>();
        int numberNodes = 0;
        double speedRatio = 0;

        //Get info from file
        try (Scanner input = new Scanner(new File("src/racingGems2.txt"))) {
            input.useDelimiter("\\s|,");
            numberNodes = input.nextInt();
            speedRatio = input.nextDouble();
            double widthTrack = input.nextDouble();
            double heightTrack = input.nextDouble();

            while (input.hasNextLine()) {
                double x = input.nextDouble();
                double y = input.nextDouble();
                int value = -input.nextInt();
                Node node = new Node(x, y, value);
                nodes.add(node);
            }
            nodes.add(new Node(0, 0, 0));

        } catch (FileNotFoundException ex) {
            ex.getMessage();
        }

        //Sort the nodes according to Y axis
        Collections.sort(nodes);

        //Create distance adjacency matrix
        //Create Next for memoization
        int[][] distance = new int[nodes.size()][nodes.size()];
        int[][] next = new int[nodes.size()][nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else if (i == 0)
                    distance[i][j] = nodes.get(j).getValue();
                else if (nodes.get(i).canGrab(nodes.get(j), speedRatio))
                    distance[i][j] = nodes.get(j).getValue();
                else
                    distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j)
                    next[i][j] = j + 1;
            }
        }

        //Floyd Warshall
        for (int k = 0; k < nodes.size(); k++) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    if (distance[i][k] != Integer.MAX_VALUE
                            && distance[k][j] != Integer.MAX_VALUE &&
                            distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        //Print resulting distance matrix
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                if (distance[i][j] == Integer.MAX_VALUE)
                    System.out.printf("%15S", "INF");
                else
                    System.out.printf("%15S", distance[i][j]);
            }
            System.out.println();
        }
        System.out.println("Path List");

        //Find Minimum
        int minRow = 0;
        int minColumn = 0;
        int min = distance[minRow][minColumn];

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                if (distance[i][j] < min) {
                    min = distance[i][j];
                    minRow = i;
                    minColumn = j;
                }
            }
        }

        System.out.println("The min is " + min + ". The position is (" + minRow + ", " + minColumn + ")");
        printResult(distance, next, minRow, minColumn);

    }

    static void printResult(int[][] dist, int[][] next, int minRow, int minColumn) {
        int u = minRow + 1;
        int v = minColumn + 1;
        String path = "";
        do {
            u = next[u - 1][v - 1];
            path +=  u + " -> ";
        } while (u != v);
        System.out.println(path);
    }
}


