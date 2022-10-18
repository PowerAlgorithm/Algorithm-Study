import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[] chars;
    static int size;
    static boolean[] check;
    static List<String> results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        chars = new char[size];
        String line = br.readLine().replace(" ", "");
        for (int i = 0; i < size; i++) {
            chars[i] = line.charAt(i);
        }
        check = new boolean[10];
        results = new ArrayList<>();

        find(0, "");
        Collections.sort(results);
        System.out.println(results.get(results.size() - 1));
        System.out.println(results.get(0));
    }

    public static void find(int count, String number) {
        if (count == size + 1) {
            results.add(number);
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (check[i])
                continue;
            if (count == 0 || compare(number.charAt(count - 1) - '0', i, chars[count - 1])) {
                check[i] = true;
                find(count + 1, number + i);
                check[i] = false;
            }
        }
    }

    public static boolean compare(int num1, int num2, char ch) {
        if (ch == '>') {
            return num1 > num2;
        }
        return num1 < num2;
    }

}