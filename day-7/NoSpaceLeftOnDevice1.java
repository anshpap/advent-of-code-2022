import java.util.Scanner;
import java.util.ArrayList;

public class NoSpaceLeftOnDevice1 {
    public static class TreeNode {
        String name;
        int size;
        TreeNode parent;
        ArrayList<TreeNode> children;

        TreeNode(String name, int size, TreeNode parent) {
            this.name = name;
            this.size = size;
            this.parent = parent;
        }
    }

    public static int calculateSize(TreeNode root, ArrayList<TreeNode> list) {          // calculates and assigns size to each directory. also updates ArrayList of directories with size less than 100000 
        if (root.children == null) {                        // file doesn't have children, so root is a file
            return root.size;
        }

        for (TreeNode child : root.children) {
            root.size += calculateSize(child, list);
        }

        if (root.size <= 100000) {
            list.add(root);
        }
        return root.size;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        TreeNode currentDir = new TreeNode(command.substring(5), 0, null);
        currentDir.children = new ArrayList<TreeNode>();
        command = sc.nextLine();

        TreeNode root = currentDir;

        while(sc.hasNext()) {
            if (command.startsWith("$ cd")) {
                String dirName = command.substring(5);

                if (dirName.equals("..")) {                             //cd .. - set current directory to parent directory
                    currentDir = currentDir.parent;
                } else {
                    for (TreeNode child : currentDir.children) {
                        if (child.name.equals(dirName)) {               //cd x - set current directory to x
                            currentDir = child;
                            currentDir.children = new ArrayList<TreeNode>();
                        }
                    }
                }

                command = sc.nextLine();

            } else if (command.equals("$ ls")) {
                String dataLine = sc.nextLine();
                
                while (!dataLine.startsWith("$ ")) {                    // add files/directories to current directory
                    String[] data = dataLine.split(" ");

                    if (data[0].equals("dir")) {
                        currentDir.children.add(new TreeNode(data[1], 0, currentDir));
                    } else {
                        currentDir.children.add(new TreeNode(data[1], Integer.parseInt(data[0]), currentDir));
                    }
                    
                    if (!sc.hasNext()) break;
                    dataLine = sc.nextLine();
                }

                command = dataLine;
            }
        }

        ArrayList<TreeNode> directories = new ArrayList<TreeNode>();        // directories with size less than 100000
        calculateSize(root, directories);

        int sum = 0;
        for (TreeNode directory : directories) {
            sum += directory.size;
        }
        System.out.println(sum);
    }
}