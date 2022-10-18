// 14425번 문자열 집합 (S3) [HashSet]
/*
<문제 정보>
 1. N개의 문자열로 이루어진 집합 S가 있을 때 입력으로 주어지는 문자열 중 S에 포함되는 것이 몇개인지 출력
    - 집합 S에 같은 문자열이 주어지는 경우는 없음

<변수 범위>
 1. 2초 / 1536MB
 2. 1<=N,M<=10,000
 3. 주어지는 문자열은 알파벳 소문자로만 이루어져 있고 길이는 500을 넘지 않는다.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Q14425 {
    static int N, M;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // ******************** 입력 & 초기화 ********************

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashSet<String> strSet = new HashSet<>();
        for (int i=0;i<N;i++) strSet.add(br.readLine());

        // ******************** 메인 로직 ********************

        int count = 0;
        while(M-- >0) {
            if(strSet.contains(br.readLine())) count++;
        }

        // ******************** 정답 출력 ********************

        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}













