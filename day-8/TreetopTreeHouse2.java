import java.io.*;
import java.util.ArrayList;

public class TreetopTreeHouse2 {

    public static int getScenicScore(ArrayList<ArrayList<Integer>> forest, int thisTreeRow, int thisTreeColumn, int thisTreeHeight) {
        int leftViewingDistance = 0, rightViewingDistance = 0, upViewingDistance = 0, downViewingDistance = 0;
        int rowLength = forest.get(thisTreeRow).size();
        int columnLength = forest.size();

        int i = thisTreeRow;
        int j = thisTreeColumn;
        while (i > 0) {
            if (forest.get(i - 1).get(j) >= thisTreeHeight) {
                leftViewingDistance++;
                break;
            }
            leftViewingDistance++;
            i--;
        }
        
        i = thisTreeRow;
        j = thisTreeColumn;
        while (i < rowLength - 1) {
            if (forest.get(i + 1).get(j) >= thisTreeHeight) {
                rightViewingDistance++;
                break;
            }
            rightViewingDistance++;
            i++;
        }

        i = thisTreeRow;
        j = thisTreeColumn;
        while (j > 0) {
            if (forest.get(i).get(j - 1) >= thisTreeHeight) {
                upViewingDistance++;
                break;
            }
            upViewingDistance++;
            j--;
        }
        
        i = thisTreeRow;
        j = thisTreeColumn;
        while (j < columnLength - 1) {
            if (forest.get(i).get(j + 1) >= thisTreeHeight) {
                downViewingDistance++;
                break;
            }
            downViewingDistance++;
            j++;
        }

        return leftViewingDistance * rightViewingDistance * upViewingDistance * downViewingDistance;
    } 

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

        int[][] scenicScore = new int[r][c];

        int maxScenicScore = -1;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0 || j == 0 || i == r - 1 || j == c - 1) {
                    scenicScore[i][j] = 0;
                } else {
                    scenicScore[i][j] = getScenicScore(forest, i, j, forest.get(i).get(j));
                }

                if (scenicScore[i][j] > maxScenicScore) {
                    maxScenicScore = scenicScore[i][j];
                }
            }
        }

        System.out.println(maxScenicScore);
    }
}