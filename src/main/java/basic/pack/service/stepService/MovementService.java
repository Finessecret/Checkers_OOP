package basic.pack.service.stepService;

import basic.pack.models.*;
import basic.pack.service.gameService.CheckService;

public class MovementService {

    private CheckService check = new CheckService();

    public void go(Node from, Node to) {
        int amountSteps = getAmountSteps(from, to);
        Direction direction = getDirection(from, to);

        int i = 0;
        if (direction == Direction.LEFT_UP) {
            while (i < amountSteps) {
                move(Graph.getGraph().getNodes().get(from.getNodeX() - i).get(from.getNodeY() + i),
                        Graph.getGraph().getNodes().get(from.getNodeX() - (i + 1)).get(from.getNodeY() + (i + 1)));
                i++;
                System.out.println("Ходов выполнено " + i);
            }
        }
        if (direction == Direction.LEFT_DOWN) {
            while (i < amountSteps) {
                move(Graph.getGraph().getNodes().get(from.getNodeX() - i).get(from.getNodeY() - i),
                        Graph.getGraph().getNodes().get(from.getNodeX() - (i + 1)).get(from.getNodeY() - (i + 1)));

                i++;
                System.out.println("Ходов выполнено " + i);
            }
        }
        if (direction == Direction.RIGHT_UP) {
            while (i < amountSteps) {
                move(Graph.getGraph().getNodes().get(from.getNodeX() + i).get(from.getNodeY() + i),
                        Graph.getGraph().getNodes().get(from.getNodeX() + (i + 1)).get(from.getNodeY() + (i + 1)));
                i++;
                System.out.println("Ходов выполнено " + i);
            }
        }
        if (direction == Direction.RIGHT_DOWN) {
            while (i < amountSteps) {
                move(Graph.getGraph().getNodes().get(from.getNodeX() + i).get(from.getNodeY() - i),
                        Graph.getGraph().getNodes().get(from.getNodeX() + (i + 1)).get(from.getNodeY() - (i + 1)));
                i++;
                System.out.println("Ходов выполнено " + i);
            }
        }
    }

    protected void move(Node from, Node to) {
        if (Battleground.getBattleground().getBlackTeam().contains(Battleground.getBattleground().getNodeToFigureMap().get(from))) {
            System.out.println("Команда чёрных");
        } else {
            System.out.println("Команда белых");
        }
        System.out.print("Ход из ");
        from.printNode();
        System.out.print("в ");
        to.printNode();
        Node curr = from;
        Direction direction = getDirection(from, to);
        System.out.println("Направление " + direction);
        if (!check.canDoStep(from, to)) {
            System.err.println("Ход не выполнен");
            System.out.println();
            return;
        } else {
            System.out.println("Ход выполнен");
            System.out.println();
            doStep(curr, curr.getNeighbors().get(direction));
        }
    }

    private void doStep(Node from, Node to) {
        Figure figure = Battleground.getBattleground().getNodeToFigureMap().get(Graph.getGraph().getNodes().get(from.getNodeX()).get(from.getNodeY()));
        Battleground.getBattleground().getNodeToFigureMap().put(to, figure);
        Battleground.getBattleground().getNodeToFigureMap().remove(from);
        Battleground.getBattleground().getFigureToNodeMap().put(figure, to);
        if (check.isWhiteTeam(figure)) {
            Battleground.getBattleground().getWhiteTeam().remove(figure);
            Battleground.getBattleground().getNodeToFigureMap().get(to).setNode(to);
            Battleground.getBattleground().getWhiteTeam().add(Battleground.getBattleground().getNodeToFigureMap().get(to));
        }
        if (check.isBlackTeam(figure)) {
            Battleground.getBattleground().getBlackTeam().remove(figure);
            Battleground.getBattleground().getNodeToFigureMap().get(to).setNode(to);
            Battleground.getBattleground().getBlackTeam().add(Battleground.getBattleground().getNodeToFigureMap().get(to));
        }
    }

    private Direction getDirection(Node from, Node to) {
        return check.diagonal(from, to);
    }

    private int getAmountSteps(Node from, Node to) {

        if (check.amountSteps(from, to)) {

            if (Math.abs(from.getNodeX() - to.getNodeX()) == 0) {
                return Math.abs(from.getNodeY() - to.getNodeY());
            }
            if (Math.abs(from.getNodeY() - to.getNodeY()) == 0) {
                return Math.abs(from.getNodeX() - to.getNodeX());
            }
            if (Math.abs(from.getNodeY() - to.getNodeY()) != 0 && Math.abs(from.getNodeX() - to.getNodeX()) != 0) {
                return Math.abs(from.getNodeX() - to.getNodeX());
            }
        }
        return -1;
    }
}
