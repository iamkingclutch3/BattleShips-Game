public class Battleship {
    public static void main(String[] args) {
        System.out.println("Loading");
        Player player = new Player();
        ComputerPlayer computer = new ComputerPlayer();

        System.out.println("Welcome to Battleship!\n\nMade by: Diego and Mauro");

        player.placeShips();

        while (!player.isGameOver() && !computer.isGameOver()) {
            player.takeTurn(computer);
            computer.takeTurn(player);
        }

        System.out.println("Game over!");
    }
}
