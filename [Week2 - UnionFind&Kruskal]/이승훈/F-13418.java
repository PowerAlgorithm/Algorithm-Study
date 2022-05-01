import java.io.*;
import java.util.*;

/**
 * 키워드
 * - 이동 경로를 짜보기로 함
 * - 최소한의 길로 모든 건물을 방문
 *
 * 조건
 * - 피로도는 최초 조사된 길을 기준으로 함
 * - 최악과 최적의 피로도 차이 계산
 * - 같은 경로 상에 2개 이상의 도로는 주어지지 않음
 * - 입구에서 모든 건물로 갈 수 있음이 보장됨
 *
 * 풀이
 * - 내리막만 추출해서 연결 (크루스칼)
 * - 그 후에 오르막길 연결
 *
 */
class Main {
    static int[] parent;

    public static int find(int a)
    {
        if(a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b)
    {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot < bRoot)
            parent[bRoot] = aRoot;
        else
            parent[aRoot] = bRoot;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        initParent(N);
        List<Road> roads = new ArrayList<>();

        for(int i=0; i<M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            roads.add(new Road(a, b, c));
        }
        Collections.sort(roads, new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o2.c - o1.c;
            }
        });

        int minVal = 0;
        int maxVal = 0;
        for(Road r : roads) {
            if(find(r.a) != find(r.b)) {
                union(r.a, r.b);
                minVal += (r.c == 0 ? 1 : 0);
            }
        }

        initParent(N);
        Collections.sort(roads, (Road a, Road b) -> a.c - b.c);
        for(Road r : roads) {
            if(find(r.a) != find(r.b)) {
                union(r.a, r.b);
                maxVal += (r.c == 0 ? 1 : 0);
            }
        }
        System.out.println(maxVal * maxVal - minVal * minVal);
        br.close();
    }

    private static void initParent(int n) {
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            parent[i] = i;
    }
}

class Road implements Comparator<Road> {
    int a;
    int b;
    int c;

    public Road(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public int compare(Road roadA, Road roadB) {
        if (roadA.c < roadB.c)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "a, b, c : " + a + ", "+ b + ", " + c;
    }
}