// 6497번 전력난 (G4) [MST - Kruskal]
/*
<문제 정보>
 1. 도시에 있는 모든 두 집쌍에 대해 왕래할 수 있으면서 절약할 수 있는 최대 액수

<변수 범위>
 1. 1초 / 256MB
 2. 집의 수 m 1<=m<=200,000
 3. 길의 수 n m-1<=n<=200,000
 4. 도시상의 모든 길의 거리의 합은 2^31 이하

<프로그램 진행>
 1. Kruskal로 불켜지는데 최소 비용을 구하고 전체 합에서 빼기

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Q6497 {
    static int N;
    static int[] parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N==0) break;
            int M = Integer.parseInt(st.nextToken());
            parent = new int[N];
            PriorityQueue<Street> pq = new PriorityQueue<>();
            int h1, h2, dist;
            int firstBudget=0;
            while(M-- >0) {
                st = new StringTokenizer(br.readLine());
                h1 = Integer.parseInt(st.nextToken());
                h2 = Integer.parseInt(st.nextToken());
                dist = Integer.parseInt(st.nextToken());
                pq.add(new Street(h1, h2, dist));
                firstBudget+=dist;
            }

            int reducedBudget=0;
            Street street;
            int connectedHouse=0;
            parentInitSet();
            while(!pq.isEmpty()) {
                street = pq.poll();
//                System.out.printf("house1 : %d, house2 : %d, dist : %d\n", street.house1, street.house2, street.dist);
                if (find(street.house1)==find(street.house2)) continue;
//            System.out.printf("house1 : %d, house2 : %d\n", street.house1, street.house2);
                union(street.house1, street.house2);
                reducedBudget+=street.dist;
                connectedHouse++;
                if (connectedHouse==N-1) break;
            }
//        System.out.printf("firstBudget: %d\n",firstBudget);
//        System.out.printf("reducedBudget: %d\n",reducedBudget);
            sb.append(firstBudget-reducedBudget).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void parentInitSet() {
        for (int i=0;i<N;i++) parent[i] = i;
    }

    public static int find(int a) {
        if (parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a==b) return;
        if (a<b) parent[b] = a;
        else parent[a] = b;
    }
}

class Street implements Comparable<Street>{
    int house1, house2;
    int dist;

    public Street (int house1, int house2, int dist) {
        this.house1 = house1;
        this.house2 = house2;
        this.dist = dist;
    }

    @Override
    public int compareTo(Street o) {
        return this.dist - o.dist;
    }
}


















