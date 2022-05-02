// 1185번 유럽여행 (P4) [Kruskal]
/*
<문제 정보>
 1. 모든 N개의 국가를 여행하고 시작나라로 돌아올 때 최소비용
    - 나라별 요금
    - 통과하는 길 당 요금
    - 나라, 길 중복 방문 가능
    - 길은 N-1개만 남긴다.

<변수 범위>
 1. 2초 / 128MB
 2. 나라의 수 N 5<=N<=10,000
 3. 연결하는 길의 수 N-1<=P<=100,000
 4. 비용 C 1<=C<=1,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Q1185 {
    static int N;
    static int[] countryCosts;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        countryCosts = new int[N+1];
        parent = new int[N+1];
        for (int i=1;i<=N;i++) countryCosts[i] = Integer.parseInt(br.readLine());

//        Path1[] paths = new Path1[P];
        PriorityQueue<Path1> pq = new PriorityQueue<>();
        int country1, country2, cost;
        for (int i=0;i<P;i++) {
            st = new StringTokenizer(br.readLine());
            country1 = Integer.parseInt(st.nextToken());
            country2 = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
//            paths[i] = new Path1(country1, country2, countryCosts[country1], countryCosts[country2], cost);
            pq.add(new Path1(country1, country2, countryCosts[country1], countryCosts[country2], cost));
        }

        // Kruskal
        int connectedCountry = 1;
        parentInitSet();
        Path1 path;
        int travelCost = 0;
        for (int i=0;i<P;i++) {
            path = pq.poll();
            if (find(path.country1)==find(path.country2)) continue;
            union(path.country1, path.country2);
            travelCost += path.cost;
            if(++connectedCountry==N) break;
        }

        // 나라별 비용중 최소인 나라 찾기
        int min = Integer.MAX_VALUE;
        for (int i=1;i<=N;i++) min = Math.min(min, countryCosts[i]);

        // 여행비용에 최소비용인 나라를 한번 더함
        travelCost += min;

        sb.append(travelCost);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void parentInitSet() {
        for (int i=1;i<=N;i++) parent[i] = i;
    }

    public static int find (int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if (a<b) parent[b] = a;
        else parent[a] = b;
    }
}

class Path1 implements Comparable<Path1>{
    int country1, country2;
    int cost;

    public Path1 (int country1, int country2, int country1_cost, int country2_cost, int pathCost) {
        this.country1 = country1;
        this.country2 = country2;
        this.cost = country1_cost + country2_cost + (pathCost*2);
    }

    public int compareTo (Path1 o) {
        return this.cost - o.cost;
    }
}
















