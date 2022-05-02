// 1939번 중량제한 (G4) [Kruskal][UnionFind]
/*
<문제 정보>
 1. 공장과 공장 사이 한 번의 이동에서 옮길 수 있는 물품들 중량의 최댓값 출력

<변수 범위>
 1. 1초 / 128MB
 2. 섬 N개  2<=N<=10,000
 3. M개의 다리 1<=M<=100,000
 4. 중량 제한 C 1<=C<=1,000,000,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Q1939 {
    static int N;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        parentInitSet();
        PriorityQueue<Bridge1> pq = new PriorityQueue<>();
        int f1, f2, weightLimit;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            f1 = Integer.parseInt(st.nextToken());
            f2 = Integer.parseInt(st.nextToken());
            weightLimit = Integer.parseInt(st.nextToken());
            pq.add(new Bridge1(f1, f2, weightLimit));
        }
        st = new StringTokenizer(br.readLine());
        int factory1 = Integer.parseInt(st.nextToken());
        int factory2 = Integer.parseInt(st.nextToken());

        Bridge1 bridge = new Bridge1(0,0,0);
        while (find(factory1)!=find(factory2)) {
            bridge = pq.poll();
            union(bridge.factory1, bridge.factory2);
        }

        sb.append(bridge.weightLimit);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void parentInitSet() {
        for (int i=1;i<=N;i++) parent[i]=i;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a]=find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a<b) parent[b]=a;
        else parent[a]=b;
    }
}

class Bridge1 implements Comparable<Bridge1> {
    int factory1;
    int factory2;
    int weightLimit;

    public Bridge1 (int factory1, int factory2, int weightLimit) {
        this.factory1 = factory1;
        this.factory2 = factory2;
        this.weightLimit = weightLimit;
    }

    @Override
    public int compareTo(Bridge1 o1) {
        return o1.weightLimit - this.weightLimit;
    }
}
















