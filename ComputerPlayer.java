public class ComputerPlayer {
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private static final int NUM_OF_SHIPS = 5;

    private Ship[] ships;
    private Board ownBoard;
    private Board targetBoard;

    public ComputerPlayer() {
        this.ships = new Ship[NUM_OF_SHIPS];
        this.ownBoard = new Board();
        this.targetBoard = new Board();
    }

    public void placeShips() {
        Randomizer randomizer = new Randomizer();

        for (int i = 0; i < NUM_OF_SHIPS; i++) {
            int length = SHIP_LENGTHS[i];
            boolean placed = false;

            while (!placed) {
                int row = randomizer.nextInt(10);
                int col = randomizer.nextInt(10);
                boolean horizontal = randomizer.nextBoolean();

                Ship newShip = new Ship(row, col, length, horizontal);

                if (ownBoard.placeShip(newShip)) {
                    placed = true;
                    ships[i] = newShip;
                }
            }
        }
    }

    public boolean takeTurn(Player opponent) {
        Randomizer randomizer = new Randomizer();

        int row = randomizer.nextInt(10);
        int col = randomizer.nextInt(10);

        while(targetBoard.getGrid()[row][col] == 'X' || targetBoard.getGrid()[row][col] == 'O') {
            row = randomizer.nextInt(10);
            col = randomizer.nextInt(10);
        }

        boolean hit = opponent.takeShot(row, col);

        if (hit) {
            targetBoard.updateCell(row, col, 'X');
        } else {
            targetBoard.updateCell(row, col, 'O');
        }

        return hit;
    }

    public boolean takeShot(int row, int col) {
        return ownBoard.takeShot(row, col);
    }

    public boolean isGameOver() {
        return ships.length == 0;
    }
}
