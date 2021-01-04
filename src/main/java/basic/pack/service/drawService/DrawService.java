package basic.pack.service.drawService;

import basic.pack.models.Battleground;
import basic.pack.models.Constants;
import basic.pack.models.Graph;
import basic.pack.models.Node;
import basic.pack.service.gameService.BattlegroundService;

public class DrawService {
    public void drawBattleground(BattlegroundService bg) {
        for (int x = -1; x < Constants.WIDTH - 1; x++) {
            for (int y = -1; y < Constants.HEIGHT  - 1; y++) {
                if (isCoordinateAsix(x, y)) {
                    drawCoordinateAsix(x,y);
                } else {
                    if (isEmpty(bg, Graph.getGraph().getNodes().get(y).get(x))) {
                        printEmptyNode();
                    } else {
                        printFigure(bg, Graph.getGraph().getNodes().get(y).get(x));
                    }
                }
            }
            System.out.println();
        }
    }

    private void printEmptyNode() {
        System.out.print("| |" + "  ");
    }

    private boolean isEmpty(BattlegroundService bg, Node n) {
        return Battleground.getBattleground().getNodeToFigureMap().get(n) == null;
    }

    private void printFigure(BattlegroundService bg, Node nodeModel) {
        if (Battleground.getBattleground().getBlackTeam().contains(Battleground.getBattleground().getNodeToFigureMap().get(nodeModel))) {
            printBlackFigure(bg, nodeModel);
        }
        if (Battleground.getBattleground().getWhiteTeam().contains(Battleground.getBattleground().getNodeToFigureMap().get(nodeModel))) {
            printWhiteFigure(bg, nodeModel);
        }
    }

    private void printBlackFigure(BattlegroundService bg, Node nodeModel) {
        System.out.print((char) 27 + "[30m|" + Battleground.getBattleground().getNodeToFigureMap().get(nodeModel).getSymbol() + "|  " + (char) 27 + "[0m");
    }

    private void printWhiteFigure(BattlegroundService bg, Node nodeModel) {
        System.out.print((char) 27 + "[31m|" + Battleground.getBattleground().getNodeToFigureMap().get(nodeModel).getSymbol() + "|  " + (char) 27 + "[0m");
    }

    private boolean isCoordinateAsix(int x, int y) {
        return x == -1 || y == -1;
    }

    private void drawCoordinateAsix(int x, int y) {
        if (x == -1 && y==-1) {
            System.out.print("    ");
        }   else {
            if(x==-1 && y!= -1){
                System.out.print("|"+ (y) + "|  ");
            }else{
                System.out.print("|"+ (x) + "| ");
            }
        }
    }
}
