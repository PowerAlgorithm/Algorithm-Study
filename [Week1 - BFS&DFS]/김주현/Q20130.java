// 20130번 Metroidvania Extreme (G1) [bfs&dfs][구현]
/*
<문제 정보>
 1. 시작지점에서 목표지점까지 거친 좌표들을 순서대로 출력
    - key와 door 이 있음
    - 가장 바깥은 전부 벽

<변수 범위>
 1. 1초 / 1024MB
 2. 3<=N,M<=200

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Q20130 {
    static int height,width;
    static boolean[] keys = new boolean[26];
    static ArrayList<Pair8>[] doors = new ArrayList[26];
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static StringBuilder sb = new StringBuilder();
    static int history=0;
    static final int WALL = '#';
    static final int PATH = '*';
    static final int START = '@';
    static final int GOAL = '!';

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        map = new int[height+1][width+1];
        visited = new boolean[height+1][width+1];
        for (int i=0;i<26;i++) doors[i] = new ArrayList<>();
        Pair8 start = new Pair8(0,0);
        for (int i=1;i<=height;i++) {
            int j=1;
            for (char c : br.readLine().toCharArray()) {
                if (c == '@') start = new Pair8(i,j);
                map[i][j++] = c;
            }
        }

        /*for (int i=1;i<=height;i++) {
            for (int j=1;j<=width;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }*/
        bfs(start);

        int offset = String.valueOf(history).length();
        sb.insert(0,history).insert(offset,"\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isDoor (int x, int y) { return map[x][y]>='A' && map[x][y]<='Z'; }
    public static boolean isKey (int x, int y) { return map[x][y]>='a' && map[x][y]<='z'; }
    public static boolean hasKeyForDoor (int door) { return keys[door-'A']; }

    public static void bfs(Pair8 start) {
        Deque<Pair8> q = new ArrayDeque<>();
        q.add(start);
        visited[start.x][start.y] = true;
        Pair8 now;
        int nextX, nextY;
        int obj_now;
        int obj_next;
        boolean inRange;
        boolean findGoal = false;

        while(!q.isEmpty()) {
            if (findGoal) break;
            now = q.poll();

            obj_now = map[now.x][now.y];
            // 1. 문을 만났을 때
            if (isDoor(now.x, now.y) && !hasKeyForDoor(obj_now)) {
                doors[obj_now-'A'].add(new Pair8(now.x, now.y));
                continue;
            }
            // 2. 키를 만났을 때
            if (isKey(now.x, now.y)) {
                keys[obj_now-'a'] = true;
                for (Pair8 door : doors[obj_now-'a']) {
                    visited[door.x][door.y] = true;
                    q.add(new Pair8(door.x, door.y));
                }
                doors[obj_now-'a'].clear();
            }
            sb.append(now.x).append(" ").append(now.y).append("\n");
            history++;

            for (int[] d : dir) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX>1 && nextY>1 && nextX<height && nextY<width;
                if(inRange && map[nextX][nextY]!=WALL && !visited[nextX][nextY]) {
                    obj_next = map[nextX][nextY];

                    // 도착 지점 도달
                    if (obj_next == GOAL) {
                        findGoal = true;
                        sb.append(nextX).append(" ").append(nextY);
                        history++;
                        break;
                    }
                    // 공통
                    visited[nextX][nextY] = true;
                    q.add(new Pair8(nextX, nextY));
                }
            }
        }
    }
}

class Pair8 {
    int x, y;

    public Pair8 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}


























