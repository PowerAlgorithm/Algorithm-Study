// 10216번 Count Circle Group_2 (G5) [UnionFind]
/*
<문제 정보>
 1. 적진 그룹 개수 출력
    - i번째 진영은 R_i거리 이내가 통신영역
    - 통신영역이 겹치면 상호 통신 가능
    - 거쳐서도 통신 가능

<변수 범위>
 1. 8초 / 256MB
 2. 진영 숫자 N  1<=N<=3,000
 3. 좌표 0<= x,y <=5,000
 4. 해당 진영의 R 0<=R<=5,000


<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q10216_2 {
    static int N; // 적군 진영 개수
    static int[] parent;
    static Enemy1[] Enemies;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int x, y, R;
        int groups;
        while(T-- >0) {
            N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            parentInitSet();
            Enemies = new Enemy1[N+1];
            groups=N;
            for (int i=1;i<=N;i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                R = Integer.parseInt(st.nextToken());
                Enemies[i] = new Enemy1(x, y, R);
            }
            for (int i=1;i<=N;i++) {
                for (int j=i+1;j<=N;j++) {

                    if (CanBeConnected(Enemies[i], Enemies[j])) {
                        if(find(i)==find(j)) continue;
                        union(i,j);
                        groups--;
                    }
                }
            }
            sb.append(groups).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean CanBeConnected (Enemy1 e1, Enemy1 e2) {
        int dx = e1.x-e2.x;
        int dy = e1.y-e2.y;
        int total = e1.radius + e2.radius;

        return total*total >= dx*dx + dy*dy;
    }

    public static void parentInitSet() {
        for (int i=1;i<=N;i++) parent[i]=i;
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a]=find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if(a==b) return; // union을 할 때마다 총 그룹수를 하나 빼는 방식이라 사이클 있으면 안됨
        if (a<b) parent[b] = a;
        else parent[a] = b;
    }
}

class Enemy1 {
    int x, y;
    int radius;

    public Enemy1 (int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}















