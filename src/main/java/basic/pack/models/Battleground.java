package basic.pack.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Battleground {

    private static Battleground battleground = new Battleground();
    private Map<Node, Figure> nodeToFigureMap = new HashMap<>();
    private Map<Figure, Node> figureToNodeMap = new HashMap<>();
    private LinkedList<Figure> blackTeam = new LinkedList<>();
    private LinkedList<Figure> whiteTeam = new LinkedList<>();
    private ArrayList<ArrayList<Node>> nodes = new ArrayList<>();



    public LinkedList<Figure> getBlackTeam() {
        return blackTeam;
    }

    public LinkedList<Figure> getWhiteTeam() {
        return whiteTeam;
    }

    public Map<Node, Figure> getNodeToFigureMap() {
        return nodeToFigureMap;
    }

    public Map<Figure, Node> getFigureToNodeMap() {
        return figureToNodeMap;
    }

    public static Battleground getBattleground() {
        return battleground;
    }
    public ArrayList<ArrayList<Node>> getNodes() {
        return nodes;
    }
}
