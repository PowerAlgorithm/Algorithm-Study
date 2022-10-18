import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Position {
    float x;
    float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int node;
    float wasteTime;

    public Edge(int node, float wasteTime) {
        this.node = node;
        this.wasteTime = wasteTime;
    }

    @Override
    public int compareTo(Edge o) {
        return (int) (this.wasteTime - o.wasteTime);
    }
}

public class Main {
    static Position start, end;
    static final float WALK = 5f;
    static final float FLY_WASTE_TIME = 2f;
    static final float FLY = 50f;
    static float[] times;
    static List<Position> positions;
    static List<List<Edge>> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        positions = new ArrayList<>();
        edges = new ArrayList<>();

        float x = Float.parseFloat(st.nextToken());
        float y = Float.parseFloat(st.nextToken());
        start = new Position(x, y);

        st = new StringTokenizer(br.readLine());
        x = Float.parseFloat(st.nextToken());
        y = Float.parseFloat(st.nextToken());
        end = new Position(x, y);

        int cannonCount = Integer.parseInt(br.readLine());
        times = new float[cannonCount + 2];

        // 시작점
        edges.add(new ArrayList<>());
        times[0] = Float.MAX_VALUE;
        positions.add(start);

        // 대포
        for (int i = 1; i <= cannonCount; i++) {
            st = new StringTokenizer(br.readLine());
            edges.add(new ArrayList<>());
            times[i] = Float.MAX_VALUE;
            x = Float.parseFloat(st.nextToken());
            y = Float.parseFloat(st.nextToken());
            positions.add(new Position(x, y));
        }

        // 끝점
        edges.add(new ArrayList<>());
        times[cannonCount + 1] = Float.MAX_VALUE;
        positions.add(end);

        // 1. 시작점에서 각 대포와 끝점 까지 걸어가는 가중치
        for (int i = 1; i < cannonCount + 2; i++) {
            edges.get(0).add(new Edge(i, getDistance(start, positions.get(i)) / WALK));
        }

        // 2. 각 대포끼리와 각 대포에서 끝점 까지의 가중치
        for (int i = 1; i < cannonCount + 2; i++) {
            for (int j = 1; j < cannonCount + 2; j++) {
                if (i != j) {
                    Position s = positions.get(i);
                    Position e = positions.get(j);
                    float distance = getDistance(s, e);
                    float walk = distance / WALK;
                    float fly = Math.abs(distance - FLY) / WALK + FLY_WASTE_TIME;
                    float wasteTime = Math.min(walk, fly);
                    edges.get(i).add(new Edge(j, wasteTime));
                }
            }
        }
        dijkstra(0);
        System.out.println(times[cannonCount + 1]);
    }

    public static void dijkstra(int node) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(node, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            for (Edge nears : edges.get(now.node)) {
                float sum = now.wasteTime + nears.wasteTime;
                if (times[nears.node] > sum) {
                    times[nears.node] = sum;
                    pq.offer(new Edge(nears.node, sum));
                }
            }
        }
    }

    public static float getDistance(Position obj1, Position obj2) {
        float diffX = obj1.x - obj2.x;
        float diffY = obj1.y - obj2.y;
        return (float) Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
    }
}