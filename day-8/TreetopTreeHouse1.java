import java.io.*;
import java.util.ArrayList;

public class TreetopTreeHouse1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        ArrayList<ArrayList<Integer>> forest = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> line = new ArrayList<Integer>();
        while(br.ready()) {
            int input = br.read();
            if (input == 10) {                  // new line feed character (ascii)
                forest.add(line);
                line = new ArrayList<Integer>();
            } else {
                line.add(Character.getNumericValue((char)input));
            }
        }

        int r = forest.size();
        int c = forest.get(0).size();
        Visibility[][] visibility = new Visibility[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                visibility[i][j] = new Visibility();
            }
        }
        
        int maxHeight = -1;
        // update the left visibility of each tree
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int thisHeight = forest.get(i).get(j);
                if (thisHeight > maxHeight) {
                    visibility[i][j].leftVisible = true;
                    maxHeight = thisHeight;
                }
            }
            maxHeight = -1;
        }

        // update the right visibility of each tree
        for (int i = 0; i < r; i++) {
            for (int j = c - 1; j >= 0; j--) {
                int thisHeight = forest.get(i).get(j);
                if (thisHeight > maxHeight) {
                    visibility[i][j].rightVisible = true;
                    maxHeight = thisHeight;
                }
            }
            maxHeight = -1;
        }

        // update the up visibility of each tree
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                int thisHeight = forest.get(i).get(j);
                if (thisHeight > maxHeight) {
                    visibility[i][j].upVisible = true;
                    maxHeight = thisHeight;
                }
            }
            maxHeight = -1;
        }

        // update the down visibility of each tree
        for (int j = 0; j < c; j++) {
            for (int i = r - 1; i >= 0; i--) {
                int thisHeight = forest.get(i).get(j);
                if (thisHeight > maxHeight) {
                    visibility[i][j].downVisible = true;
                    maxHeight = thisHeight;
                }
            }
            maxHeight = -1;
        }

        int numberOfVisibleTrees = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visibility[i][j].isVisible()) {
                    numberOfVisibleTrees++;
                }
            }
        }

        System.out.println(numberOfVisibleTrees);
    }
}