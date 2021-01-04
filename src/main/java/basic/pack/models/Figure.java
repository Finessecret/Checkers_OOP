package basic.pack.models;


import java.util.ArrayList;

public class Figure {

    private Node currentNode;
    private String symbol;
    private ArrayList<Direction> allowedDirection;
    private boolean isDame = false;
    private int steps = 1;

    public ArrayList<Direction> getAllowedDirection() {
        return allowedDirection;
    }

    public void setAllowedDirection(ArrayList<Direction> allowedDirection) {
        this.allowedDirection = allowedDirection;
    }

    public boolean isDame() {
        return isDame;
    }

    public boolean getDame() {
       return isDame;
    }

    public void setDame(boolean dame) {
        isDame = dame;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public Figure(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getNode() {
        return currentNode;
    }

    public void setNode(Node currentNodeModel) {
        this.currentNode = currentNodeModel;
    }

    public String getSymbol() {

        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getSteps() {
        return steps;
    }
}
