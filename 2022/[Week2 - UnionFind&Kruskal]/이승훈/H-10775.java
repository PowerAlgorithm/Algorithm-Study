import java.io.*;
import java.util.*;

/**
 * 키워드
 * -
 *
 * 조건
 * -  10^5개의 비행기와 게이트 == 특정 게이트로부터 옆으로 이동하는 방식 사용 불가능
 *
 * 풀이
 * - 주어진 번호로부터 우측에서 좌측으로 이동하며 빈 게이트를 찾아야한다.
 * - 자신의 게이트에 도킹할 수 없을 때 도킹할 수 있는 게이트를 바로 알 수 있어야한다.
 * - 그 방법 중 하나로 이미 사용된 게이트들을 그룹으로 만드는 방법이 있다.
 *
 */
class Main {
    static int[] parent;
    static int G;
    static int P;
    static int[][] relationship;
    static int[] commDepth;

    public static int find(int a)
    {
        if(a == parent[a])
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


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        initParent(G+1);
        int[] planes = new int[P];
        for(int i=0; i<P; i++) {
            planes[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for (int plane : planes) {
            int root = find(plane);
            if (root == 0) {
                break;
            }
            union(root - 1, plane);
            cnt += 1;
        }
        System.out.println(cnt);


        br.close();
    }

    private static void initParent(int n)
    {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }
}