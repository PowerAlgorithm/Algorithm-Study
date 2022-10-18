// 2610번 회의준비 (G2) [최단거리; Floyd-Warshall]
/*
<문제 정보>
 1. 위원회에서 모든 참석자들의 의사전달시간 중 최댓값이 최소가 되도록 대표 정하기

<변수 범위>
 1. 1초 / 128MB
 2. 회의 참석하는 사람 N 1<=N<=100

<프로그램 진행>
 1. UnionFind & Floyd-Warshall

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Q2610 {
    static int N;
//    static int parent[];
    static int dist[][];
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
//        parent = new int[N+1];
        dist = new int[N+1][N+1];
        for (int i=1;i<=N;i++) Arrays.fill(dist[i], INF);

        int M = Integer.parseInt(br.readLine());
        int a1, a2;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            a1 = Integer.parseInt(st.nextToken());
            a2 = Integer.parseInt(st.nextToken());
            dist[a1][a2] = 1;
            dist[a2][a1] = 1;
        }
        FloydWarshall();

        /*for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",dist[i][j]==INF ? 0 : dist[i][j]);
            System.out.println();
        }*/

        int[] maxTimeList = new int[N+1];
        Arrays.fill(maxTimeList, 0);
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                if(dist[i][j]!=INF) maxTimeList[i] = Math.max(maxTimeList[i],dist[i][j]);
            }
        }
        /*System.out.println();
        for (int i=1;i<=N;i++) System.out.printf("%d ",maxTimeList[i]);*/

        ArrayList<Committee> committees = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        int committeeNum=0;
        for (int i=1;i<=N;i++) {
            if (!visited[i]) {
//                System.out.printf("%d에서 새 위원회\n",i);
                visited[i] = true;
                committees.add(new Committee());
                committees.get(committeeNum).setRepresentative(i,maxTimeList[i]);
                for (int person=1;person<=N;person++) {
                    if (person!=i && dist[i][person]!=INF) {
                        visited[person] = true;
                        committees.get(committeeNum).setRepresentative(person,maxTimeList[person]);
                    }
                }
                committeeNum++;
            }
        }
        sb.append(committeeNum).append("\n");
        Collections.sort(committees);
        for (Committee committee : committees) sb.append(committee.representative).append("\n");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void FloydWarshall() {
        for (int via=1;via<=N;via++) {
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=N;j++) {
                    if (i!=j && i!=via && j!=via && dist[i][via]!=INF && dist[via][j]!=INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via]+dist[via][j]);
                    }
                }
            }
        }
    }


}

class Committee implements Comparable<Committee> {
    int maxTime;
    int representative;

    public Committee () {
        maxTime = Integer.MAX_VALUE;
        representative = 0;
    }

    public void setRepresentative (int candidate, int maxTime) {
        representative = this.maxTime>maxTime ? candidate : representative;
        this.maxTime = Math.min(this.maxTime,maxTime);
    }

    @Override
    public int compareTo (Committee o) {
        return this.representative - o.representative;
    }
}

/*
class UndirectedGraph {
    ArrayList<Integer>[] adjList;
    boolean[] visited;

    public UndirectedGraph (int size) {
        adjList = new ArrayList[size+1];
        visited = new boolean[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge (int v1, int v2) {
        adjList[v1].add(v2);
        adjList[v2].add(v1);
    }
}
*/


















