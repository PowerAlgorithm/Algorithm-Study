// 2668번 숫자 고르기 (G5) [그리디]
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.*;

public class Q2668 {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        for (int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // ******************** 메인 로직 ********************
        visited = new boolean[N+1];
        for (int i=1; i<=N; i++) {
            if (!visited[i]) {
                search(i);
            }
        }

        // ******************** 정답 출력 ********************
        sb.append(answer).append("\n");
        for (int i=1; i<=N; i++) {
            if (visited[i]) sb.append(i).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search (int n) {
        int start = n;
        int end = 0;
        Set<Integer> visitList = new HashSet<>();
        boolean isOriginalPoint = false;

        while ( !isOriginalPoint ) {
            end = arr[start];
            if ( visited[end] || visitList.contains(end) ) break;
            visitList.add(end);
            if (end == n) {
                isOriginalPoint = true;
            } else if ( start == end) {
                break;
            } else {
                start = end;
            }
        }

        if (isOriginalPoint) {
            answer += visitList.size();
            for (int point : visitList) {
                visited[point] = true;
            }
        }
    }
}
