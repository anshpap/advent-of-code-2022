import java.util.Scanner;

public class RockPaperScissorsPart1 {
    public static int playRound(int opponent, int player) {
        if (opponent == player) return 3;       // draw

        opponent %= 3;                          // wrap around
        if (player == opponent + 1) return 6;   // win (P == R + 1, S == P + 1, R = S + 1)

        return 0;                               //lose
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int score = 0;
        while (sc.hasNext()) {
            int opponent = sc.next().charAt(0) - 64;      // convert input to 1, 2, 3 (R, P, S)
            int player = sc.next().charAt(0) - 87;

            int result = playRound(opponent, player);
            score += player + result;
        }

        System.out.println(score);
    }
}