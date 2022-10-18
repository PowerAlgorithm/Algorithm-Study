// 16168번 퍼레이드 (G4) [UnionFind][Eulerian Trail]
/*
<문제 정보>
 1. 민원을 받지 않으면서 모든 구간을 지나는 경로가 있으면 YES 없으면 NO
    - 같은 연결구간 두 번이상 지날경우 민원
    - 같은 지점은 두번 이상 지나도 됨
    - 모든 지점과 모든 연결구간을 전부 지나야 함

<변수 범위>
 1. 2초 / 128MB
 2. 연결지점 V, 연결구간 E 1<=V<=E<=3,000

<프로그램 진행>
 1. 한 붓 그리기

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q16168 {
    static int V;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        parentInitSet();
        int[] degree = new int[V+1];

        int v1, v2;
        int connectedVertex=0;
        boolean allConnected = false;
        while (E-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            degree[v1]++;
            degree[v2]++;
            if (!allConnected && find(v1)!=find(v2)) {
                union(v1, v2);
                connectedVertex++;
                if (connectedVertex == V-1) allConnected = true;
            }
        }

        String ans = allConnected && IsThisEulerianTrail(degree) ? "YES" : "NO";
        sb.append(ans);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean IsThisEulerianTrail (int[] degree) {
        int oddDegree=0;
        for (int i=1;i<=V;i++) {
            if (degree[i]%2==1) oddDegree++;
        }
        return oddDegree==0 || oddDegree==2;
    }

    public static void parentInitSet() {
        for (int i=1;i<=V;i++) parent[i] = i;
    }

    public static int find (int a) {
        if (parent[a]==a) return a;
        return parent[a]=find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if (a<b) parent[b] = a;
        else parent[a] = b;
    }
}















