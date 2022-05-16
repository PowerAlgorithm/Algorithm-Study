// 14226번 이모티콘 (G5) [dp + bfs]
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q14226_2 {
    static int N;
    static boolean[][] visited;

    static final int COPY = 0;
    static final int PASTE = 1;
    static final int DELETE = 2;
    static final int EMPTY = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        visited = new boolean[2*N+1][N+1];

        if (N==2) {
            System.out.println(2);
            return;
        }

        Deque<Pair14> q = new ArrayDeque<>();
        q.add(new Pair14(2, 1));
        Pair14 now;
        int nextN = 0;
        int nextClip = 0;
        int time = 2;
        int qSize;
        boolean isIgnored;
        boolean findAns = false;

        while (!q.isEmpty()) {
            if (findAns) break;
            qSize = q.size();

            for (int i=0;i<qSize;i++) {
                if (findAns) break;
                now = q.poll();

                for (int operation=0;operation<3;operation++) {
                    isIgnored = true;
                    switch (operation) {
                        case COPY :
                            if (now.n != now.clip && now.n < N && !visited[now.n][now.n]) {
                                nextN = now.n;
                                nextClip = now.n;
                                isIgnored = false;
                            }
                            break;
                        case PASTE :
                            if (now.clip != EMPTY && now.n < N && !visited[now.n + now.clip][now.clip]) {
                                nextN = now.n+now.clip;
                                nextClip = now.clip;
                                isIgnored = false;
                            }
                            break;
                        case DELETE :
                            if (now.n != 1 && !visited[now.n-1][now.clip]) {
                                nextN = now.n -1;
                                nextClip = now.clip;
                                isIgnored = false;
                            }
                    }
                    if (isIgnored) continue;
                    if (nextN == N) {
                        findAns = true;
                        break;
                    }
                    q.add(new Pair14(nextN, nextClip));
                    visited[nextN][nextClip] = true;
                }
            }
            time++;
        }

        sb.append(time);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Pair14 {
    int n, clip;

    public Pair14 (int n, int clip) {
        this.n = n;
        this.clip = clip;
    }
}
















