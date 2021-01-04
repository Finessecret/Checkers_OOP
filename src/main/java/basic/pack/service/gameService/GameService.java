package basic.pack.service.gameService;

import basic.pack.models.*;
import basic.pack.service.drawService.DrawService;
import basic.pack.service.randomeService.RandomService;
import basic.pack.service.stepService.AttackService;
import basic.pack.service.stepService.MovementService;

import java.util.LinkedList;

public class GameService {
    LinkedList<LinkedList<Figure>> players = new LinkedList<>();
    CheckService check = new CheckService();
    BattlegroundService bg = BattlegroundService.getBattlegroundService();
    FigureService figureService = new FigureService();

    public void start() {
        MovementService mv = new MovementService();
        AttackService at = new AttackService();
        DrawService draw = new DrawService();
        initPlayers();
        startGame(mv, at, draw);
        makeDames();
        figureService.createDame(players.get(0));
        draw.drawBattleground(bg);
    }

    private void startGame(MovementService mv, AttackService at, DrawService draw) {
        Figure figureToAttack;
        Figure figureToStep;
        while (!gameOver()) {
            for (int i = 0; i < players.size(); i++) {
                draw.drawBattleground(bg);
                figureToAttack = findFigureToAttack(players.get(i));
                if (figureToAttack == null && !gameOver()) {
                    figureToStep = findFigureToStep(players.get(i));
                    mv.go(figureToStep.getNode(), figureService.getNodesToStep(figureToStep).get(RandomService.interval(0, figureService.getNodesToStep(figureToStep).size() - 1)));

                } else {
                    if (figureToAttack != null) {
                        while (figureService.getNodesToAttack(figureToAttack).size() != 0) {
                            at.attack(figureToAttack.getNode(), figureService.getNodesToAttack(figureToAttack).get(RandomService.interval(0, figureService.getNodesToAttack(figureToAttack).size() - 1)));
                        }
                    }
                }
                makeDames();
                figureService.createDame(players.get(i));
            }
        }
    }

    private void makeDames() {
        Figure figure;
        for (int i = 0; i < Constants.WIDTH - 1; i++) {
            figure = Battleground.getBattleground().getNodeToFigureMap().get(Graph.getGraph().getNodes().get(i).get(0));
            if (check.canMakeDame(figure)) {
                figure.setDame(true);
            }
            figure = Battleground.getBattleground().getNodeToFigureMap().get(Graph.getGraph().getNodes().get(i).get(Constants.HEIGHT - 2));
            if (check.canMakeDame(figure)) {
                figure.setDame(true);
            }
        }
    }

    private Figure findFigureToAttack(LinkedList<Figure> team) {
        for (Figure figure : team) {
            if (figureService.getNodesToAttack(figure).size() != 0) {
                return figure;
            }
        }
        return null;
    }

    private Figure findFigureToStep(LinkedList<Figure> team) {
        Figure randomFigure;
        if (team.size() > 1) {
            randomFigure = team.get(RandomService.interval(0, team.size() - 1));
        } else {
            randomFigure = team.get(0);
        }
        LinkedList<Node> possibleToStep = figureService.getNodesToStep(randomFigure);
        while (possibleToStep.size() == 0) {
            randomFigure = team.get(RandomService.interval(0, team.size() - 1));
            possibleToStep = figureService.getNodesToStep(randomFigure);
        }
        return randomFigure;
    }

    private void initPlayers() {
        players.add(Battleground.getBattleground().getWhiteTeam());
        players.add(Battleground.getBattleground().getBlackTeam());
    }

    private boolean gameOver() {
        return Battleground.getBattleground().getWhiteTeam().size() == 0 || Battleground.getBattleground().getBlackTeam().size() == 0;
    }
}