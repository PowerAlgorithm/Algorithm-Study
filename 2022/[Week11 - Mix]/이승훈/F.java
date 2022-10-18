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
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            int n = Integer.parseInt(br.readLine());
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            StringTokenizer st;
            for(int j=0; j<n-1; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                hashMap.put(b, a);
            }
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            HashSet<Integer> set = new HashSet<>();

            int parent;
            int child = a;
            set.add(child);
            while (hashMap.containsKey(child)) {
                parent = hashMap.get(child);
                set.add(parent);
                child = parent;
            }
            child = b;
            if (set.contains(child)) {
                System.out.println(child);
                continue;
            }
            while (hashMap.containsKey(child)) {
//                System.out.println("fuck");
                parent = hashMap.get(child);
                if (set.contains(parent)) {
                    System.out.println(parent);
                    break;
                }
                child = parent;
            }

        }

        StringTokenizer st;

        br.close();
    }
}