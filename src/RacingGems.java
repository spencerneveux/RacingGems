import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RacingGems {
    private File file;
    private ArrayList<Node> gems;
    private int numGems;
    private double speedRatio;
    private double widthTrack;
    private double heightTrack;

    /**
     * Constructor to read in txt file and extract racing gem info
     * @param path string path of file to be read
     */
    public RacingGems(String path) {
        this.file = new File(path);
        gems = new ArrayList<>();
        read();
    }

    /**
     * Method to read in values from given file
     */
    private void read() {
        try(Scanner input = new Scanner(file)) {
            // Fill in first line stats
            input.useDelimiter("\\s|,");
            numGems = input.nextInt();
            speedRatio = input.nextDouble();
            widthTrack = input.nextDouble();
            heightTrack = input.nextDouble();

            // Get gem positions/values
            while (input.hasNextLine()) {
                double gemXPosition = input.nextDouble();
                double gemYPosition = input.nextDouble();
                int value = input.nextInt();
                Node gem = new Node(gemXPosition, gemYPosition, value);
                gems.add(gem);
            }

            for (Node node: gems) {
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
        if (gems.size() != 0) {
            //Run through all the gems and see which ones can be grabbed
            for (int i = 0; i < gems.size(); i++) {
                for (int j = i+1; j < gems.size(); j++) {
                    // If you can grab the next gem from the current position
                    if (gems.get(i).canGrab(gems.get(j), speedRatio))
                        gems.get(i).addNode(gems.get(j));
                }
            }
        }
    }


}
