import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RacingGems {
    private File file;
    private ArrayList<Node> nodes;
    private int numNodes;
    private double speedRatio;
    private double widthTrack;
    private double heightTrack;

    /**
     * Constructor to read in txt file and extract racing gem info
     * @param path string path of file to be read
     */
    public RacingGems(String path) {
        this.file = new File(path);
        nodes = new ArrayList<>();
        read();
    }

    /**
     * Method to read in values from given file
     */
    private void read() {
        try(Scanner input = new Scanner(file)) {
            // Fill in first line stats
            input.useDelimiter("\\s|,");
            numNodes = input.nextInt();
            speedRatio = input.nextDouble();
            widthTrack = input.nextDouble();
            heightTrack = input.nextDouble();

            // Get gem positions/values
            while (input.hasNextLine()) {
                double gemXPosition = input.nextDouble();
                double gemYPosition = input.nextDouble();
                int value = input.nextInt();
                Node gem = new Node(gemXPosition, gemYPosition, value);
                nodes.add(gem);
            }

            for (Node node: nodes) {
                System.out.println(node);
            }

        }catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    /**
     * Method to add all paths to the nodeList
     */
    public void createPath() {
        //Create all possible paths from each node
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i+1; j < nodes.size(); j++) {
                // If you can grab the next gem from the current position
                if (nodes.get(i).canGrab(nodes.get(j), speedRatio))
                    //Create the directed path
                    nodes.get(i).addNode(nodes.get(j));
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public int getNumNodes() { return numNodes; }
    public double getSpeedRatio() { return speedRatio; }

    @Override
    public String toString() {
        return ("Number Of Gems: " + numNodes + "\nSpeed Ratio: " + speedRatio + "\nHeight of Track: " + heightTrack);
    }

}
