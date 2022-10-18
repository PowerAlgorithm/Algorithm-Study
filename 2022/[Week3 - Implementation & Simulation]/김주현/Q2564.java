// 2564번 경비원 (S1)
/*
<문제 정보>
 1. 동근이의 위치로부터 각 상점까지의 최단거리의 합
    - 길은 내부가 아닌 가장자리로만 다닐 수 있다.

<변수 범위>
 1. 1초 / 128MB
 2. 블럭의 가로, 세로, 상점의 개수 모두 100 이하의 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// 쭉 펴서 1자로 생각한다면 구현이 훨씬 간단!

import java.io.*;
import java.util.StringTokenizer;

public class Q2564 {
    static int width, height, total;
    static Pair11 security;
    static int startDirection=0;
    static int[] clockwise = {1, 4, 2, 3};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        total = (width+height)*2;
        int storeNum = Integer.parseInt(br.readLine());
        Pair11[] stores = new Pair11[storeNum];
        int dir, dis;
        for (int i=0;i<=storeNum;i++) {
            st = new StringTokenizer(br.readLine());
            dir = Integer.parseInt(st.nextToken());
            dis = Integer.parseInt(st.nextToken());
            if (dir==2) dis = width - dis;
            if (dir==3) dis = height - dis; // 시계방향으로 통일
            if(i!=storeNum) stores[i] = new Pair11(dir, dis);
            else security = new Pair11(dir, dis);
        }
        for (int i=0;i<4;i++) {
            if (clockwise[i] == security.direction) startDirection = (i+1)%4;
        }

        /*System.out.printf("Total Dist : %d\n",total);
        System.out.printf("start direction : %d\n",startDirection);
        System.out.printf("경비의 방향 : %d, 거리 : %d\n",security.direction, security.dist);*/


        int totalDist=0;
        for (int i=0;i<storeNum;i++) {
            /*int minDist=getMinDist(stores[i]);
            System.out.printf("%d 상점까지 최단거리 : %d\n\n",i+1,minDist);
            totalDist+=minDist;*/
            totalDist+=getMinDist(stores[i]);
        }

        sb.append(totalDist);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getMinDist(Pair11 store) {
//        System.out.printf("이번 상점의 방향 : %d, 거리 : %d\n",store.direction, store.dist);
        int dist;
        if (security.direction == store.direction) return Math.abs(security.dist-store.dist);

        dist = (security.direction==1 || security.direction==2) ? width-security.dist : height-security.dist;
//        System.out.printf("처음 거리 : %d\n",dist);
        // 경비 출발 경로를 미리 넣는다.

        int currentDirection;
        for (int i=0;i<3;i++) {
            currentDirection = clockwise[(startDirection + i)%4];
//            System.out.printf("currentDirection : %d\n",currentDirection);
            if (currentDirection == store.direction) {
                dist+=store.dist;
                break;
            }
            else dist+=(currentDirection==1 || currentDirection==2) ? width : height;
//            System.out.printf("%d 방향에서 dis : %d\n",currentDirection,dist);
        }
        return Math.min(dist,total-dist);
    }
}

class Pair11 {
    int direction;
    int dist;

    public Pair11 (int direction, int dist) {
        this.direction = direction;
        this.dist = dist;
    }
}














