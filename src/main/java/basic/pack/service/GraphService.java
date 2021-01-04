package basic.pack.service;

import basic.pack.models.Constants;
import basic.pack.models.Direction;
import basic.pack.models.Node;
import basic.pack.service.gameService.CheckService;

import java.util.ArrayList;

public class GraphService {

    CheckService check = new CheckService();

    public ArrayList<ArrayList<Node>> initField(ArrayList<ArrayList<Node>> nodes) {
        buildField(nodes);
        buildNeighbors(nodes);
        return nodes;
    }

    private void buildField(ArrayList<ArrayList<Node>> nodes) {
        for (int x = 0; x < Constants.WIDTH; x++) {
            ArrayList<Node> nodeArrayList = new ArrayList<>();
            for (int y = 0; y < Constants.HEIGHT; y++) {
                nodeArrayList.add((new Node(y,x)));
            }
            nodes.add(nodeArrayList);
        }
    }

    private void buildNeighbors(ArrayList<ArrayList<Node>> nodes) {
        for (int x = 0; x < Constants.WIDTH - 1; x++) {
            for (int y = 0; y < Constants.HEIGHT - 1; y++) {
                if (x > 0) {
                    addNeighbor(nodes, nodes.get(x).get(y), x - 1, y + 1);
                }
                if (y > 0) {
                    addNeighbor(nodes, nodes.get(x).get(y), x + 1, y - 1);
                }
                if ((x > 0) && (y > 0)) {
                    addNeighbor(nodes, nodes.get(x).get(y), x - 1, y - 1);
                }
                addNeighbor(nodes, nodes.get(x).get(y), x + 1, y + 1);
            }
        }
    }

    private void addNeighbor(ArrayList<ArrayList<Node>> nodes, Node curNode, int neighborX, int neighborY) {
        Direction direction = check.defineDirection(curNode, nodes.get(neighborX).get(neighborY));
        curNode.getNeighbors().put(direction, nodes.get(neighborX).get(neighborY));
    }
}