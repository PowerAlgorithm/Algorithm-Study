import java.io.*;
import java.util.*;

class Main {
    static boolean[] visited;
    static List<List<Integer>> list = new ArrayList<>();
    static int[] degree;
    static int nodeCount, edgeCount;

    public static void main(String[] args) throws IOException {
        // 이 문제는 '오일러의 경로'의 특징을 알면 되는 문제이다
        // '오일러의 경로'를 구할 수 있는지만 구분하면 되기 때문에 해당 알고리즘의 특성만 알고 있으면 된다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        // 방문 배열 , 노드의 차수 기록용 배열 생성
        visited = new boolean[nodeCount];
        degree = new int[nodeCount];

        // 인접리스트 생성
        for (int i = 0; i < nodeCount; i++)
            list.add(new ArrayList<>());

        // 인접리스트 입력 및 각 노드의 차수 기록
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            degree[node1]++;
            degree[node2]++;
            list.get(node1).add(node2);
            list.get(node2).add(node1);
        }

        // 홀수 차수를 가진 노드를 확인한다
        int oddCount = 0;
        for (int i = 0; i < nodeCount; i++) {
            if (degree[i] % 2 == 1)
                oddCount++;
        }

        // 차수가 홀수인 노드는 0개 또는 2개이어야 한다. (시작점 , 끝점)
        String result = "YES";
        if (oddCount == 1 || oddCount > 2) {
            result = "NO";
        } else {
            // 차수가 홀수인 노드가 0개 또는 2개 일때 모든 노드들이 연결이 되는지 확인
            visited[0] = true;
            dfs(0);
            for (boolean value : visited) {
                if (!value) {
                    result = "NO";
                    break;
                }
            }
        }
        bw.append(result);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int node) {
        for (int nearNode : list.get(node)) {
            if (!visited[nearNode]) {
                visited[nearNode] = true;
                dfs(nearNode);
            }
        }
    }
}