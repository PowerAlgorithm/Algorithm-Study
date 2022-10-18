// 10775번 공항 (G2) [UnionFind - 응용]
/*
<문제 정보>
 1. 도킹시킬 수 있는 비행기의 최댓값 출력
    - i번째 비행기를 1번부터 g_i 번째 게이트 중 하나에 영구적으로 도킹
    - 비행기가 어느 공항에도 도킹할 수 없다면 공항이 폐쇄, 이후 비행기들 도킹 불가

<변수 범위>
 1. 게이트의 수 G 1<=G<=10^5
 2. 비행기의 수 P 1<=P<=10^5
 3. 1<=g_i<=G

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

//280 인데 240도 나옴

import java.io.*;

public class Q10775 {
    static int N;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        parentInitSet();
        int maxDockedAirplanes=0;
        int g_i;
        for (int i=1;i<=M;i++) {
            // i번째 비행기를 도킹
            g_i = Integer.parseInt(br.readLine());
//            System.out.println(g_i);
            if (parent[find(g_i)]==0) break;
            maxDockedAirplanes++;
            parent[find(g_i)]--;
            union(g_i, parent[find(g_i)]);

            /*System.out.printf("%d번째 비행기 도킹 후 상황\n",i);
            for (int p : parent) System.out.printf("%d ",p);
            System.out.println();*/
        }

        sb.append(maxDockedAirplanes);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void parentInitSet() {
        for (int i=0;i<=N;i++) parent[i] = i;
    }

    public static int find (int a) {
        if (parent[a]==a) return a;
        return parent[a]=find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);

        if (a<b) parent[b] = a;
        parent[a] = b;
    }
}

