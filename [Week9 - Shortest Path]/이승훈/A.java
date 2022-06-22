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

class Node implements Comparable<Node>{
    int v;
    int w;

    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }

    @Override
    public String toString() {
        return "Node{" +
                "v=" + v +
                ", w=" + w +
                '}';
    }
}
class Main {
    static int V, E;
    static ArrayList<Node> graph[];
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = (ArrayList<Node>[]) new ArrayList[V+1];
        distance = new int[V + 1];
        int start = Integer.parseInt(br.readLine());
        for(int i=0; i<V+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, w));
        }
        for(int i=0; i<V+1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        PriorityQueue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(start, 0));

        while(!heap.isEmpty()) {
            Node cur = heap.poll();

            if (distance[cur.v] < cur.w) {
                continue;
            }
            for(Node next : graph[cur.v]) {
                if(distance[next.v] > distance[cur.v] + next.w) {
                    distance[next.v] = distance[cur.v] + next.w;
                    heap.add(new Node(next.v, distance[next.v]));
                }
            }
        }

        for(int i=1; i<V+1; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }else {
                System.out.println(distance[i]);
            }
        }
        br.close();
    }
}