package Aug19;

import java.util.ArrayList;
import java.util.Scanner;

public class Classwork2 {
    public static void main(String[] args) {
        GameManager game = new GameManager();
        game.play();
    }
}

class GameManager {
    private ArrayList<ArrayList<Character>> grid = new ArrayList<>(3);
    private Scanner sc = new Scanner(System.in);
    private boolean firstPlayer = true;

    public GameManager() {
        for (int i = 0; i < 3; i++) {
            grid.add(new ArrayList<Character>(3));
            for (int j = 0; j < 3; j++) {
                grid.get(i).add('*');
            }
        }
    }

    public void printGrid() {
        for (ArrayList<Character> row : grid) {
            for (char entry : row) {
                System.out.print(entry + " ");
            }
            System.out.println();
        }
    }

    public void play() {
        printGrid();
        while (true) {
            System.out.println("Enter the coordinates you want to place your character as x, y");
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            if (x > grid.get(0).size() || y > grid.size() || grid.get(y).get(x) != '*') {
                System.out.println("Invalid coordinates");
                continue;
            }
            grid.get(y).set(x, 'X');
            if (checkGameOver()) {
                return;
            }
            firstPlayer = false;
            playComputer();
            if (checkGameOver()) {
                return;
            }
            printGrid();
            firstPlayer = true;
        }
    }

    public void playComputer() {
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == '*') {
                    grid.get(i).set(j, 'O');
                    return;
                }
            }
        }
    }

    public boolean checkGameOver() {
        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i).get(0) != '*' && grid.get(i).get(0) == grid.get(i).get(1) && grid.get(i).get(1) == grid.get(i).get(2)) {
                System.out.println("Player " + (firstPlayer ? "1" : "2") + " wins by a row!");
                return true;
            }
        }
        for (int i = 0; i < grid.get(0).size(); i++) {
            if (grid.get(0).get(i) != '*' && grid.get(0).get(i) == grid.get(1).get(i) && grid.get(1).get(i) == grid.get(2).get(i)) {
                System.out.println("Player " + (firstPlayer ? "1" : "2") + " wins by a column!");
                return true;
            }
        }
        if (grid.get(0).get(0) != '*' && grid.get(0).get(0) == grid.get(1).get(1) && grid.get(0).get(0) == grid.get(2).get(2)) {
            System.out.println("Player " + (firstPlayer ? "1" : "2") + " wins by a diagonal!");
            return true;
        }
        if (grid.get(0).get(2) != '*' && grid.get(0).get(2) == grid.get(1).get(1) && grid.get(0).get(2) == grid.get(2).get(0)) {
            System.out.println("Player " + (firstPlayer ? "1" : "2") + " wins by a diagonal!");
            return true;
        }
        boolean gridFull = true;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(0).size(); j++) {
                if (grid.get(i).get(j) == '*') {
                    gridFull = false;
                }
            }
        }
        if (gridFull) {
            System.out.println("Tie!");
            return true;
        }
        return false;
    }
}
