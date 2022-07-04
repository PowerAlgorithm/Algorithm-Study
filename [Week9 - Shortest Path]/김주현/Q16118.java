/*
// 16118번 달빛 여우 (G1)
*/
/*
<문제 정보>
 1. 달빛 여우 : 항상 일정한 속도 / 달빛 늑대 : 오솔길 한번 한번 번갈아 가며 여우의 2배, 절반 의 속도로 간다.
    - 오솔길은 양방향
    - 각자 목적지까지 가장 빠르게 가는 경로를 택함
    - 달빛여우가 더 먼저 도달 할 수 있는 그루터기가 몇개 있는지 출력

<변수 범위>
 1. 1초 / 512MB
 2. 그루터기 수 N 2<=N<=4,000
 3. 오솔길의 수 1<=M=100,000
 4. 오솔길 길이 1<=d<=100,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 *//*


import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q16118 {
    static int N, M;
    static WGraph g;
    static int[] fox;
    static int[] wolf;
    static int visitPossible = 0;

    static final int INF = Integer.MAX_VALUE;
    static final boolean FAST = true;
    static final boolean SLOW = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new WGraph(N);
        fox = new int[N+1];
        wolf = new int[N+1];
        Arrays.fill(fox, INF);
        fox[1] = 0;
        int v1, v2, dist;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            g.addEdge(v1, v2, dist*2);
        }
//        bfs();
//        System.out.printf("도달 가능 수 : %d\n",visitPossible);
        dijkstra_fox();
        dijkstra_wolf();
        */
/*for (int i=1;i<=N;i++) System.out.printf("%d ",fox[i]);
        System.out.println();
        for (int i=1;i<=N;i++) System.out.printf("%f ",wolf[i]);
        System.out.println();*//*

        int ans=0;
        for (int i=1;i<=N;i++) {
            if (fox[i]<wolf[i]) ans++;
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void dijkstra_fox() {
        PriorityQueue<VPair> pq = new PriorityQueue<>();
        pq.add(new VPair(1,0));
        boolean[] visited = new boolean[N+1];
        VPair now;
        int visitNum = 0;

        while(!pq.isEmpty()) {
            if(visitNum==visitPossible) break;
            now = pq.poll();
            if(visited[now.v]) continue;
            visited[now.v] = true;
            visitNum++;

            for (VPair v : g.adjList[now.v]) {
                if (fox[v.v] > now.d + v.d) {
                    fox[v.v] = now.d+v.d;
                    if(!visited[v.v]) pq.add(new VPair(v.v, fox[v.v]));
                }
            }
        }
    }

    public static void dijkstra_wolf() {
        PriorityQueue<Distance> pq = new PriorityQueue<>();
        pq.add(new Distance(1,0,SLOW));
        int[] wolfFast = new int[N+1];
        int[] wolfSlow = new int[N+1];
        Arrays.fill(wolfFast, INF);
        Arrays.fill(wolfSlow, INF);
//        wolfFast[1] = 0;
        wolfSlow[1] = 0;
        boolean[] visitedFast = new boolean[N+1];
        boolean[] visitedSlow = new boolean[N+1];
//        visitedFast[1] = true;
        Distance now;
        int visitedFastNum = 0;
        int visitedSlowNum = 0;

        while(!pq.isEmpty()) {
            if(visitedFastNum==visitPossible && visitedSlowNum==visitPossible) break;
            now = pq.poll();
            if(now.velocity) {
                if (visitedFast[now.v]) continue;
                else {
                    visitedFast[now.v]=true;
                    visitedFastNum++;
                }
            }
            if(!now.velocity) {
                if (visitedSlow[now.v]) continue;
                else {
                    visitedSlow[now.v]=true;
                    visitedSlowNum++;
                }
            }

            for (VPair v : g.adjList[now.v]) {
                if (now.velocity) {
                    if (wolfSlow[v.v] > now.dist + (v.d*4)) {
                        wolfSlow[v.v] = now.dist + (v.d*4);
                        if(!visitedSlow[v.v]) pq.add(new Distance(v.v, wolfSlow[v.v], SLOW));
                    }
                }
                else {
                    if (wolfFast[v.v] > now.dist + v.d) {
                        wolfFast[v.v] = now.dist + v.d;
                        if(!visitedFast[v.v]) pq.add(new Distance(v.v, wolfFast[v.v], FAST));
                    }
                }
            }
        }
        for (int i=1;i<=N;i++) wolf[i] = Math.min(wolfFast[i], wolfSlow[i]);
    }

    */
