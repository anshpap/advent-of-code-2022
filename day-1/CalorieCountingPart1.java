import java.util.Scanner;

public class CalorieCountingPart1 {

    public static void printMaxCalorieSum(Scanner sc) {
        int maxCalorieSum = Integer.MIN_VALUE;
        int currentCalorieSum = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.isEmpty()) {
                maxCalorieSum = Math.max(currentCalorieSum, maxCalorieSum);
                currentCalorieSum = 0;
            } else {
                currentCalorieSum += Integer.parseInt(input);
            }
        }

        System.out.println(maxCalorieSum);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        printMaxCalorieSum(sc);
    }
}