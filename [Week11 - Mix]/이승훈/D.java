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

        Pair[] arr = new Pair[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);

        int cnt = 0;
        int end = 0;
        for(Pair p : arr) {
            if (p.start >= end) {
                cnt += 1;
                end = p.end;
            }
        }
        System.out.println(cnt);
        br.close();
    }
}