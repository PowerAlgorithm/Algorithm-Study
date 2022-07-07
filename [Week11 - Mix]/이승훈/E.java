import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */
class Pair implements Comparable<Pair>{
    int start;
    int end;

    @Override
    public String toString() {
        return "Pair{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.end == o.end) {
            return this.start - o.start;
        }
        return this.end - o.end;
    }
}
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] arr = new int[n][];
        PriorityQueue<Pair> q = new PriorityQueue<>();
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        Arrays.sort(arr, Comparator.comparing(a -> a[0]));

        int ans = 0;
        for(int[] p : arr) {
            if(q.isEmpty()) {
                q.add(new Pair(p[0], p[1]));
                ans = Math.max(ans, 1);
            } else {
                Pair peek = q.peek();
                while (p[0] >= peek.end) {
                    q.poll();
                    peek = q.peek();
                }
                q.add(new Pair(p[0], p[1]));
                ans = Math.max(ans, q.size());
            }
        }
        System.out.println(ans);
        br.close();
    }
}