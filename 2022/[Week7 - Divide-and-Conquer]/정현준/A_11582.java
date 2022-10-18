import java.io.*;
import java.util.StringTokenizer;

class Main {
    static int[] arr, result;
    static int size, target;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {

        size = Integer.parseInt(br.readLine());
        arr = new int[size];
        result = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        target = Integer.parseInt(br.readLine());

        mergeSort(0, size - 1);

        for (int value : result) {
            sb.append(value).append(" ");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    public static void merge(int left, int mid, int right) {
        // 인원을 만족하는 순간 return
        // right - left : 현재 정렬중인 리스트의 크기
        // size / target : 전체 배열 크기에서 입력받은 인원 수를 나눈 것
        // 4명이 정렬중이라고 하면 인원 당 정렬중인 배열의 크기는 8 / 4인 2이기 때문
        if (right - left > size / target) {
            return;
        }
        int i = left;
        int j = mid + 1;
        int index = left;
        // 작은 순서대로 배열에 삽입
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                result[index++] = arr[i++];
            } else {
                result[index++] = arr[j++];
            }
        }

        // 남은 데이터 삽입
        while (i <= mid)
            result[index++] = arr[i++];
        while (j <= right)
            result[index++] = arr[j++];

        // 정렬된 배열을 삽입
        for (int q = left; q <= right; q++) {
            arr[q] = result[q];
        }
    }

    public static void mergeSort(int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // System.out.println(left + " " + mid + " " + right);
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }
}