package basic.pack.service.gameService;

import basic.pack.models.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FigureService {

    private ArrayList<Direction> directionsForBlackChecker = Stream.of(Direction.LEFT_UP, Direction.RIGHT_UP).
            collect(Collectors.toCollection(ArrayList::new));

    private ArrayList<Direction> directionsForWhiteChecker = Stream.of(Direction.LEFT_DOWN, Direction.RIGHT_DOWN).
            collect(Collectors.toCollection(ArrayList::new));

    private ArrayList<Direction> directionsForDame = Stream.of(Direction.LEFT_DOWN, Direction.RIGHT_DOWN, Direction.LEFT_UP, Direction.RIGHT_UP).
            collect(Collectors.toCollection(ArrayList::new));


    public void createDame(LinkedList<Figure> team){
        for (Figure figure : team) {
            if (figure.getDame()) {
                figure.setSteps(Constants.HEIGHT);
                figure.setSymbol("D");
                figure.setAllowedDirection(directionsForDame);
            }
        }
    }

    public LinkedList<Node> getNodesToStep(Figure figure){
        CheckService check = new CheckService();
        BattlegroundService bg = BattlegroundService.getBattlegroundService();
        LinkedList<Node> nodesToStep = new LinkedList<>();
        Node currentNode = figure.getNode();

        for (int x = 0; x < Constants.WIDTH; x++) {
            for (int y = 0; y < Constants.HEIGHT; y++) {
                if (check.canDoStep(currentNode, Graph.getGraph().getNodes().get(x).get(y))) {
                    nodesToStep.add(Graph.getGraph().getNodes().get(x).get(y));
                }
            }
        }
        return nodesToStep;
    }

    public LinkedList<Node> getNodesToAttack(Figure figure){
        CheckService check = new CheckService();
        BattlegroundService bg = BattlegroundService.getBattlegroundService();
        LinkedList<Node> nodesToAttack = new LinkedList<>();
        Node currentNode = figure.getNode();

        for (int x = 0; x < Constants.WIDTH; x++) {
            for (int y = 0; y < Constants.HEIGHT; y++) {
                if (check.canDoAttack(currentNode, Graph.getGraph().getNodes().get(x).get(y))) {
                    nodesToAttack.add(Graph.getGraph().getNodes().get(x).get(y));
                }
            }
        }
        return nodesToAttack;
    }

    public ArrayList<Direction> getDirectionsForWhiteChecker() {
        return directionsForWhiteChecker;
    }

    public ArrayList<Direction> getDirectionsForBlackChecker() {
        return directionsForBlackChecker;
    }


}
