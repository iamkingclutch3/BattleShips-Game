import java.util.Scanner;

public class Player {
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int NUM_OF_SHIPS = 5;

    private Ship[] ships = new Ship[NUM_OF_SHIPS];
    private Board ownBoard;
    private Board targetBoard;

    public Player() {
        this.ownBoard = new Board();
        this.targetBoard = new Board();
    }

    public void placeShips() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            int length = SHIP_LENGTHS[i];
            boolean placed = false;

            while (!placed) {
                this.displayOwnBoard();

                System.out.println("Ship " + (i + 1) + ": Length " + length);
                System.out.print("Enter the row (A-J): ");
                char Char = scanner.next().toUpperCase().charAt(0);
                int row = Char - 'A';

                System.out.print("Enter the column (1-10): ");
                int col = scanner.nextInt() - 1;

                System.out.print("Enter the direction (0 for horizontal, 1 for vertical): ");
                boolean horizontal = scanner.nextInt() == 0 ? true : false;

                Ship newShip = new Ship(row, col, length, horizontal);

                if (ownBoard.placeShip(newShip)) {
                    placed = true;
                    ships[i] = newShip;
                    System.out.println("Ship placed!");
                } else {
                    System.out.println("Invalid placement. Please try again.");
                }
            }
        }
    }

    public boolean takeTurn(ComputerPlayer opponent) {
        Scanner scanner = new Scanner(System.in);
        this.displayGameBoards();

        System.out.println("It's player's turn!");
        System.out.print("Enter the target row (A-J): ");
        char Char = scanner.next().toUpperCase().charAt(0);
        int row = Char - 'A';

        System.out.print("Enter the target column (1-10): ");
        int col = scanner.nextInt() - 1;

        if (targetBoard.getGrid()[row][col] == 'X' || targetBoard.getGrid()[row][col] == 'O') {
            System.out.println("You have already shot there. Please try again.");
            return false;
        }

        boolean hit = opponent.takeShot(row, col);

        if (hit) {
            targetBoard.updateCell(row, col, 'X');
            System.out.println("Hit!");
        } else {
            targetBoard.updateCell(row, col, 'O');
            System.out.println("Miss!");
        }

        return hit;
    }

    public boolean takeShot(int row, int col) {
        return ownBoard.takeShot(row, col);
    }

    public boolean isGameOver() {
        return ships.length == 0;
    }

    public void displayOwnBoard() {
        System.out.println("Player's Board:");
        ownBoard.displayBoard();
    }

    public void displayGameBoards() {
        System.out.println("Player's Game Boards:");
        System.out.println("Target Board\t\t\t\t\tOwn Board");

        System.out.print("  ");
        for (int i = 0; i < targetBoard.getSize(); i++) {
            System.out.print(i + " ");
        }
        System.out.print("\t\t\t\t  ");

        for (int i = 0; i < ownBoard.getSize(); i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < targetBoard.getSize(); i++) {
            char rowChar = (char) ('A' + i);
            System.out.print(rowChar + " ");

            for (int j = 0; j < targetBoard.getSize(); j++) {
                System.out.print(targetBoard.getGrid()[i][j] + " ");
            }

            System.out.print("\t\t\t\t");

            System.out.print(rowChar + " ");

            for (int j = 0; j < ownBoard.getSize(); j++) {
                System.out.print(ownBoard.getGrid()[i][j] + " ");
            }

            System.out.println();
        }
    }

}
