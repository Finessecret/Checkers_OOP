package basic.pack.service.stepService;

import basic.pack.models.Battleground;
import basic.pack.models.Figure;
import basic.pack.models.Graph;
import basic.pack.models.Node;
import basic.pack.service.gameService.CheckService;

public class AttackService {

    private CheckService check = new CheckService();

    public void attack(Node from, Node to) {
        System.out.print("Атака из ");
        from.printNode();
        System.out.print("в ");
        to.printNode();
        System.out.println();
        if(check.canDoAttack(from, to)){
            doAttack(from, to);
            System.out.println("Attack is successful");
        }else {
            System.err.println("Attack is dead");
        }
    }

    private void doAttack(Node from, Node to){
        Figure figure = Battleground.getBattleground().getNodeToFigureMap().get(Graph.getGraph().getNodes().get(from.getNodeX()).get(from.getNodeY()));
        Figure attackedFigure = Battleground.getBattleground().getNodeToFigureMap().get(to);
        Battleground.getBattleground().getNodeToFigureMap().put(to.getNeighbors().get(check.defineDirection(from, to)), figure);
        Battleground.getBattleground().getNodeToFigureMap().remove(from); // удаление себя
        Battleground.getBattleground().getNodeToFigureMap().remove(to); // удаление противника
        Battleground.getBattleground().getFigureToNodeMap().put(figure, to.getNeighbors().get(check.defineDirection(from, to)));
        if (check.isBlackTeam(figure)) {
            Battleground.getBattleground().getBlackTeam().remove(figure);
            Battleground.getBattleground().getNodeToFigureMap().get(to.getNeighbors().get(check.defineDirection(from, to))).setNode(to.getNeighbors().get(check.defineDirection(from, to)));
            Battleground.getBattleground().getBlackTeam().add(Battleground.getBattleground().getNodeToFigureMap().get(to.getNeighbors().get(check.defineDirection(from, to))));
            Battleground.getBattleground().getWhiteTeam().remove(attackedFigure);
        }
        if (check.isWhiteTeam(figure)) {
            Battleground.getBattleground().getWhiteTeam().remove(figure);
            Battleground.getBattleground().getNodeToFigureMap().get(to.getNeighbors().get(check.defineDirection(from, to))).setNode(to.getNeighbors().get(check.defineDirection(from, to)));
            Battleground.getBattleground().getWhiteTeam().add(Battleground.getBattleground().getNodeToFigureMap().get(to.getNeighbors().get(check.defineDirection(from, to))));
            Battleground.getBattleground().getBlackTeam().remove(attackedFigure);
        }
    }


}
