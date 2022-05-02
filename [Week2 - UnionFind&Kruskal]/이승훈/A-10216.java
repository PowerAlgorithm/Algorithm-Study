import java.io.*;
import java.util.StringTokenizer;

/**
 * 키워드
 * - i와 j가 간접 통신이 되면 상호간에 통신이 가능한 것으로 본다.
 * - 상호간에 통신이 가능한 부대끼리는 결집력있는 한 그룹처럼 행동한다
 *
 * 조건
 * - O(3000 * 3000)으로 각 진영들이 같은 그룹인지 찾아본다.
 *
 * 풀이
 * - 반경 안에 있는지 계산을 할 때 root를 씌우는 것이 아닌 제곱으로 비교 (소수점 issue)
 * - 그룹의 개수를 알아내는 문제 == union find로 가능
 *
 */
class Main {
    static int[] parent;
    static int[][] towers;

    public static int find(int a)
    {
        if(parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b)
    {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot < bRoot)
            parent[bRoot] = aRoot;
        else
            parent[aRoot] = bRoot;
    }

    public static boolean findConnect(int[] towerA, int[] towerB)
    {
        int x_dif = towerA[0] - towerB[0];
        int y_dif = towerA[1] - towerB[1];
        int r = towerA[2] + towerB[2];
        int dist = x_dif * x_dif + y_dif * y_dif;
        if (dist <= r * r) {
            union(towerA[3], towerB[3]);
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(br.readLine());
            towers = new int[n][4];
            parent = new int[n];
            for(int i=0; i<n; i++) {
                parent[i] = i;
            }
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                towers[i][0] = x;
                towers[i][1] = y;
                towers[i][2] = r;
                towers[i][3] = i;
            }
            int ans = n;
            for(int i=0; i<n; i++) {
                for(int j=i+1; j<n; j++) {
                    if(find(i)==find(j))
                        continue;
                    if (findConnect(towers[i], towers[j]))
                        ans--;
                }
            }
            System.out.println(ans);
        }
        br.close();
    }

}