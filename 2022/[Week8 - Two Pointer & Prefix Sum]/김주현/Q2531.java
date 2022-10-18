// 2531번 회전 초밥 (S1) [해시맵][구현][투포인터(아직)]
/*
<문제 정보>
 1. 회전 초밥 벨트 상태, 메뉴 초밥 가짓수, 연속 접시, 쿠폰 번호가 있을 때 손님이 먹을 수 있는 초밥 종류 최댓값 출력

<변수 범위>
 1. 1초 / 256MB
 2. 벨트 위의 접시 수 2<=N<=30,000
 3. 초밥 가짓수 2<=d<=3,000
 4. 연속 접시 2<=k<=3,000 (k<=N)
 4. 쿠폰 1<=c<=d

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Q2531 {
    static int N,d,k,c;
    static int[] sushi;
    static HashMap<Integer, Integer> mySushi = new HashMap<>();
    static int maxNumOfType = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushi = new int[N+k-1];
        for (int i=0;i<N;i++) sushi[i] = Integer.parseInt(br.readLine());
        for (int i=N;i<sushi.length;i++) sushi[i] = sushi[i-N];
        addSushi(c);
        for (int i=0;i<k;i++) addSushi(sushi[i]);
//        printMap();
        maxNumOfType = mySushi.size();
        getMaxType();
        sb.append(maxNumOfType);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void addSushi(int sushi) {
        if (mySushi.containsKey(sushi)) mySushi.replace(sushi, mySushi.get(sushi)+1);
        else {
            mySushi.put(sushi,1);
//            System.out.println("new sushi");
        }
    }

    public static void removeSushi(int sushi) {
        if (mySushi.get(sushi)>1) mySushi.replace(sushi, mySushi.get(sushi)-1);
        else {
            mySushi.remove(sushi);
//            System.out.println("sushi out");
        }
    }

    public static void getMaxType() {
        for (int i=k;i<sushi.length;i++) {
//            System.out.printf("removed sushi : %d\n",sushi[i-k]);
//            System.out.printf("this sushi : %d\n",sushi[i]);
            removeSushi(sushi[i-k]);
            addSushi(sushi[i]);
            maxNumOfType = Math.max(maxNumOfType, mySushi.size());
//            printMap();
        }
    }

    public static void printMap() {
        System.out.println("+ + + + +");
        mySushi.forEach((key, value) -> {
            System.out.printf("sushi : %d, dishes : %d\n", key, value);
        });
        System.out.println("-------------------------------");
    }
}
















