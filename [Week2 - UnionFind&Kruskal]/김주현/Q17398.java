// 17398번 통신망 분할 (G1) [Kruskal]
/*
<문제 정보>
 1. Q번의 통신 연결 제거를 통해 나오는 비용의 합 출력
    - 제거를 통해 망이 나누어졌다면 비용은 나누어진 망에 속한 통신탑 개수의 곱
    - 나누어지지 않았다면 비용은 0

<변수 범위>
 1. 1초 / 512MB
 2. 통신탑의 개수 1<=N<=100,000
 3. 통신탑 연결의 개수 1<=M<=100,000
 4. 제거 횟수 1<=Q<=M

 **주의** 비용의 합이 long 범위로 넘어갈 수 있음!!
 50,000 * 50,000 = 2,500,000,000

 ** N*Q -> 100,000,000,000 (1초 초과)

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q17398 {
    static int N;
    static int[] parent;
    static Connection[] connections;
    static int[] removedList;
    static int[] connectedTower;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        connectedTower = new int[N+1];
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        removedList = new int[Q+1];
        connections = new Connection[M+1];
        int t1, t2;
        for (int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            t1 = Integer.parseInt(st.nextToken());
            t2 = Integer.parseInt(st.nextToken());
            connections[i] = new Connection(t1, t2);
        }
        for (int i=1;i<=Q;i++) {
            removedList[i] = Integer.parseInt(br.readLine());
            connections[removedList[i]].remove();
        }

        parentInitSet();
        Arrays.fill(connectedTower,1);
        for (int i=1;i<=M;i++) {
            if(connections[i].removed) continue;
            union(connections[i].t1, connections[i].t2);
        }

        System.out.println("초기 상태");
        System.out.print("[parent] -> ");
        for (int i=1;i<=N;i++) System.out.printf("%d ",parent[i]);
        System.out.println();
        System.out.print("[connectedTower] -> ");
        for (int i=1;i<=N;i++) System.out.printf("%d ",connectedTower[i]);
        System.out.println();


        long totalCost = 0;
        for (int i=Q;i>=1;i--) {
            totalCost += getCost(i);
            System.out.printf("%d번째 연결 합침\n",i);
            System.out.print("[parent] -> ");
            for (int j=1;j<=N;j++) System.out.printf("%d ",parent[j]);
            System.out.println();
            System.out.print("[connectedTower] -> ");
            for (int j=1;j<=N;j++) System.out.printf("%d ",connectedTower[j]);
            System.out.println();
            System.out.printf("totalcost : %d\n",totalCost);
            System.out.println();
        }

        sb.append(totalCost);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // order 번째 연결을 제거했을 경우 비용
    public static long getCost (int order) {
        Connection removed = connections[removedList[order]];

        // 1. removed 대상의 두개 tower가 각각 속한 집합의 루트가 같으면 비용 0
        // 2. 루트가 다르다면 union 하고 connectedTower 값의 곱을 반환

        // 1. 루트가 같다면
        if (find(removed.t1)==find(removed.t2)) return 0;

        // 2. 루트가 다르다면
        System.out.printf("첫번째 그룹 수 : %d\n",getNetworkNum(removed.t1));
        System.out.printf("두번째 그룹 수 : %d\n",getNetworkNum(removed.t2));

        long cost = (long) getNetworkNum(removed.t1) * getNetworkNum(removed.t2);
        System.out.printf("%d번째 합침 대상 : %d, %d\n",order, removed.t1, removed.t2);
        union(removed.t1, removed.t2);
        return cost;
    }

    // tower가 속한 통신망의 총 타워 숫자
    public static int getNetworkNum (int tower) {
        return connectedTower[find(tower)];
    }

    public static void parentInitSet() {
        for (int i=1;i<=N;i++) parent[i] = i;
    }

    public static int find (int a) {
        if (parent[a]==a) return a;
        return parent[a]=find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if (a==b) return;
        if (a<b) {
            parent[b] = a;
            connectedTower[a]+=connectedTower[b];
        }
        else {
            parent[a] = b;
            connectedTower[b]+=connectedTower[a];
        }
    }

}

class Connection {
    int t1, t2;
    boolean removed;

    public Connection (int t1, int t2) {
        this.t1 = t1;
        this.t2 = t2;
        this.removed = false;
    }

    public void remove() {
        this.removed = true;
    }
}


















