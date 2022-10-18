// 1269번 대칭차집합 (S3) [해시]
/*
<문제 정보>
 1. 두 집합의 대칭차집합 구하기 (A-B) ∪ (B-A) = (A ∪ B) - (A ∩ B)

<변수 범위>
 1. 2초 / 256MB
 2. 각 집합의 원소는 200,000 이하
 3. 모든 원소의 값은 1억 이하 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Q1269 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // ******************** 입력 & 초기화 ********************

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<Integer> symmetricDifferenceSet = new HashSet<>();

        // ******************** 메인 로직 ********************

        st = new StringTokenizer(br.readLine());
        while (N-- >0) symmetricDifferenceSet.add(Integer.parseInt(st.nextToken()));

        // M의 있는 원소들을 N의 원소들이 있는 곳에 넣으면서 겹치면 그 원소를 빼고 겹치지 않으면 넣는다.
        st = new StringTokenizer(br.readLine());
        int num;
        while (M-- >0) {
            num = Integer.parseInt(st.nextToken());
            if (symmetricDifferenceSet.contains(num)) symmetricDifferenceSet.remove(num);
            else symmetricDifferenceSet.add(num);
        }

        // ******************** 정답 출력 ********************

        // symmetricDifferenceSet 의 size가 정답
        sb.append(symmetricDifferenceSet.size());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}