/*public static void bfs() {
        boolean[] visited = new boolean[N+1];
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;
        int now;
        visitPossible++;

        while(!q.isEmpty()){
            now = q.poll();

            for (VPair v : g.adjList[now]) {
                if (!visited[v.v]) {
                    visited[v.v]=true;
                    visitPossible++;
                    q.add(v.v);
                }
            }
        }
    }*//*

}

class Distance implements Comparable<Distance>{
    int v, dist;
    boolean velocity;

    public Distance (int v, int dist, boolean velocity) {
        this.v = v;
        this.dist = dist;
        this.velocity = velocity;
    }

    @Override
    public int compareTo (Distance o) {
        return this.dist-o.dist;
    }
}

class WGraph {
    ArrayList<VPair>[] adjList;

    public WGraph (int size) {
        adjList = new ArrayList[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge(int v1, int v2, int dis) {
        adjList[v1].add(new VPair(v2, dis));
        adjList[v2].add(new VPair(v1, dis));
    }
}

class VPair implements Comparable<VPair> {
    int v, d;

    public VPair (int v, int d) {
        this.v = v;
        this.d = d;
    }

    @Override
    public int compareTo (VPair o) {
        return this.d-o.d;
    }
}














*/

/*
import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;

public class Q16118 {
    static int N, M;
    static WGraph g;
    static int[] fox;
    static int[] wolf;

    static final int INF = Integer.MAX_VALUE;
    static final boolean FAST = true;
    static final boolean SLOW = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new WGraph(N);
        fox = new int[N+1];
        wolf = new int[N+1];
        Arrays.fill(fox, INF);
        fox[1] = 0;
        int v1, v2, dist;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            g.addEdge(v1, v2, dist*2);
        }
        dijkstra_fox();
        dijkstra_wolf();
        */
/*for (int i=1;i<=N;i++) System.out.printf("%d ",fox[i]);
        System.out.println();
        for (int i=1;i<=N;i++) System.out.printf("%d ",wolf[i]);
        System.out.println();*//*

        int ans=0;
        for (int i=1;i<=N;i++) {
            if (fox[i]<wolf[i]) ans++;
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void dijkstra_fox() {
        PriorityQueue<VPair> pq = new PriorityQueue<>();
        pq.add(new VPair(1,0));
        VPair now;

        while(!pq.isEmpty()) {
            now = pq.poll();
            if(now.d>fox[now.v]) continue;

            for (VPair v : g.adjList[now.v]) {
                if (fox[v.v] > now.d + v.d) {
                    fox[v.v] = now.d+v.d;
                    pq.add(new VPair(v.v, fox[v.v]));
                }
            }
        }
    }

    public static void dijkstra_wolf() {
        PriorityQueue<Distance> pq = new PriorityQueue<>();
        pq.add(new Distance(1,0,SLOW));
        int[] wolfFast = new int[N+1];
        int[] wolfSlow = new int[N+1];
        Arrays.fill(wolfFast, INF);
        Arrays.fill(wolfSlow, INF);
        wolfSlow[1] = 0;
        Distance now;

        while(!pq.isEmpty()) {
            now = pq.poll();
            if(now.velocity) {
                if (now.dist>wolfFast[now.v]) continue;
            }
            if(!now.velocity) {
                if (now.dist>wolfSlow[now.v]) continue;
            }

            for (VPair v : g.adjList[now.v]) {
                if (now.velocity) {
                    if (wolfSlow[v.v] > now.dist + (v.d<<1)) {
                        wolfSlow[v.v] = now.dist + (v.d<<1);
                        pq.add(new Distance(v.v, wolfSlow[v.v], SLOW));
                    }
                }
                else {
                    if (wolfFast[v.v] > now.dist + (v.d>>1)) {
                        wolfFast[v.v] = now.dist + (v.d>>1);
                        pq.add(new Distance(v.v, wolfFast[v.v], FAST));
                    }
                }
            }
        }

        for (int i=1;i<=N;i++) wolf[i] =Math.min(wolfFast[i], wolfSlow[i]);
    }
}

class Distance implements Comparable<Distance>{
    int v, dist;
    boolean velocity;

    public Distance (int v, int dist, boolean velocity) {
        this.v = v;
        this.dist = dist;
        this.velocity = velocity;
    }

    @Override
    public int compareTo (Distance o) {
        return this.dist-o.dist;
    }
}

class WGraph {
    ArrayList<VPair>[] adjList;

    public WGraph (int size) {
        adjList = new ArrayList[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge(int v1, int v2, int dis) {
        adjList[v1].add(new VPair(v2, dis));
        adjList[v2].add(new VPair(v1, dis));
    }
}

class VPair implements Comparable<VPair> {
    int v, d;

    public VPair (int v, int d) {
        this.v = v;
        this.d = d;
    }

    @Override
    public int compareTo (VPair o) {
        return this.d-o.d;
    }
}*/

