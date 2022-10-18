import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Move implements Comparable<Move> {
    int startNode;
    int endnode;
    int weight;

    public Move(int startNode, int endNode, int weight) {
        this.startNode = startNode;
        this.endnode = endNode;
        this.weight = weight;
    }

    @Override
    public int compareTo(Move o) {
        return o.weight - this.weight;
    }

    @Override
    public String toString() {
        return "Move{" +
                "startNode=" + startNode +
                ", endnode=" + endnode +
                ", weight=" + weight +
                '}';
    }
}

class Main {
    static List<Move> moves;
    static int node, edge;
    static int[] relation, cost;

    public static boolean isConnect(int s, int t) {
        if (Find(s) == Find(t))
            return true;
        return false;
    }

    public static void Union(Move move) {
        int firstRoot = Find(move.startNode);
        int secondRoot = Find(move.endnode);

        relation[Math.max(firstRoot, secondRoot)] = Math.min(firstRoot, secondRoot);
    }

    public static int Find(int node) {
        if (relation[node] == node)
            return node;
        return relation[node] = Find(relation[node]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 섬과 다리의 수
        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());

        // 관계 배열 , 비용 배열 생성
        relation = new int[node + 1];
        cost = new int[node + 1];

        // 관계 배열 초기화
        for (int i = 0; i <= node; i++)
            relation[i] = i;

        moves = new ArrayList<>();
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            moves.add(new Move(start, destination, weight));
        }

        // 시작 지점 , 도착 지점
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        // moves 비용 별 내림차순 정렬
        Collections.sort(moves);
        for (Move move : moves) {
            // 비용이 높은 다리 순서로 연결된 2개의 노드 관계 세팅
            Union(move);
            if (isConnect(start, destination)) {
                bw.append(String.valueOf(move.weight));
                break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
