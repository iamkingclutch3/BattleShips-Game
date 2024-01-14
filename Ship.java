public class Ship {
    private int row;
    private int col;
    private int length;
    private boolean horizontal;

    public Ship(int row, int col, int length, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.length = length;
        this.horizontal = horizontal;
    }

    // Getters and setters for Ship class
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
    


}
