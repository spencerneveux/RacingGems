import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Node implements Comparable<Node>{
    private int value;
    private int maxValue;
    private Point2D.Double location;
    private ArrayList<Node> children;

    /**
     * Constructor for Nodes
     * @param x position of node
     * @param y position of node
     * @param value of node
     */
    public Node(double x, double y, int value) {
        location = new Point2D.Double(x, y);
        this.value = value;
        this.maxValue = 0;
        children = new ArrayList<>();
    }

    /**
     * Method to see if you can get to a node from you current spot
     * with your given horizontal max speed
     * @param nextNode the next node to try and grab
     * @param speedRatio the speed that can be obtained
     * @return boolean value if you can grab the next node
     */
    public boolean canGrab(Node nextNode, double speedRatio) {
        if (nextNode.location.y > this.location.y) {
            double xDistance = Math.abs(nextNode.location.x - this.location.x);
            double xSpeed = (nextNode.location.y - this.location.y) / speedRatio;
            if (xDistance <= xSpeed)
                return true;
        }
        return false;
    }

    public int getValue() {
        return value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public ArrayList<Node> getNodeList() {
        return children;
    }

    public void addNode(Node node) {
        children.add(node);
    }

    @Override
    public String toString() { return ("X: " + location.x + " Y: " + location.y + " Value: " + value);}

    @Override
    public int compareTo(Node node) {
        if (this.location.y > node.location.y)
            return 1;
        if (this.location.y < node.location.y)
            return -1;
        return 0;

    }

}
