package basic.pack.models;


import basic.pack.service.GraphService;

import java.util.ArrayList;

public class Graph {

    private static Graph graph = new Graph();
    private ArrayList<ArrayList<Node>> nodes = new ArrayList<>();


    public static Graph getGraph() {
        return graph;
    }

    public ArrayList<ArrayList<Node>> getNodes() {
        return new GraphService().initField(nodes);
    }

    public void setNodes(ArrayList<ArrayList<Node>> nodes) {
        this.nodes = nodes;
    }

}