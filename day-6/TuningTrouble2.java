import java.util.LinkedList;
import java.util.HashSet;
import java.io.*;

public class TuningTrouble2 {
    public static int getStartOfMessageCount(BufferedReader reader) throws IOException {
        LinkedList<Character> markerList = new LinkedList<Character>();         // a linked list to store last 14 characters

        int count = 0;
        while(reader.ready()) {
            char ch = (char)reader.read();
            markerList.add(ch);
            count++;

            if (markerList.size() != 14) continue;                               // only relevant for first 13 iterations

            HashSet<Character> markerSet = new HashSet<Character>(markerList);  // hashset removes duplicates
            if (markerSet.size() == 14) {
                return count;
            } else {
                markerList.removeFirst();
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = getStartOfMessageCount(reader);
        System.out.println(count);
    }
}