import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken()); // 초밥 개수
        int kind = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int seq = Integer.parseInt(st.nextToken()); // 연속으로 먹는 수
        int coupon = Integer.parseInt(st.nextToken()); // 쿠폰 초밥

        int[] table = new int[size];
        int[] sushi = new int[kind + 1];

        for (int i = 0; i < size; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for (int i = 0; i < seq; i++) {
            if (sushi[table[i]] == 0) {
                count++;
            }
            sushi[table[i]]++;
        }

        int maxCount = count;

        for (int i = 1; i < size; i++) {
            if (maxCount <= count) {

                if (sushi[coupon] == 0) {
                    maxCount = count + 1;
                } else {
                    maxCount = count;
                }
            }

            --sushi[table[i - 1]];
            if (sushi[table[i - 1]] == 0)
                --count;

            if (sushi[table[(i + seq - 1) % size]] == 0)
                ++count;

            ++sushi[table[(i + seq - 1) % size]];
        }

        bw.append(String.valueOf(maxCount));
        bw.flush();
        bw.close();
        br.close();

    }
}