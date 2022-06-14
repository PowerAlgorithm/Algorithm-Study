// 10800번 컬러볼 (G3)
/*
<문제 정보>
 1. 자신의 공과 다른색이면서 더 작은 크기만 잡을 수 있음
 2. 각 플레이어가 잡을 수 있는 모든 공들의 크기의 합 출력

<변수 범위>
 1. 1초 / 256MB
 2. 1<=N<=200,000
 3. 공의 색 1<=C_i<=N
 4. 크기 1<=S_i<=2,000


<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// 시간복잡도 N^2 불가능
// 색별로 정렬해서 누적합

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Q10800 {
    static int N;
    static int[] ballsBySize = new int[2001];
    static int[] ballsSizeSum = new int[2001];
    static ArrayList<Integer>[] ballsByColor;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        ColorBall[] balls = new ColorBall[N+1];
        ballsByColor = new ArrayList[N+1];
        for (int i=1;i<=N;i++) ballsByColor[i] = new ArrayList<>();
        int color,size;
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            color = Integer.parseInt(st.nextToken());
            size = Integer.parseInt(st.nextToken());
            balls[i] = new ColorBall(color, size);
            ballsBySize[size] += 1;
            ballsByColor[color].add(size);
        }
        for (int i=1;i<=2000;i++) ballsSizeSum[i] = ballsSizeSum[i-1] + (i*ballsBySize[i]);
        for (int i=1;i<=N;i++) {
            if (ballsByColor[i].isEmpty()) continue;
            Collections.sort(ballsByColor[i]);
            /*System.out.printf("[color : %d]\n",i);
            System.out.printf("%d ",ballsByColor[i].get(0));*/
            for (int j=1;j<ballsByColor[i].size();j++) {
                ballsByColor[i].set(j,ballsByColor[i].get(j-1)+ballsByColor[i].get(j));
//                System.out.printf("%d ",ballsByColor[i].get(j));
            }
//            System.out.println();
        }
        int allColor, sameColor, idx;
        for (int i=1;i<=N;i++) {
            allColor = ballsSizeSum[balls[i].size-1];
//            System.out.printf("allColor : %d\n",allColor);
            if(allColor==0) {
                sb.append(0).append("\n");
                continue;
            }
            idx = BS_lowerBound(balls[i].size, balls[i].color);
            sameColor = idx==0 ? 0 : ballsByColor[balls[i].color].get(idx-1);
//            System.out.printf("[%d번째] sameColor : %d\n",i,sameColor);
            sb.append(allColor-sameColor).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BS_lowerBound(int key, int color) {
        int lo = 0;
        int hi = ballsByColor[color].size()-1;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if(getNthOfColor(color,mid)<key) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }

    public static int getNthOfColor (int color, int n) {
        if (n==0) return ballsByColor[color].get(n);
        return ballsByColor[color].get(n)- ballsByColor[color].get(n-1);
    }
}

class ColorBall {
    int color, size;

    public ColorBall(int color, int size) {
        this.color = color;
        this.size = size;
    }
}


















