import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

class Permutation {
    static StringBuffer sb = new StringBuffer(); // stores the permutation

    static void printPermutations(ArrayList<ArrayList<Character>> arr, char a[], int curr) {
        if (curr >= arr.size()) { // base case of recursion
            for (int i = 0; i < a.length; i++) // if the curr index exceeds or is equal to the size of arraylist arr,
                sb.append(a[i]); // rest of the array is appended to the static stringbuffer

            sb.append(", ");
            a = new char[arr.size()];
            return;
        }

        ArrayList<Character> currAl = arr.get(curr); // adds the arraylist stored at curr index to currAl
        for (int i = 0; i < currAl.size(); i++) {
            a[curr] = currAl.get(i); // add the char to a array
            printPermutations(arr, a, curr + 1); // calls the recursive function again with next index;
        }
    }

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in); // scanner class object created
        String filePath = ""; // file path string

        System.out.print("ENTER FILE PATH");
        filePath = scan.nextLine().trim(); // file path string scanned
        scan.close();
        ArrayList<ArrayList<Character>> arr = new ArrayList<>();
        FileReader fr = null; // reading file and storing it in an arraylist of arraylist of characters
        try {
            fr = new FileReader(filePath);
        } catch (Exception e) {
            System.out.println("file not found!"); // filepath incorrect
        }

        int i = 0;
        int rows = 0;
        ArrayList<Character> dmyRows = new ArrayList<>();
        while ((i = fr.read()) != -1) {
            if (i == 10) {                              // if white space is incountered, it is treated
                arr.add(rows, new ArrayList<>(dmyRows));// as new line and is inserted in the next row of the array list
                rows++;
                dmyRows.clear();
            }
            if (i >= 65 && i <= 90 || i >= 97 && i <= 122 || i >= 48 && i <= 57) // only characters and digits are
                                                                                 // scanned and inserted
                dmyRows.add((char) i);
        }
        arr.add(rows, new ArrayList<>(dmyRows)); // the last row is added in the arraylist
        char ans[] = new char[arr.size()];
        printPermutations(arr, ans, 0); // function to store permutation int stringbuffer is called
        sb.setLength(sb.length() - 2); // trims the last comma and space
        System.out.println(sb); // prints the stringbuffer

    }
}