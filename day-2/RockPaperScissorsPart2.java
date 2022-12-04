import java.util.Scanner;

public class RockPaperScissorsPart2 {

    public static int calculatePlayerChoice(int opponent, int result) {
        if (result == 1) return opponent;               // draw

        if (result == 2) return 1 + (opponent % 3);     // win

        opponent -= 1;                                  // convert {1, 2, 3} to {0, 1, 2}
        return Math.floorMod(opponent - 1, 3) + 1;      // lose (subtract 1. find floorMod 3 to wrap around. add 1 to reconvert to 1, 2, 3)
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int score = 0;
        while (sc.hasNext()) {
            int opponent = sc.next().charAt(0) - 64;        // convert input to 1, 2, 3 (R, P, S)
            int result = sc.next().charAt(0) - 88;          // 0, 1, 2 (L, D, W)

            int player = calculatePlayerChoice(opponent, result);
            score += player + (result * 3);
        }

        System.out.println(score);
    }
}