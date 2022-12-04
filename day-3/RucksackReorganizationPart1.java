import java.util.Scanner;
import java.util.HashSet;

public class RucksackReorganizationPart1 {

    public static int findErroneousItemPriority(String compartment1, String compartment2) {
        HashSet<Character> itemSet1 = new HashSet<Character>();
        HashSet<Character> itemSet2 = new HashSet<Character>();

        for (int i = 0; i < compartment1.length(); i++) {
            itemSet1.add(compartment1.charAt(i));
        }
        for (int i = 0; i < compartment2.length(); i++) {
            itemSet2.add(compartment2.charAt(i));
        }
        
        int erroneousItemPriority = 0;
        for (char item : itemSet1) {
            if (itemSet2.contains(item)) {
                erroneousItemPriority = Character.isLowerCase(item) ? item - 96: item - 38;      // convert to 1 - 52 priority
                break;
            }
        }

        return erroneousItemPriority;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = 0;
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String compartment1 = line.substring(0, line.length() / 2);            // split string into two compartments
            String compartment2 = line.substring(line.length() / 2);

            int erroneousItemPriority = findErroneousItemPriority(compartment1, compartment2);
            sum += erroneousItemPriority;
        }

        System.out.println(sum);
    }
} 