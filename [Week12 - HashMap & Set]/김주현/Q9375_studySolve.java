// 9375번 패션왕 신해빈 (S3) [해시]
/*
<문제 정보>
 1. 옷의 조합 수를 출력
 2.

<변수 범위>
 1. 1초 / 128MB
 2. 테스트 케이스 최대 100
 3. 의상 수 N 0<=N<=30
 4. 문자열 1이상 20이하 소문자
 5. 같은 의상 존재하지 않음

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Q9375_studySolve {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        int N;
        while(T-- >0) {

        // ******************** 입력 & 초기화 ********************
            N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> closet = new HashMap<>();

        // ******************** 메인 로직 ********************

            String typeOfClothes;
            while(N-- >0) {
                st = new StringTokenizer(br.readLine());
                st.nextToken(); // 같은 이름의 의상은 존재하지 않으므로 의상 이름은 버린다.
                typeOfClothes = st.nextToken();
                if (!closet.containsKey(typeOfClothes)) closet.put(typeOfClothes, 1);
                else closet.replace(typeOfClothes, closet.get(typeOfClothes)+1);
            }

            int numOfCombination = 1;
            for (int value : closet.values()) numOfCombination *= value+1;
            numOfCombination--; // 아무것도 입지 않았을 경우 1가지 뺴주기

        // ******************** 정답 출력 ********************
            sb.append(numOfCombination).append("\n");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
