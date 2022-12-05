import java.util.Scanner;
import java.util.Stack;
import java.util.regex.*;

public class SupplyStacks2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] crateGrid = new char[8][9];

        int k = 0;
        for (int j = 0; j < 8; j++) {                       // create a grid of crates from input
            String line = sc.nextLine();
            for (int i = 1; i < line.length(); i += 4) {
                crateGrid[j][k] = line.charAt(i);
                k++;
            }
            k = 0;
        }

        sc.nextLine();
        sc.nextLine();

        Stack<Character>[] stacks = new Stack[9];           // create an array of stacks
        for (int i = 0; i < 9; i++) {
            stacks[i] = new Stack<Character>();
        }

        for (int i = 7; i >= 0; i--) {                      // push crates from grid to their respective stacks
            for (int j = 8; j >= 0; j--) {
                if (!Character.isWhitespace(crateGrid[i][j])) {
                    stacks[j].push(Character.valueOf(crateGrid[i][j]));
                }
            }
        }

        while (sc.hasNext()) {
            sc.findInLine("move (\\d+) from (\\d+) to (\\d+)");        // "move n crates from stack a to stack b"
            MatchResult result = sc.match();
            int n = Integer.parseInt(result.group(1));
            int a = Integer.parseInt(result.group(2));
            int b = Integer.parseInt(result.group(3));

            Stack<Character> temp = new Stack<Character>();
            for (int i = 0; i < n; i++) {
                temp.push(stacks[a - 1].pop());                    // reverse stack to retain order
            }
            for (int i = 0; i < n; i++) {
                stacks[b - 1].push(temp.pop());
            }
            sc.nextLine();
        }

        for (int i = 0; i < stacks.length; i++) {
            System.out.print(stacks[i].lastElement());
        }

    }
}