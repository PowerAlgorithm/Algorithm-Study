// 1802번 종이 접기 (S2) [구현] [분할정복 (풀이아직)]
/*
<문제 정보>
 1. 종이가 접혀 있는 정보가 주어질 때 규칙 대로 접을 수 있는지 YES or NO 출력
    - 규칙 : 반으로 접을 때 시계방향 or 반시계방향으로만 접을 수 있음
    - OUT : 1, IN : 0

<변수 범위>
 1. 2초 / 128MB
 2. 테스트 케이스 T 1000이하의 자연수
 3. 문자열의 길이는 3000보다 작으며 항상 2^N-1 꼴

<프로그램 진행>
 1.

<필요 함수>
 1.

 */



import java.io.*;

public class Q1802 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- >0) {
            char[] origami = br.readLine().toCharArray();
            sb.append(isPossible(origami) ? "YES" : "NO").append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isPossible (char[] origami) {
        int length = origami.length;
        int startIdx=0;
        int gap=2;
        int value;
        boolean possible = true;
        while(startIdx<length) {
            if(!possible) break;
            value = origami[startIdx];
            // 아래의 length는 바뀌면 안되는 변수인데 length/=2 를 넣어주니 당연히 틀림..
            // 변수를 쓸때 이게 진행과정에서 바뀌어도 되는 변수인지 아닌지 꼭 생각하자!
            for (int i=startIdx+gap; i<length; i+=gap) {
                if (value==origami[i]) {
                    possible = false;
                    break;
                }
                value = origami[i];
            }
            startIdx+=gap/2;
            gap*=2;
        }
        return possible;
    }
}