import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;

public class Q16118 {
    static int N, M;
    static WGraph g;
    static int[] fox;
    static int[] wolf;

    static final int INF = Integer.MAX_VALUE;
    static final boolean FAST = true;
    static final boolean SLOW = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new WGraph(N);
        fox = new int[N+1];
        wolf = new int[N+1];
        Arrays.fill(fox, INF);
        fox[1] = 0;
        int v1, v2, dist;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            g.addEdge(v1, v2, dist*2);
        }
        dijkstra_fox();
        dijkstra_wolf();
        int ans=0;
        for (int i=1;i<=N;i++) {
            if (fox[i]<wolf[i]) ans++;
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void dijkstra_fox() {
        PriorityQueue<VPair> pq = new PriorityQueue<>();
        pq.add(new VPair(1,0));
        boolean[] visited = new boolean[N+1];
        VPair now;

        while(!pq.isEmpty()) {
            now = pq.poll();
            if(visited[now.v]) continue;
            visited[now.v] = true;

            for (VPair v : g.adjList[now.v]) {
                if (fox[v.v] > now.d + v.d) {
                    fox[v.v] = now.d+v.d;
                    if(!visited[v.v]) pq.add(new VPair(v.v, fox[v.v]));
                }
            }
        }
    }

    public static void dijkstra_wolf() {
        PriorityQueue<Distance> pq = new PriorityQueue<>();
        pq.add(new Distance(1,0,SLOW));
        int[] wolfFast = new int[N+1];
        int[] wolfSlow = new int[N+1];
        Arrays.fill(wolfFast, INF);
        Arrays.fill(wolfSlow, INF);
        wolfSlow[1] = 0;
        boolean[] visitedFast = new boolean[N+1];
        boolean[] visitedSlow = new boolean[N+1];
        Distance now;

        while(!pq.isEmpty()) {
            now = pq.poll();
            if(now.velocity) {
                if (visitedFast[now.v]) continue;
                else visitedFast[now.v]=true;
            }
            if(!now.velocity) {
                if (visitedSlow[now.v]) continue;
                else visitedSlow[now.v]=true;
            }

            for (VPair v : g.adjList[now.v]) {
                if (now.velocity) {
                    if (wolfSlow[v.v] > now.dist + (v.d<<1)) {
                        wolfSlow[v.v] = now.dist + (v.d<<1);
                        pq.add(new Distance(v.v, wolfSlow[v.v], SLOW));
                    }
                }
                else {
                    if (wolfFast[v.v] > now.dist + (v.d>>1)) {
                        wolfFast[v.v] = now.dist + (v.d>>1);
                        pq.add(new Distance(v.v, wolfFast[v.v], FAST));
                    }
                }
            }
        }

        for (int i=1;i<=N;i++) wolf[i] = Math.min(wolfFast[i], wolfSlow[i]);
    }
}

class Distance implements Comparable<Distance>{
    int v, dist;
    boolean velocity;

    public Distance (int v, int dist, boolean velocity) {
        this.v = v;
        this.dist = dist;
        this.velocity = velocity;
    }

    @Override
    public int compareTo (Distance o) {
        return this.dist-o.dist;
    }
}

class WGraph {
    ArrayList<VPair>[] adjList;

    public WGraph (int size) {
        adjList = new ArrayList[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge(int v1, int v2, int dis) {
        adjList[v1].add(new VPair(v2, dis));
        adjList[v2].add(new VPair(v1, dis));
    }
}

class VPair implements Comparable<VPair> {
    int v, d;

    public VPair (int v, int d) {
        this.v = v;
        this.d = d;
    }

    @Override
    public int compareTo (VPair o) {
        return this.d-o.d;
    }
}