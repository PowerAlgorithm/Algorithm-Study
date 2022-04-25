// 9328번 열쇠 (G1) [bfs&dfs][구현]
/*
<문제 정보>
 1. 각 테스트 케이스마다 훔칠 수 있는 문서의 최대 개수 출력
    - 열쇠와 문이 맞으면 여러번 사용하고 열 수 있음
        또한 해당 열쇠가 여러개 있을 수 있음

<변수 범위>
 1. 1초 / 256MB
 2. 지도의 너비와 높이 2<=h,w<=100
 3. 테스트 케이스 100개 이하


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

public class Q9328 {
    static int documents;
    static int height,width;
    static boolean[] keys;
    static ArrayList<Pair7>[] doors = new ArrayList[26];
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static final int WALL = '*';
    static final int PATH = '.';
    static final int DOC = '$';


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 아스키코드 정보
        System.out.printf("벽 * : %d\n",(int)'*');
        System.out.printf("길 . : %d\n",(int)'.');
        System.out.printf("문서 $ : %d\n",(int)'$');
         */

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while(T-- >0) {
            st = new StringTokenizer(br.readLine());
            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            map = new int[height+1][width+1];
            visited = new boolean[height+1][width+1];
            keys = new boolean[26];
            documents=0;
            for (int i=0;i<26;i++) doors[i] = new ArrayList<>();
            // 맵 정보
            for (int i=1;i<=height;i++) {
                int j=1;
                for (char c : br.readLine().toCharArray()) map[i][j++] = (int) c;
            }
            // 가지고 있는 키 정보
            for (char key : br.readLine().toCharArray()) {
                if (key=='0') break;
                keys[key-'a'] = true;
            }
            
            /* 입력 확인
            for (int i=1;i<=height;i++) {
                for (int j=1;j<=width;j++) System.out.printf("%d ",map[i][j]);
                System.out.println();
            }
            System.out.println(hasKey('b'));
             */
            bfs();
            sb.append(documents).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static boolean isDoor (int x, int y) {
        return map[x][y]>='A' && map[x][y]<='Z';
    }
    public static boolean isKey (int x, int y) {
        return map[x][y]>='a' && map[x][y]<='z';
    }
    public static boolean hasKey (int key) {
        return keys[key-'a'];
    }
    public static int getKey (int door) {
        return door+32;
    }

    public static void getStartPoint (Deque<Pair7> q) {
        for (int i=1;i<=height;i++) {
            for (int j=1;j<=width;j++) {
                if (i!=1 && j!=1 && i!=height && j!=width) continue;
                if (map[i][j]!=WALL) {
                    // 1. 문이라면
                    if (isDoor(i, j) && !hasKey(getKey(map[i][j]))) {
                        doors[map[i][j]-'A'].add(new Pair7(i, j));
                        continue;
                    }
                    // 2. 키라면
                    else if (isKey(i, j)) {
                        keys[map[i][j]-'a'] = true;
                        // 얻은 키로 열 수 있는 문들 모두 Queue에 추가
                        for (Pair7 dr : doors[map[i][j]-'a']) {
                            visited[dr.x][dr.y] = true;
                            q.add(new Pair7(dr.x, dr.y));
                        }
                        doors[map[i][j]-'a'].clear();
                    }
                    // 3. 문서라면
                    else if (map[i][j] == DOC) documents++;
                    // 공통
                    visited[i][j] = true;
                    q.add(new Pair7(i, j));
                }
            }
        }
    }

    public static void bfs() {
        Deque<Pair7> q = new ArrayDeque<>();
        getStartPoint(q);
        Pair7 now;
        int nextX, nextY;
        int door;
        boolean inRange;

        while(!q.isEmpty()) {
            now = q.poll();

            for (int[] d : dir) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX>=1 && nextY>=1 && nextX<=height && nextY<=width;
                if(inRange && map[nextX][nextY]!=WALL && !visited[nextX][nextY]) {
                    // 1. 문을 만났을 때
                    if (isDoor(nextX, nextY)) {
                        door = map[nextX][nextY];
                        if(!hasKey(getKey(door))) {
                            doors[door-'A'].add(new Pair7(nextX, nextY));
                            continue;
                        }
                    }
                    // 2. 키를 만났을 때
                    else if (isKey(nextX, nextY)) {
                        // 얻은 키를 저장
                        keys[map[nextX][nextY]-'a'] = true;
                        // 얻은 키로 열 수 있는 문들 모두 Queue에 추가
                        for (Pair7 dr : doors[map[nextX][nextY]-'a']) {
                            visited[dr.x][dr.y] = true;
                            q.add(new Pair7(dr.x, dr.y));
                        }
                        doors[map[nextX][nextY]-'a'].clear();

                    }
                    // 3. 문서를 만났을 때
                    else if (map[nextX][nextY] == DOC) documents++;
                    // 공통
                    visited[nextX][nextY] = true;
                    q.add(new Pair7(nextX, nextY));
                }
            }
        }
    }
}

class Pair7 {
    int x, y;

    public Pair7 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}















