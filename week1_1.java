import java.util.*;

public class week1_1 {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "Draw";
        else if (
            (playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))
        )
            return "Player Wins";
        else
            return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] player = new String[5];
        String[] computer = new String[5];
        String[] result = new String[5];

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter your move (Rock/Paper/Scissors): ");
            player[i] = sc.next();

            player[i] = player[i].substring(0, 1).toUpperCase()
                    + player[i].substring(1).toLowerCase();

            computer[i] = moves[rand.nextInt(3)];
            result[i] = playRound(player[i], computer[i]);

            if (result[i].equals("Player Wins"))
                wins++;
            else if (result[i].equals("Computer Wins"))
                losses++;
            else
                draws++;

            System.out.println("Round " + (i + 1)
                    + " | Player: " + player[i]
                    + " | Computer: " + computer[i]
                    + " | Result: " + result[i]);
        }

        System.out.println("\nFinal Summary");
        System.out.println("Round | Player Move | Computer Move | Result");

        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + " | " + player[i]
                    + " | " + computer[i] + " | " + result[i]);
        }

        double percentage = (wins / 5.0) * 100;

        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("Win Percentage: " + percentage + "%");

        sc.close();
    }
}
}
