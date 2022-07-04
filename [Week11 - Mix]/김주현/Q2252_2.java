// 2252번 줄 세우기 (G3) [위상정렬]
/*
<문제 정보>
 1. 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램 작성

<변수 범위>
 1. 2초 / 128MB
 2. 학생 수 1<=N<=32,000
 3. 키를 비교한 횟수 1<=M<=100,000
 4. 학생들 번호는 1~N

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

/*
    <위상 정렬>
    # 필요한 변수 및 자료구조
        - 줄을 세우는 ArrayList
        - 현재 나한테 오는 노드가 몇개 있는지 알려주는 in_degree 배열
        - bfs 탐색을 위해 필요한 Queue
        - 방문 표시를 위한 boolean 배열

    # 과정
        1. 주어진 정보로 인접 리스트 그래프를 만든다.
        2. 인접 리스트 그래프를 만들면서 in_degree 배열 또한 만든다. (간선 입력받는 동시에)
        3. bfs 진행
            - Queue에 넣는 노드들은 넣음과 동시에 방문 표시를 한다. (큐에 들어간 노드는 결국 방문 예정이기 때문에)
            - 큐에서 꺼내는 순서가 정렬하는 순서와 같다. 따라서 큐에서 요소를 꺼낼때 그 요소를 ArrayList에 더해준다.
            - 꺼낸 요소에 이어져 있는 (인접한) 노드의 in_degree 값을 하나 빼준다. (why ? 오던 요소가 하나 빠졌기 때문에)
            - in_degree 값이 0이 된다면 Queue에 넣어준다. (+ 방문표시)
            - Queue에 넣는 과정이 끝났을 때 큐가 비어있다면 탐색 완료.
 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q2252_2 {
    static int N, M;
    static D_Graph graph;
    static int[] in_degree;

    // 줄을 세울 ArrayList
    static ArrayList<Integer> line = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new D_Graph(N);
        in_degree = new int[N+1];

        // 1. 간선 입력
        // 2. in_degree 갱신
        int firstStudent_in, secondStudent_in;
        while (M-- >0) {
            st = new StringTokenizer(br.readLine());
            firstStudent_in = Integer.parseInt(st.nextToken());
            secondStudent_in = Integer.parseInt(st.nextToken());
            graph.addEdge(firstStudent_in, secondStudent_in);
            in_degree[secondStudent_in]++;
        }

        // ******************** 메인 로직 ********************

        // bfs로 탐색하며 줄을 세운다.
        bfs();

        // ******************** 정답 출력 ********************

        // line의 값을 순차적을 순차적으로 출력한다.
        for (int student : line) sb.append(student).append(" ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs() {
        Deque<Integer> q = new ArrayDeque<>();

        // in_degree를 돌며 값이 0인 학생들을 방문표시 + 큐에 넣어줌
        for (int i=1;i<=N;i++) {
            if (in_degree[i] == 0) {
                q.add(i);
                graph.visited[i] = true;
            }
        }

        int cur_student;
        while(!q.isEmpty()) {
            // 1. 큐에서 한 학생을 꺼낸다.
            cur_student = q.poll();

            // 2. 줄을 세운다.
            line.add(cur_student);

            // 3. 이 학생 다음에 서야하는 학생들 중 방문하지 않은 순회하며 in_degree 값을 1 감소시킨다.
            //      -> 만약 in_degree가 0이 된다면 큐에 넣는다. (+ 방문표시)
            for(int nextStudent : graph.adjList[cur_student]) {
                if(!graph.visited[nextStudent]) {
                    in_degree[nextStudent]--;
                    if (in_degree[nextStudent] == 0) {
                        graph.visited[nextStudent] = true;
                        q.add(nextStudent);
                    }
                }
            }
        }
    }
}

// 방향 그래프
class D_Graph {
    ArrayList<Integer>[] adjList;
    boolean[] visited;

    public D_Graph(int size) {
        adjList = new ArrayList[size+1];
        visited = new boolean[size+1];
        for (int i=1;i<=size;i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge (int v1, int v2) {
        adjList[v1].add(v2);
    }
}












