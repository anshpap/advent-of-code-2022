import java.util.Scanner;
import java.util.ArrayList;

public class NoSpaceLeftOnDevice2 {
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

    public static int calculateSize(TreeNode root) {          // calculates and assigns size to each directory
        if (root.children == null) {
            return root.size;
        }

        for (TreeNode child : root.children) {
            root.size += calculateSize(child);
        }

        return root.size;
    }

    public static void getCandidateDirectories(TreeNode root, ArrayList<TreeNode> list, int requiredSpace) {
        if (root.children == null) {            // file doesn't have children, so root is a file
            return;
        }

        for (TreeNode child : root.children) {
            getCandidateDirectories(child, list, requiredSpace);
        }

        if (root.size >= requiredSpace) {
            list.add(root);
        }
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

        int usedSpace = calculateSize(root);
        int freeSpace = 70000000 - usedSpace;
        int requiredSpace = 30000000 - freeSpace;

        ArrayList<TreeNode> directories = new ArrayList<TreeNode>();
        getCandidateDirectories(root, directories, requiredSpace);

        int smallestDirectorySize = Integer.MAX_VALUE;
        for (TreeNode directory : directories) {
            if (directory.size < smallestDirectorySize) {
                smallestDirectorySize = directory.size;
            }
        }
        System.out.println(smallestDirectorySize);
    }
}