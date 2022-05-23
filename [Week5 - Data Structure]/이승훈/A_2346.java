import java.util.*;
import java.io.*;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Balloon {
    int val;
    int idx;

    Balloon(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }

    @Override
    public String toString() {
        return val + ", " + idx;
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Balloon> balloons = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            balloons.add(new Balloon(
                    Integer.parseInt(st.nextToken()), i));
        }
        while(!balloons.isEmpty()) {
            Balloon b = balloons.poll();
            System.out.print(b.idx+1 + " ");
            if (balloons.isEmpty()) break;
            if(b.val>0) {
                for(int i=0; i<b.val - 1; i++) {
                    balloons.add(balloons.poll());
                }
            } else {
                for(int i=0; i<-b.val; i++) {
                    balloons.addFirst(balloons.pollLast());
                }
            }
        }
        br.close();
    }
}