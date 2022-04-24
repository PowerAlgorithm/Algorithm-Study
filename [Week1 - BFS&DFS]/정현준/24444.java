import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class Main {
    static int nodeCount, edgeCount;
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 수, 간선 수, 시작 노드
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken()) - 1;

        // 양방향 인접 리스트 노드 크기만큼 초기화
        for (int i = 0; i < nodeCount; i++)
            list.add(new ArrayList<>());

        // 양방향 인접 리스트 생성
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            // 노드의 번호는 입력 값 - 1
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            list.get(node1).add(node2);
            list.get(node2).add(node1);
        }

        // 각 노드들의 인접 노드 오름차순으로 정렬
        for (int i = 0; i < nodeCount; i++)
            Collections.sort(list.get(i));

        bw.append(bfs(startNode));

        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs(int node) {
        StringBuilder sb = new StringBuilder();
        int visitCount = 1;
        int[] visit = new int[nodeCount];
        boolean[] checked = new boolean[nodeCount];
        visit[node] = visitCount;
        checked[node] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int nearNode : list.get(now)) {
                if (!checked[nearNode]) {
                    checked[nearNode] = true;
                    visit[nearNode] = ++visitCount;
                    queue.offer(nearNode);
                }
            }
        }

        for (int val : visit) {
            sb.append(val).append("\n");
        }

        return sb.toString();
    }

}