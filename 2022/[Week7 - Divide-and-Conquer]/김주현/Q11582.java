// 11582번 치킨 TOP N (S4) [정렬 - MergeSort][분할정복]
/*
<문제 정보>
 1. mergeSort의 진행 과정에서 k번의 정렬이 일어나는 단계 직후의 결과를 출력

<변수 범위>
 1. 5초 / 256MB
 2. 4<=N<=2^20, N은 항상 2의 거듭제곱
 3. 각각의 수 : 10억 이하의 자연수 또는 0
 4. 1<=k<N, k는 항상 2의 거듭제곱

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q11582 {
    static int N, k;
    static int[] arr;
    static int goalDepth;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        goalDepth = (int) Math.round(Math.log(k)/Math.log(2));
//        System.out.println(k);
//        System.out.println(Math.log(k));
//        System.out.println(Math.log(k)/Math.log(2));
//        System.out.printf("maxD : %d, goalD : %d\n",maxDepth, goalDepth);

        mergeSort(0,N-1,0);
        for (int num : arr) sb.append(num).append(" ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void mergeSort(int start, int end, int depth) {
        if (start+1==end) {
            if (arr[start]>arr[end]) swap(start,end);
            return;
        }

        int mid = (end+start-1)/2;
//        System.out.printf("start : %d, end : %d, mid : %d, depth : %d\n",start, end, mid, depth);
        mergeSort(start,mid,depth+1);
        mergeSort(mid+1,end,depth+1);
        if (depth<goalDepth) return;
        // 이 partition은 depth가 현재 상태의 depth가 되기 위해 합치는 것
        partition(start,mid,end);
    }

    public static void partition (int start, int mid, int end) {
        int length = end-start+1;
        int[] temp = new int[length];
        int idx = 0;
        int left = start;
        int right = mid+1;
        for (int i=0;i<length;i++) {
            if (arr[left]<=arr[right]) temp[idx++] = arr[left++];
            else temp[idx++] = arr[right++];
            if (left>mid || right>end) break;
        }
        if (left<=mid) {
            for (int i=left; i<=mid; i++) temp[idx++] = arr[i];
        }
        else {
            for (int i=right; i<=end; i++) temp[idx++] = arr[i];
        }
        System.arraycopy(temp,0,arr,start,length);
    }

    public static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
