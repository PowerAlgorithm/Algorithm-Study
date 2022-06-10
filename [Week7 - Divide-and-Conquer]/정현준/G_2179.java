import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            list.add(br.readLine());
        }

        int max = 0;
        String first = "", second = "";

        for (int i = 0; i < size; i++) {
            String word1 = list.get(i);
            for (int j = i + 1; j < size; j++) {
                String word2 = list.get(j);
                int length = Math.min(word1.length(), word2.length());
                int count = 0;
                for (int k = 0; k < length; k++) {
                    if (word1.charAt(k) != word2.charAt(k))
                        break;
                    count++;
                }
                if (max < count) {
                    max = count;
                    first = word1;
                    second = word2;
                }
            }
        }
        bw.append(first).append("\n").append(second);
        bw.flush();
        bw.close();
        br.close();
    }

}