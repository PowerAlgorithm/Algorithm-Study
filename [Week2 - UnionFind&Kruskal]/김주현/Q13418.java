// 13418번 학교 탐방하기 (G3) [MST - Kruskal]
/*
<문제 정보>
 1. 모든 건물을 방문하는데 필요한 최소한의 길을 선택할 때 최악과 최선의 피로도 차를 출력
    - 오르막길을 k번 오르면 피로도는 k^2
    - 입구는 0 , 항상 1번 건물과 연결되어 있다.
    - 입구에서 모든 건물로 갈 수 있음이 보장된다.

<변수 범위>
 1. 1초 / 256MB
 2. 건물의 수 1<=N<=1,000
 3. 도로의 개수 1<=M<=N(N-1)/2

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Q13418 {
    static int N;
    static int[] parent;
    static ArrayList<Path>[] paths;
    static final int UP = 0;
    static final int DOWN = 1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); M++;
        parent = new int[N+1];
        paths = new ArrayList[2];
        for (int i=0;i<2;i++) paths[i] = new ArrayList<>();

        int b1, b2, UpDownStatus;
        while (M-- >0) {
            st = new StringTokenizer(br.readLine());
            b1 = Integer.parseInt(st.nextToken());
            b2 = Integer.parseInt(st.nextToken());
            UpDownStatus = Integer.parseInt(st.nextToken());
            paths[UpDownStatus].add(new Path(b1, b2));
        }

        int minFatigue = getFatigue(DOWN);
        int maxFatigue = getFatigue(UP);

//        System.out.printf("minFatigue : %d\n",minFatigue);
//        System.out.printf("maxFatigue : %d\n",maxFatigue);
        sb.append(maxFatigue - minFatigue);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getFatigue (int firstStatus) {
        int secondStatus = firstStatus==UP ? DOWN : UP;

        // statusCheck[0] : usedEdges
        // statusCheck[1] : fatigue
        int[] statusCheck;

        parentInitSet();
        statusCheck = SearchPath(firstStatus, 0);
        if (statusCheck[0]==N || firstStatus == UP) return statusCheck[1]*statusCheck[1];
//        System.out.printf("First status was %s.\n",firstStatus==UP ? "UP" : "DOWN");
//        System.out.printf("usedEdges : %d\n",statusCheck[0]);
        statusCheck = SearchPath(secondStatus, statusCheck[0]);
        return statusCheck[1]*statusCheck[1];
    }

    public static int[] SearchPath (int UpDownStatus, int usedEdges) {
        int fatigue = 0;
        for (Path path : paths[UpDownStatus]) {
            if (find(path.building1)==find(path.building2)) continue;
            union(path.building1, path.building2);
            if (UpDownStatus==UP) fatigue++;
            usedEdges++;
            if (usedEdges == N) break;
        }
        return new int[] {usedEdges, fatigue};
    }

    public static void parentInitSet() {
        for (int i=0;i<=N;i++) parent[i]=i;
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

class Path {
    int building1, building2;

    public Path (int building1, int building2) {
        this.building1 = building1;
        this.building2 = building2;
    }
}
