package basic.pack.service.gameService;

import basic.pack.models.*;

import java.util.LinkedList;
import java.util.Map;

public class BattlegroundService {

    private static BattlegroundService battlegroundService = new BattlegroundService();
    private FigureService figureService = new FigureService();

    public static BattlegroundService getBattlegroundService() {
        return battlegroundService;
    }

    public BattlegroundService() {
        initFigure();
    }

    private void initFigure() {
        Battleground battleground = Battleground.getBattleground();

        initBlackTeam(battleground.getBlackTeam(), battleground.getNodeToFigureMap(), battleground.getFigureToNodeMap());
        initWhiteTeam(battleground.getWhiteTeam(),  battleground.getNodeToFigureMap(), battleground.getFigureToNodeMap());

    }

    private void initBlackTeam(LinkedList<Figure> blackTeam, Map<Node, Figure> nodeToFigureMap, Map<Figure, Node> figureToNodeMap) {
        for (int y = 0; y < Constants.HEIGHT - 1; y++) {
            for (int x = 0; x < Constants.WIDTH - 1; x++) {
                while (x < 3) {
                    if (x % 2 != 0 && y % 2 == 0) {
                        putFigure(new Checker(Graph.getGraph().getNodes().get(y).get(x)), blackTeam,nodeToFigureMap, figureToNodeMap);
                    }
                    if (x % 2 == 0 && y % 2 != 0) {
                        putFigure(new Checker(Graph.getGraph().getNodes().get(y).get(x)), blackTeam, nodeToFigureMap, figureToNodeMap);
                    }
                    x++;
                }
            }
        }
        initBlackDirection(blackTeam);
    }

    private void initWhiteTeam(LinkedList<Figure> whiteTeam, Map<Node, Figure> nodeToFigureMap, Map<Figure, Node> figureToNodeMap) {
        for (int x = Constants.WIDTH - 2; x >= 0; x--) {
            for (int y = Constants.HEIGHT - 2; y >= 0; y--) {
                while (y > Constants.HEIGHT - 5) {
                    if (y % 2 != 0 && x % 2 == 0) {
                        putFigure(new Checker(Graph.getGraph().getNodes().get(x).get(y)), whiteTeam, nodeToFigureMap, figureToNodeMap);
                    }
                    if (y % 2 == 0 && x % 2 != 0) {
                        putFigure(new Checker(Graph.getGraph().getNodes().get(x).get(y)), whiteTeam, nodeToFigureMap, figureToNodeMap);
                    }
                    y--;
                }
            }
        }
        initWhiteDirection(whiteTeam);
    }

    private void putFigure(Figure figure, LinkedList<Figure> team,
                           Map<Node, Figure> nodeToFigureMap,
                           Map<Figure, Node> figureToNodeMap) {
        team.add(figure);
        nodeToFigureMap.put(figure.getNode(), figure);
        figureToNodeMap.put(figure, figure.getNode());
    }


    private void initWhiteDirection( LinkedList<Figure> whiteTeam) {
        for (int i = 0; i < whiteTeam.size(); i++) {
            whiteTeam.get(i).setAllowedDirection(figureService.getDirectionsForWhiteChecker());
        }
    }

    private void initBlackDirection( LinkedList<Figure> blackTeam) {
        for (int i = 0; i < blackTeam.size(); i++) {
            blackTeam.get(i).setAllowedDirection(figureService.getDirectionsForBlackChecker());
        }
    }

    public LinkedList<Figure> getNodeToTeam(Node n, Map<Node, Figure> nodeToFigureMap,
                                            LinkedList<Figure> blackTeam,
                                            LinkedList<Figure> whiteTeam) {
        Figure f = nodeToFigureMap.get(n);
        if (blackTeam.contains(f)) {
            return blackTeam;
        }
        if (whiteTeam.contains(f)) {
            return whiteTeam;
        }
        return null;
    }

}