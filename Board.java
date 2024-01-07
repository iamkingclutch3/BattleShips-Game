public class Board {
    private char[][] grid;
    private static final int size = 10;

    // Constructor

    public Board() {
        this.grid = new char[size][size];
        createGrid();
    }

    // Getters and setters

    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public int getSize() {
        return size;
    }

    // Other methods

    private void createGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void displayBoard() {
        System.out.print("  ");
        for (int i = 1; i < size+1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            char rowChar = (char) ('A' + i);
            System.out.print(rowChar + " ");

            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean placeShip(Ship ship) {
        int row = ship.getRow();
        int col = ship.getCol();
        int length = ship.getLength();
        boolean horizontal = ship.isHorizontal();

        if (horizontal) { // Checks if the ship fits in the board
            if (col + length > size)
                return false;
            for (int i = col; i < col + length; i++) {
                if (grid[row][i] != '-')
                    return false;
            }
            for (int i = col; i < col + length; i++) {
                grid[row][i] = 'S';
            }
        } else {
            if (row + length > size)
                return false;
            for (int i = row; i < row + length; i++) {
                if (grid[i][col] != '-')
                    return false;
            }
            for (int i = row; i < row + length; i++) {
                grid[i][col] = 'S';
            }
        }

        for (int i = 0; i < length; i++) { // Checks if there is no a boat already there
            if (horizontal) {
                if (col + i >= size || grid[row][col + i] != '-') {
                    return false;
                }
            } else {
                if (row + i >= size || grid[row + i][col] != '-') {
                    return false;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            if (horizontal) {
                grid[row][col + i] = 'S';
            } else {
                grid[row + i][col] = 'S';
            }
        }

        return true;
    }

    public boolean takeShot(int row, int col) {

        if (row < 0 || row >= size || col < 0 || col >= size) { // checks valid coords
            return false;
        }

        if (grid[row][col] == '-') {
            grid[row][col] = 'O';
            System.out.println("Miss!");
            return false;
        } else if (grid[row][col] == 'S') {
            grid[row][col] = 'X';
            System.out.println("Hit!");
            return true;
        } else if (grid[row][col] == 'X' || grid[row][col] == 'O') {
            System.out.println("You have already shot at this location.");
            return false;
        }

        return false;
    }

    public void updateCell(int row, int col, char content) {
        grid[row][col] = content;
    }

}
