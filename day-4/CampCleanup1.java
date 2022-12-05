import java.util.Scanner;
import java.util.regex.*;

public class CampCleanup1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] a = new int[4];
        int count = 0;
        while(sc.hasNext()) {
            sc.findInLine("(\\d+)-(\\d+),(\\d+)-(\\d+)");
            MatchResult result = sc.match();
            for (int i = 0; i < 4; i++) {
                a[i] = Integer.parseInt(result.group(i + 1));
            }

            if (a[0] <= a[2] && a[3] <= a[1] || a[2] <= a[0] && a[1] <= a[3]) {   // (a,b) contains (c,d) if a <= c && d <= b
                count++;
            }
            sc.nextLine();
        }

        System.out.println(count);

    }
}