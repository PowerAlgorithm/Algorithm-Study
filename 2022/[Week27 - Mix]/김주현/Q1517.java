// 1517번 버블 소트 (P5) [정렬]
/*
<문제 정보>
 1. N개의 수로 이루어진 수열에 대해 버블 소트를 수행할 때 swap 이 몇번 발생하는지 출력

<변수 범위>
 1. 1초 / 512MB
 2. 1 <= N <= 500,000
 3. 수열 안의 각 수의 절댓값 1,000,000,000 이하의 정수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1517 {
    static int N;
    static int[] arr;
    static long answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ******************** 메인 로직 ********************

        mergeSort(0, N-1);

        // ******************** 정답 출력 ********************
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void mergeSort (int start, int end) {
        if (start == end) return;

        int mid = (start + end) / 2;
        mergeSort(start, mid);
        mergeSort(mid+1, end);
        merge(start, mid, end);
    }

    public static void merge(int start, int mid, int end) {
        int left = start;
        int right = mid+1;
        int[] temp = new int[end - start + 1];
        int idx = 0;

        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp[idx] = arr[left];
                left++;
            }
            else { // arr[left] > arr[right]
                temp[idx] = arr[right];
                right++;
                answer += (mid - left + 1);
            }
            idx++;
        }

        while (left <= mid) {
            temp[idx++] = arr[left];
            left++;
        }

        while (right <= end) {
            temp[idx++] = arr[right];
            right++;
        }

        System.arraycopy(temp, 0, arr, start, end - start + 1);
    }
}
