import java.io.*;
import java.util.*;

class Emoticon {
    int emoCount;
    int time;
    int clipboard;

    public Emoticon(int emoCount, int time, int clipboard) {
        this.emoCount = emoCount;
        this.time = time;
        this.clipboard = clipboard;

    }

    @Override
    public String toString() {
        return "Emoticon [emoCount=" + emoCount + ", time=" + time + ", clipboard=" + clipboard + "]";
    }

}

class Main {
    static boolean[][] checked = new boolean[2001][2001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int target = Integer.parseInt(br.readLine());

        bw.append(String.valueOf(solve(target)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int solve(int target) {
        Queue<Emoticon> queue = new LinkedList<Emoticon>();
        // 시작은 이모티콘 1개
        queue.offer(new Emoticon(1, 0, 0));
        while (!queue.isEmpty()) {
            Emoticon now = queue.poll();
            // 현재 이모티콘 객체의 이모티콘 개수가 목표와 같다면
            if (now.emoCount == target)
                return now.time;
            Emoticon after;
            if (now.emoCount >= 0 && now.emoCount < 1001) {
                if (!checked[now.emoCount][now.emoCount]) {
                    after = new Emoticon(now.emoCount, now.time + 1, now.emoCount);
                    queue.offer(after);
                    checked[now.emoCount][now.emoCount] = true;
                }
                if (!checked[now.emoCount + now.clipboard][now.clipboard]) {
                    after = new Emoticon(now.emoCount + now.clipboard, now.time + 1, now.clipboard);
                    queue.offer(after);
                    checked[now.emoCount + now.clipboard][now.clipboard] = true;
                }
                if (now.emoCount - 1 >= 1 && !checked[now.emoCount - 1][now.clipboard]) {
                    after = new Emoticon(now.emoCount - 1, now.time + 1, now.clipboard);
                    queue.offer(after);
                    checked[now.emoCount - 1][now.clipboard] = true;
                }
            }
        }
        return 0;
    }
}