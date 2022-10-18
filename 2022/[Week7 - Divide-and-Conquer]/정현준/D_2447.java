import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int size = Integer.parseInt(br.readLine());

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                write(i, j, size);
            }
            sb.append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void write(int x, int y, int size) {
        if ((x / size) % 3 == 1 && (y / size) % 3 == 1) {
            sb.append(" ");
        } else {
            if (size / 3 == 0)
                sb.append("*");
            else
                write(x, y, size / 3);
        }
    }
}