import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Book {
    int price;
    String title;

    public Book(int price, String title) {
        this.price = price;
        this.title = title;
    }
}

public class Main {
    static List<Book> books;
    static int[] alphabet, count;
    static int bookCount;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        min = Integer.MAX_VALUE;
        books = new ArrayList<Book>();
        String target = br.readLine();
        int targetCount = target.length();

        alphabet = new int[26];
        count = new int[26];
        for (int i = 0; i < targetCount; i++) {
            alphabet[target.charAt(i) - 'A']++;
        }

        bookCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < bookCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            books.add(new Book(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        dfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void dfs(int index, int total) {
        if (index == bookCount) {
            for (int i = 0; i < 26; i++) {
                if (alphabet[i] > count[i])
                    return;
            }
            min = Math.min(min, total);
            return;
        }

        for (int i = 0; i < books.get(index).title.length(); i++) {
            count[books.get(index).title.charAt(i) - 'A']++;
        }
        dfs(index + 1, total + books.get(index).price);
        for (int i = 0; i < books.get(index).title.length(); i++) {
            count[books.get(index).title.charAt(i) - 'A']--;
        }
        dfs(index + 1, total);
    }
}