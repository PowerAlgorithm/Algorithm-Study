// 10830번 행렬 제곱 (G4) [분할정복][수학]
/*
<문제 정보>
 1. 크기가 N*N인 행렬 A의 B제곱을 출력
    - 각 원소를 1000으로 나눈 나머지를 출력

<변수 범위>
 1. 1초 / 256MB
 2. 2<=N<=5
 3. 1<=B<=100,000,000,000
 4. 행렬의 각 원소는 1,000 이하의 자연수 또는 0

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// 중간 과정에서 overflow 가능성 체크합시다
// 엣지케이스 진짜... 사고과정 -> div 처리가 안되는 상황이 있다면 B=1 일때, 모든 가능성을 생각 -> 아 1000이면 처음에 div처리 안해줬다면 틀림

import java.io.*;
import java.util.StringTokenizer;

public class Q10830_studySolve {
    static int N;
    static long B;
    static int[][] arr;

    static final int div = 1000;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) arr[i][j] = Integer.parseInt(st.nextToken())%1000;
        }
        int[][] result = expArr(arr, B);
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) sb.append(result[i][j]).append(" ");
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] expArr(int[][] arr, long exp) {
        if (exp==1) return arr;

        if (exp%2==0) return expArr(squareArr(arr), exp/2);
        else return mulArr(arr, expArr(arr,exp-1));
    }

    public static int[][] mulArr(int[][] arr1, int[][] arr2) {
        int[][] temp = new int[N][N];
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                for (int k=0;k<N;k++) {
                    temp[i][j] += arr1[i][k]*arr2[k][j];
                }
                temp[i][j]%=div;
            }
        }
        return temp;
    }

    public static int[][] squareArr(int[][] arr) {
        return mulArr(arr, arr);
    }
}


























