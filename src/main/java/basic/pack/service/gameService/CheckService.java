package basic.pack.service.gameService;

import basic.pack.models.*;

public class CheckService {

    private BattlegroundService bg = BattlegroundService.getBattlegroundService();

    public boolean amountSteps(Node from, Node to) {
        Figure f = Battleground.getBattleground().getNodeToFigureMap().get(Graph.getGraph().getNodes().get(from.getNodeX()).get(from.getNodeY()));
        return (Math.abs(from.getNodeX() - to.getNodeX()) <= f.getSteps()) && (Math.abs(from.getNodeY() - to.getNodeY()) <= f.getSteps())
                && (to.getNodeY() < Constants.HEIGHT - 1)
                && (to.getNodeY() >= 0)
                && (to.getNodeX() < Constants.WIDTH - 1)
                && (to.getNodeX() >= 0);
    }

    public boolean canDoStep(Node from, Node to) {

        return !isNull(from)
                && !isNull(to)
                && !employment(to)
                && employment(from)
                && amountSteps(from, to)
                && wayIsClear(from, to)
                && direction(from, to);
    }

    private boolean isNull(Node node) {
        return node == null;
    }

    public boolean canDoAttack(Node from, Node to) {

        return !isNull(from)
                && !isNull(to)
                && employment(from)
                && employment(to)
                && !teamsEqueals(from, to)
                && !employment(to.getNeighbors().get(defineDirection(from, to)))
                && amountSteps(from, to)
                && direction(from, to)
                && !isBounds(to);
    }

    private boolean isBounds(Node node) {
        return (node.getNodeX() == 0) || (node.getNodeY() == 0) || (node.getNodeX() == Constants.WIDTH - 2) || (node.getNodeY() == Constants.HEIGHT - 2);
    }

    public boolean isBlackTeam(Figure figure) {
        return Battleground.getBattleground().getBlackTeam().contains(figure);
    }

    public boolean isWhiteTeam(Figure figure) {
        return Battleground.getBattleground().getWhiteTeam().contains(figure);
    }

    private boolean employment(Node to) {
        return Battleground.getBattleground().getNodeToFigureMap().get(to) != null;
    }

    private boolean direction(Node from, Node to) {
        return Battleground.getBattleground().getNodeToFigureMap().get(from).getAllowedDirection().contains(defineDirection(from, to));
    }

    private boolean wayIsClear(Node from, Node to) {
        BattlegroundService bg = BattlegroundService.getBattlegroundService();
        int i = 0;
        Direction direction = diagonal(from, to);

        direction = diagonal(from, to);
        if (direction == Direction.LEFT_UP) {
            while (Graph.getGraph().getNodes().get(from.getNodeX() - i).get(from.getNodeY() + i) != to) {
                i++;
                if (employment(Graph.getGraph().getNodes().get(from.getNodeX() - i).get(from.getNodeY() + i))) {
                    return false;
                }
            }
            return true;
        }
        if (direction == Direction.LEFT_DOWN) {
            while (Graph.getGraph().getNodes().get(from.getNodeX() - i).get(from.getNodeY() - i) != to) {
                i++;
                if (employment(Graph.getGraph().getNodes().get(from.getNodeX() - i).get(from.getNodeY() - i))) {
                    return false;
                }
            }
            return true;
        }
        if (direction == Direction.RIGHT_UP) {
            while (Graph.getGraph().getNodes().get(from.getNodeX() + i).get(from.getNodeY() + i) != to) {
                i++;
                if (employment(Graph.getGraph().getNodes().get(from.getNodeX() + i).get(from.getNodeY() + i))) {
                    return false;
                }
            }
            return true;
        }
        if (direction == Direction.RIGHT_DOWN) {
            while (Graph.getGraph().getNodes().get(from.getNodeX() + i).get(from.getNodeY() - i) != to) {
                i++;
                if (employment(Graph.getGraph().getNodes().get(from.getNodeX() + i).get(from.getNodeY() - i))) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private boolean teamsEqueals(Node from, Node to) {
        return bg.getNodeToTeam(from, Battleground.getBattleground().getNodeToFigureMap(),
                Battleground.getBattleground().getBlackTeam(), Battleground.getBattleground().getWhiteTeam()).equals(
                bg.getNodeToTeam(to, Battleground.getBattleground().getNodeToFigureMap(),
                        Battleground.getBattleground().getBlackTeam(), Battleground.getBattleground().getWhiteTeam()));
    }

    public Direction defineReverseDirection(Node from, Node to) {
        if (Math.abs(from.getNodeX() - to.getNodeX()) != Math.abs(from.getNodeY() - to.getNodeY())) {
            return null;
        }
        if ((from.getNodeX() < to.getNodeX()) && (from.getNodeY() < to.getNodeY())) {
            return Direction.LEFT_DOWN;
        }
        if ((from.getNodeX() > to.getNodeX()) && (from.getNodeY() > to.getNodeY())) {
            return Direction.RIGHT_UP;
        }
        if ((from.getNodeX() < to.getNodeX()) && (from.getNodeY() > to.getNodeY())) {
            return Direction.LEFT_UP;
        }
        if ((from.getNodeX() > to.getNodeX()) && (from.getNodeY() < to.getNodeY())) {
            return Direction.RIGHT_DOWN;
        }
        return null;
    }

    public Direction defineDirection(Node from, Node to) {
        if (diagonal(from, to) != null) {
            return diagonal(from, to);
        }
        return null;
    }

    public Direction diagonal(Node from, Node to) {
        if (Math.abs(from.getNodeX() - to.getNodeX()) != Math.abs(from.getNodeY() - to.getNodeY())) {
            return null;
        }
        if ((from.getNodeX() < to.getNodeX()) && (from.getNodeY() < to.getNodeY())) {
            return Direction.RIGHT_UP;
        }
        if ((from.getNodeX() > to.getNodeX()) && (from.getNodeY() > to.getNodeY())) {
            return Direction.LEFT_DOWN;
        }
        if ((from.getNodeX() < to.getNodeX()) && (from.getNodeY() > to.getNodeY())) {
            return Direction.RIGHT_DOWN;
        }
        if ((from.getNodeX() > to.getNodeX()) && (from.getNodeY() < to.getNodeY())) {
            return Direction.LEFT_UP;
        }
        return null;
    }

    public boolean canMakeDame(Figure figure) {

        if (isWhiteTeam(figure) && figure != null) {
            return figure.getNode().getNodeY() == 0;
        }
        if (isBlackTeam(figure) && figure != null) {
            return figure.getNode().getNodeY() == Constants.HEIGHT - 2;
        }
        return false;
    }
}