package tictactoe;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static boolean finished = true;
    static boolean playerX = true;

    public static void main(String[] args) {
        System.out.println("Enter cells:");
        char[][] grid = createGrid("         ");
        printGrid(grid);
        while (checkGrid(grid).equals("Game not finished")) {
            input(grid);
        }
        System.out.println(checkGrid(grid));
    }

    public static void printGrid(char[][] array) {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(array[row][col] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static char[][] createGrid(String input) {
        char[][] array = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                array[row][col] = input.charAt(col + 3 * row);
            }
        }
        return array;
    }

    public static String checkGrid(char[][] grid) {
        boolean xWins = checkRow(grid, 'X');
        boolean oWins = checkRow(grid, 'O');

        if (!isPossible(grid) || xWins && oWins) {
            return "Impossible";
        }
        if (xWins) {
            return "X wins";
        }
        if (oWins) {
            return "O wins";
        }
        if (finished) {
            return "Draw";
        }
        return "Game not finished";
    }

    static boolean checkRow(char[][] grid, char player) {
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] == player && grid[row][1] == player && grid[row][2] == player) {
                return true;
            }
            if (grid[0][row] == player && grid[1][row] == player && grid[2][row] == player) {
                return true;
            }
            if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
                return true;
            }
            if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
                return true;
            }
        }
        return false;
    }

    static boolean isPossible(char[][] grid) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                countX += grid[i][j] == 'X' ? 1 : 0;
                countO += grid[i][j] == 'O' ? 1 : 0;
            }
        }
        finished = countO + countX == 9;
        return !(countO - countX > 1 || countX - countO > 1);
    }

    static void input(char[][] grid) {
        while (true) {
            System.out.println("Enter the coordinates:");
            String rowInput = scanner.next();
            String colInput = scanner.next();
            try {
                int row = Integer.parseInt(rowInput);
                int col = Integer.parseInt(colInput);
            } catch (NumberFormatException nfe) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int row = Integer.parseInt(rowInput);
            int col = Integer.parseInt(colInput);
            if (row > 3 || row < 1 || col > 3 || col < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (grid[row - 1][col - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            grid[row - 1][col - 1] = playerX ? 'X' : 'O';
            playerX = !playerX;
            printGrid(grid);
            break;
        }
    }
}
