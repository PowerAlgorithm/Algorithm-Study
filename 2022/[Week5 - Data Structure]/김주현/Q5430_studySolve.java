// 5430번 AC (G5) [덱, 덱구현]
/*
<문제 정보>
 1. 배열 연산 2가지 구현
    - R : 뒤집기
    - D : 버리기
    - 에러가 발생할 경우 error 출력

<변수 범위>
 1. 1초 / 256MB
 2. 테스트케이스 T 최대 100개
 3. 수행함수 p 길이 1이상 100,000 이하
 4. 배열 길이 0<=n<=100,000
 5. 배열의 정수 1이상 100 이하
 6. 전체 테스트케이스 p와 n의 합은 70만을 넘지 않는다.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q5430_studySolve {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        String S;
        int N;
        Deque<Integer> arr = new ArrayDeque<>();
        while(T-- >0) {
            S = br.readLine();
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(),"[,]");
            for (int i=0;i<N;i++) arr.add(Integer.parseInt(st.nextToken()));
            boolean fromFirst = true;
            boolean error = false;
            for(char operation : S.toCharArray()) {
                if (operation == 'R') fromFirst=!fromFirst;
                else {
                    if (arr.size()==0) {
                        error = true;
                        break;
                    }
                    if (fromFirst) arr.poll();
                    else arr.pollLast();
                }
            }
            if (error) sb.append("error").append("\n");
            else {
                int num;
                boolean isArrEmpty = arr.isEmpty();
                sb.append("[");
                while(!arr.isEmpty()) {
                    if (fromFirst) num = arr.poll();
                    else num = arr.pollLast();
                    sb.append(num).append(",");
                }
                if (!isArrEmpty) sb.deleteCharAt(sb.length()-1);
                sb.append("]").append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
