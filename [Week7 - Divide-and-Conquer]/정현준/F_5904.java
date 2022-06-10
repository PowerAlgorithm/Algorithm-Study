import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        // 입력한 n 이 몇 번째 수열에 포함되는지?
        // totLength = 수열 전체 길이, midLength = o가 k+2개인 부분 길이
        // 초기 상태 : moo
        int totLength = 3;
        int midLength = 3;

        // n이 현재 수열의 길이보다 크면 수열 계속 늘려주기
        while (n > totLength) {
            totLength = totLength * 2 + ++midLength;
        }

        while (true) {
            // t 는 이전 문자열의 길이
            int t = (totLength - midLength) / 2;

            // n이 첫 번째 부분이면
            // o가 k+2개인 부분의 길이를 1 줄여주고, 전체 길이를 이전 문자열의 길이로
            if (n <= t) {
                midLength--;
                totLength = t;
            }
            // n이 세 번째 부분이면
            // 이전 문자열의 길이 만큼 줄여주며 사라진 만큼 n도 줄여준다
            // o가 k+2개인 부분의 길이를 1 줄여주고, 전체 길이를 이전 문자열의 길이로
            else if (n > t + midLength) {
                n -= t + midLength;
                midLength--;
                totLength = t;
            }
            // n이 중간 부분이면
            // n에서 이전 문자열의 크기만 빼준다.
            else {
                n -= t;
                break;
            }
        }
        bw.append(n == 1 ? "m" : "o");
        bw.flush();
        bw.close();
        br.close();
    }
}