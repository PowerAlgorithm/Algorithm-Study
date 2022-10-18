import java.io.*;
import java.util.*;

class Main {
    static int[] visit;
    static boolean[] checked;
    static int nodeCount, edgeCount, startNode, visitCount = 2;
    static List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 수, 간선 수, 시작 노드
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken()) - 1;

        // 방문 체크 배열 , 방문 순서 배열 생성
        checked = new boolean[nodeCount];
        visit = new int[nodeCount];

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

        // 시작 노드 방문 체크 및 방문 순서 1
        visit[startNode] = 1;
        checked[startNode] = true;
        dfs(startNode);

        // 방문 순서 배열 순회
        for (int i = 0; i < nodeCount; i++)
            bw.append(String.valueOf(visit[i])).append("\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int node) {
        // 인접 노드 순회
        for (int nearNode : list.get(node)) {
            if (!checked[nearNode]) {
                checked[nearNode] = true;
                // 방문 카운트는 전역 변수
                visit[nearNode] = visitCount++;
                dfs(nearNode);
            }
        }
    }
}