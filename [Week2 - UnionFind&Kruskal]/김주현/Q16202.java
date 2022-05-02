// 16202번 MST 게임 (G4) [MST-Kruskal][UnionFind]
/*
<문제 정보>
 1. 턴마다 MST 비용을 구하는데, 한턴이 넘어갈때마다 해당 턴의 MST에서 가장 적은 가중치 하나 제거
 2. 각 턴의 점수가 몇점인지 출력
 3. 각 간선의 가중치는 주어지는 순서대로 1,2,...,M

<변수 범위>
 1. 2초 / 512MB
 2. 정점 개수 2<=N<=1,000
 3. 간선 개수 1<=M<=min(10,000, N(N-1)/2)
 4. 턴의 개수 1<K<=100

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q16202 {
    static int N,M;
    static int[] parent;
    static Edge1[] edges;
    static int MSTcost;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        edges = new Edge1[M+1];
        int v1, v2;
        for (int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            edges[i] = new Edge1(v1, v2);
        }
        MSTcost = getMSTcost();
        sb.append(MSTcost).append(" "); K--;
        while(K-- >0) {
//            System.out.println("\nnew MST");
            if (MSTcost==0) {
                sb.append(0).append(" ");
                continue;
            }
            MSTcost = getMSTcost();
            sb.append(MSTcost).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void parentInitSet() {
        for (int i=1;i<=N;i++) parent[i] = i;
    }

    public static int find (int a) {
        if (parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if(a==b) return;
        if(a<b) parent[b]=a;
        else parent[a]=b;
    }
    
    // MST 비용 구하고 MST를 이루는 가장 적인 비용의 간선 제거
    public static int getMSTcost() {
        int cost=0;
        int start=-1;
        int edgeCount=0;
        boolean findMST = false;
        parentInitSet();
        for (int i=1;i<=M;i++) {
            if (edges[i].removed) continue;
            if (find(edges[i].v1)==find(edges[i].v2)) continue;
//            System.out.printf("%d 추가\n",i);
            if (start==-1) start = i;
            union(edges[i].v1, edges[i].v2);
            cost+=i;
            edgeCount++;
            if (edgeCount==N-1) {
                findMST = true;
                break;
            }
        }
        if (findMST) edges[start].removeEdge();
        return findMST ? cost : 0;
    }
}

class Edge1 {
    int v1, v2;
    boolean removed;

    public Edge1 (int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
        removed = false;
    }

    public void removeEdge () {
        this.removed = true;
    }
}





























