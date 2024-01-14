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

                if (horizontal) {
            if (col + length > size) {
                return false;
            }

            for (int i = col; i < col + length; i++) {
                if (i >= size || grid[row][i] != '-') {
                    return false;
                }
                grid[row][i] = 'S';
            }
        } else {
            if (row + length > size) {
                return false;
            }

            for (int i = row; i < row + length; i++) {
                if (i >= size || grid[i][col] != '-') {
                    return false;
                }
                grid[i][col] = 'S';
            }
        }

        return true;
    }

    public boolean takeShot(int row, int col) {

        if (row < 0 || row >= size || col < 0 || col >= size) { // checks valid coords
            return false;
        }

        if (grid[row][col] == '-') { // Misses
            grid[row][col] = 'O';
            return false;
        } else if (grid[row][col] == 'S') { // Hits
            grid[row][col] = 'X';
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
