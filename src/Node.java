import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Node {
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

    public int maxValue() {

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

    public Point2D.Double getLocation() {
        return location;
    }

    public ArrayList<Node> getNodeList() {
        return children;
    }

    public void addNode(Node node) {
        children.add(node);
    }

    public String toString() {
        return ("X: " + location.x + " Y: " + location.y + " Value: " + value);
    }

}
