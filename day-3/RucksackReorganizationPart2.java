import java.util.Scanner;
import java.util.HashSet;

public class RucksackReorganizationPart2 {

    public static int findBadgeItemPriority(String rucksack1, String rucksack2, String rucksack3) {
        HashSet<Character> itemSet1 = new HashSet<Character>();
        HashSet<Character> itemSet2 = new HashSet<Character>();
        HashSet<Character> itemSet3 = new HashSet<Character>();

        for (int i = 0; i < rucksack1.length(); i++) {
            itemSet1.add(rucksack1.charAt(i));
        }
        for (int i = 0; i < rucksack2.length(); i++) {
            itemSet2.add(rucksack2.charAt(i));
        }
        for (int i = 0; i < rucksack3.length(); i++) {
            itemSet3.add(rucksack3.charAt(i));
        }
        
        int badgeItemPriority = 0;
        for (char item : itemSet1) {
            if (itemSet2.contains(item) && itemSet3.contains(item)) {
                badgeItemPriority = Character.isLowerCase(item) ? item - 96: item - 38;      // convert to 1 - 52 priority
                break;
            }
        }

        return badgeItemPriority;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        while (sc.hasNext()) {
            String rucksack1 = sc.nextLine();
            String rucksack2 = sc.nextLine();
            String rucksack3 = sc.nextLine();

            int badgeItemPriority = findBadgeItemPriority(rucksack1, rucksack2, rucksack3);
            sum += badgeItemPriority;
        }

        System.out.println(sum);
    }
} 