import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tcase = Integer.parseInt(br.readLine());

        while (tcase-- > 0) {
            char[] line = br.readLine().toCharArray();

            if (line.length % 2 == 0) {
                bw.append("NO\n");
                continue;
            }

            bw.append(test(line, 0, line.length - 1) ? "YES\n" : "NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean test(char[] line, int left, int right) {
        if (left >= right)
            return true;

        int leftIndex = left;
        int rightIndex = right;

        while (leftIndex < rightIndex) {
            if (line[leftIndex++] == line[rightIndex--])
                return false;
        }

        return test(line, left, rightIndex - 1);
    }
}