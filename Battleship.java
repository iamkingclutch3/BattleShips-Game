import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Battleship!\n\nMade by: Diego and Mauro\n\n");
        System.out.print("Press enter to continue...");
        scanner.nextLine();
        System.out.println("\n\n\n\n");
        
        Player player = new Player();
        player.placeShips();

        System.out.println("All ships have been placed!\n\n\n");

        ComputerPlayer computer = new ComputerPlayer();
        computer.placeShips();

        while (!player.isGameOver() && !computer.isGameOver()) {
            player.takeTurn(computer);
            computer.takeTurn(player);
        }

        System.out.println("Game over!");
    }
}
