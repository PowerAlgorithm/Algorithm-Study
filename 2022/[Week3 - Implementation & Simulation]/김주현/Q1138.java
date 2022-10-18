// 1138번 한줄로 서기 (S2) [구현]
/*
<문제 정보>
 1. 사람들이 줄을 어떻게 서야 하는지
    - 자기 왼쪽에 자신보다 키큰 사람이 몇명 있었는지 기억

<변수 범위>
 1. 2초 / 128MB
 2. 사람의 수 N 1<=N<=10

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1138 {
    static int N;
    static int[] arr;
    static int[] line;
    static boolean[] ordered;
    static boolean findAns = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        line = new int[N+1];
        ordered = new boolean[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());
        dfs(0);
        for (int i=1;i<=N;i++) sb.append(line[i]).append(" ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int orderedPeople) {
        if (orderedPeople == N) {
            findAns = true;
            return;
        }
        int cnt;
        // 이번에 서야할 줄은 orderedPeople+1 번째
        for (int person=1;person<=N;person++) {
            if (findAns) return; // 줄 다 세웠으면 더 이상 dfs 진행할 필요 없음
            if (ordered[person]) continue; // 이미 줄을 서있는 사람이라면 다음 사람 조사
            cnt = 0;
            for (int j=orderedPeople;j>0;j--) {
                if (line[j] > person)  cnt++; // 왼쪽에 있는 자신보다 키큰 사람 수 세기
            }
            if(cnt>arr[person]) continue; // 왼쪽에 있어야 하는 수보다 크다면 다음 사람(person)을 조사
            if (cnt == arr[person]) { // 왼쪽에 있어야 하는 사람 수와 같다면 배치하고 다름 줄칸 조사
                line[orderedPeople+1] = person;
                ordered[person] = true;
                dfs(orderedPeople+1);
                ordered[person] = false;
            }
        }
    }
}



















