import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


/***
 * 키워드
 *  - 평범한 배낭
 *
 * 조건
 *
 * 풀이
 *
 */
class Bag {
    int w;
    int v;
    public Bag(int w, int v) {
        this.w = w;
        this.v = v;
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] values = new int[n+1][k+1]; // 세로 물건, 가로 무게, 값 가치
        Bag[] bag = new Bag[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bag[i] = new Bag(w, v);
        }
//        Arrays.sort(bag, Comparator.comparing((a, b) -> a))
        for(int i=1; i<n+1; i++) {
            Bag now = bag[i-1];
            for(int j=1; j<k+1; j++) {
                if (now.w <= j) {
                    values[i][j] = Math.max(
                            values[i - 1][j],
                            values[i - 1][j - now.w] + now.v);
                } else {
                    values[i][j] = values[i - 1][j];
                }
            }
        }
//        Arrays.stream(values).forEach(System.out::println);
//        for(var v : values) {
//            for(var f : v) {
//                System.out.print(f +" ");
//            }
//            System.out.println();
//        }
        System.out.println(values[n][k]);


        br.close();

    }


}