// 11478번 서로 다른 부분 문자열의 개수 (S3) [Hash]
/*
<문제 정보>
 1. 문자열 S가 주어졌을 때 S의 서로다른 부분 문자열 개수

<변수 범위>
 1. 1초 / 512MB
 2. S길이 1,000 이하

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.HashSet;

public class Q11478 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // ******************** 입력 & 초기화 ********************
        String S = br.readLine();
        int len = S.length();

        // ******************** 메인 로직 ********************

        HashSet<String> subStrings = new HashSet<>();
        for (int i=0;i<len;i++) {
            for (int j=i+1;j<=len;j++) subStrings.add(S.substring(i,j));
        }

        // ******************** 정답 출력 ********************

        sb.append(subStrings.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
