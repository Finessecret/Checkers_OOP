package basic.pack.models;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private Map<Direction, Node> neighbors = new HashMap<>();
    private int x, y;

    public Node(int x, int y) {
        this.x = y;
        this.y = x;
    }

    public Map<Direction, Node> getNeighbors() {
        return neighbors;
    }

    public int getNodeX() {
        return x;
    }

    public int getNodeY() {
        return y; }

    public void printNode() {
        System.out.print(x + " ");
        System.out.print(y + " ");
    }
}


