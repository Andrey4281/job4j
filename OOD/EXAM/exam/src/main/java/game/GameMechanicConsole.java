package game;

import com.google.common.base.Joiner;

import java.util.Scanner;

public class GameMechanicConsole extends AbstractGameMechanic {
    private static final String LN = System.getProperty("line.separator");
    private final Console console;

    public GameMechanicConsole(Player playerOne, Player playerTwo, GameField field, RulesOfGame rules, Console console) {
        super(playerOne, playerTwo, field, rules);
        this.console = console;
    }

    @Override
    public void start() {
        console.output(Joiner.on(LN).join("Welcome in the game", ""));
        console.output(Joiner.on(LN).join(field.toString(), ""));
        while (true) {
            playerOne.makeMove();
            console.output(Joiner.on(LN).join(field.toString(), ""));
            if (checkGame(playerOne)) {
                break;
            }
            playerTwo.makeMove();
            console.output(Joiner.on(LN).join(field.toString(), ""));
            if (checkGame(playerTwo)) {
                break;
            }
        }
    }

    private boolean checkGame(Player player) {
        boolean res = false;

        if (rules.IfWin(player.getSignOfPlayer())) {
            console.output(String.format("Player %s is win%n", player.getSignOfPlayer()));
            res = true;
        } else if (rules.IfDraw()) {
            console.output("Draw!");
            res = true;
        }
        return res;
    }

    public static void main(String[] args) {
        Console console = new GameConsole(new Scanner(System.in), System.out);
        Player playerPerson = new PlayerPersonConsole(console, 'X');
        Player playerComputer = new StupidComputerPlayer('O');
        GameField field = new SimpleGameField(5);
        RulesOfGame rulesOfGame = new GeneralizedRules(field, 4);

        AbstractGameMechanic gameMechanic =
                new GameMechanicConsole(playerPerson, playerComputer, field, rulesOfGame, console);

        gameMechanic.start();
    }
}
