import java.util.Scanner;
import java.util.PriorityQueue;

public class CalorieCountingPart2 {

    public static void printMaxNCalorieSums(Scanner sc, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        int currentCalorieSum = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if(input.isEmpty()) {
                pq.add(-currentCalorieSum);     // using negative to create a max-heap (will also use negative when removing an element)
                currentCalorieSum = 0;
            } else {
                currentCalorieSum += Integer.parseInt(input);
            }
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += -pq.poll();
        }

        System.out.println(total);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);       
        printMaxNCalorieSums(sc, 3);
    }
}