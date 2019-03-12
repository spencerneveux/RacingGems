import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private int numNodes;
    private int[][] DAG;

    /**
     * Constructor for Graph. Instantiates the 2D Dag
     * with num nodes
     * @param numNodes the number of nodes that this graph will have
     */
    public Graph(int numNodes) {
        DAG = new int[numNodes + 1][numNodes + 1];
    }

    /**
     * Method to create a DAG from given nodes
     * @param nodes a list of all the nodes
     * @param speedRatio the ratio of speed, used to determine if we can reach a node or not
     */
    public void createDag(ArrayList<Node> nodes, double speedRatio) {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i == j)
                    DAG[i][j] = nodes.get(i).getValue();
                else if (nodes.get(i).canGrab(nodes.get(j), speedRatio))
                    DAG[i][j] = nodes.get(i).getValue() + nodes.get(j).getValue();
                else
                    DAG[i][j] = Integer.MIN_VALUE;
            }
        }
    }

    public void createAPSP(int[][] dag) {
        int[][] distance = new int[numNodes][numNodes];

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    distance[i][j] = Math.max(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        printSolution(distance);
    }

    public void printSolution(int dist[][])
    {
        System.out.println("The following matrix shows the shortest "+
                "distances between every pair of vertices");
        for (int i=0; i< numNodes; ++i)
        {
            for (int j=0; j< numNodes; ++j)
            {
                if (dist[i][j]==Integer.MIN_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+"   ");
            }
            System.out.println();
        }
    }

    public int[][] getDAG() { return DAG; }

    @Override
    public String toString() {
        return (Arrays.deepToString(DAG));
    }
}
