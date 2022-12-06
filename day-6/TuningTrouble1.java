import java.util.LinkedList;
import java.util.HashSet;
import java.io.*;

public class TuningTrouble1 {
    public static int getStartOfPacketCount(BufferedReader reader) throws IOException {
        LinkedList<Character> markerList = new LinkedList<Character>();         // a linked list to store last 4 characters

        int count = 0;
        while(reader.ready()) {
            char ch = (char)reader.read();
            markerList.add(ch);
            count++;

            if (markerList.size() != 4) continue;                               // only relevant for first 3 iterations

            HashSet<Character> markerSet = new HashSet<Character>(markerList);  // hashset removes duplicates
            if (markerSet.size() == 4) {
                return count;
            } else {
                markerList.removeFirst();
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = getStartOfPacketCount(reader);
        System.out.println(count);
    }
}