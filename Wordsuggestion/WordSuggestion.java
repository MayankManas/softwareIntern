import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Pair {
    int matches;
    String word = "";
    int originalIndex;

    Pair(int fre, String word, int index) {
        matches = fre;
        this.word = word;
        originalIndex = index;
    }
}

class myComparator implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        if (b.matches != a.matches)
            return b.matches - a.matches; // if matches differ
        else
            return a.originalIndex - b.originalIndex; // if matches dont differ, sort in order of indexes
    }
}

class WordSuggestion {

    static int getMatches(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        Arrays.fill(freq1, 0);
        Arrays.fill(freq2, 0);

        // To store the count of valid pairs
        int i, count = 0;

        // Update the frequencies of
        // the characters of string s1
        for (i = 0; i < n1; i++)
            freq1[(int) s1.charAt(i) - 'a']++;

        // Update the frequencies of
        // the characters of string s2
        for (i = 0; i < n2; i++)
            freq2[s2.charAt(i) - 'a']++;

        // Find the count of valid pairs
        for (i = 0; i < 26; i++)
            count += (Math.min(freq1[i], freq2[i]));

        return count;
    }

    public static void main(String args[]) throws IOException {
        Scanner scan = new Scanner(System.in); // scanner declared
        FileReader fr = null;
        System.out.print("Enter pathname and searchstring");
        String pathName = scan.next(); // dictionary pathname scanned

        try {
            fr = new FileReader(pathName); // file read from pathname
        } catch (Exception e) {
            System.out.print("FILE NOT FOUND"); // exception if pathname incorrect
        }
        int i = 0;
        StringBuffer temp = new StringBuffer();
        while ((i = fr.read()) != -1) {
            if (i >= 65 && i <= 90 || i >= 97 && i <= 122 || i == 32) // scanned only characters and whitespaces
                temp.append((char) i);
        }

        String arr[] = temp.toString().split(" "); // split the string into array using white spaces as delimiters

        String base = scan.next();
        ArrayList<Pair> al = new ArrayList<>();
        for (i = 0; i < arr.length; i++) {
            al.add(new Pair(getMatches(base.toLowerCase(), arr[i].toLowerCase()), arr[i].toLowerCase(), i));
        }

        Collections.sort(al, new myComparator()); // sorted the dictionary based on the number of matches of
                                                  // searchstring and order of insertion in dictionary
        StringBuffer ans = new StringBuffer();
        int count = 0;
        for (Pair x : al) {
            ans.append(x.word);
            ans.append(", ");
            count++;
            if (count >= 5)
                break;
        }
        scan.close(); // scanner closed
        ans.setLength(ans.length() - 2);
        System.out.println("Relevant strings are:");
        System.out.print(ans);
    }
}