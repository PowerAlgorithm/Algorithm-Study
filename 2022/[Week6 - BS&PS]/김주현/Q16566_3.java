// 16566번 카드게임 (P5) [이분탐색 & DSU]
/*
<문제 정보>
 1. K번 동안 민수가 내는 카드를 출력
    - 철수가 낼 카드보다 큰 카드중 가장 작은 카드

<변수 범위>
 1. 1.2초 / 512MB
 2. N개중 M개의 카드를 고름 1<=M<=N<=4,000,000
 3. 1<=K<=min(M,10000))

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q16566_3 {
    static int N,M,K;
    static int[] cards;
    static int[] parent;
    static boolean[] removedCards;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cards = new int[M];
        parent = new int[M];
        parentInitSet();
        removedCards = new boolean[M];

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) cards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cards);

        st = new StringTokenizer(br.readLine());
        int key;
        int firstCheckIdx;
        int secondCheckIdx;
        int myCard, myCardIdx;
        while(K-- >0) {
            key = Integer.parseInt(st.nextToken());
            firstCheckIdx = checkInCards(key);
//            System.out.printf("first : %d\n",firstCheckIdx);

            // 해당 카드가 제거되지 않았거나 제거된 카드가 없다면 이번에 이카드를 내고 삭제한다.
            if (!removedCards[firstCheckIdx]) {
                myCard = cards[firstCheckIdx];
                myCardIdx = firstCheckIdx;
            }

            // 해당 카드가 제거되었다면 제거된 카드리스트에 없는 수 중에서 해당 카드보다 높은 수 중 최솟값을 찾는다.
            else {
                secondCheckIdx = find(firstCheckIdx)+1;
                myCard = cards[secondCheckIdx];
                myCardIdx = secondCheckIdx;
//                union(firstCheckIdx, secondCheckIdx);
            }

            sb.append(myCard).append("\n");
            removedCards[myCardIdx] = true;
            if (myCardIdx!=0 && removedCards[myCardIdx-1]) union(myCardIdx-1, myCardIdx);
            if (myCardIdx!=M-1 && removedCards[myCardIdx+1]) union(myCardIdx+1, myCardIdx);


            /*for (int i=0;i<M;i++) System.out.printf("%d ",parent[i]);
            System.out.println();
            for (int i=0;i<M;i++) System.out.printf("%d ",removedCards[i] ? 1 : 0);
            System.out.println("\n");*/
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // lo와 hi는 모두 배열 인덱스
    public static int checkInCards(int key) {
        int lo = 0;
        int hi = M-1;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if(cards[mid]<=key) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }

    public static void parentInitSet() {
        for (int i=0;i<M;i++) parent[i] = i;
    }

    public static int find(int a) {
        if (parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }

    public static void union (int a, int b) {
        a = find(a);
        b = find(b);
        // 큰쪽이 부모가 되어야 함
        if (a==b) return;
        if(a>b) parent[b] = a;
        else parent[a] = b;
    }
}