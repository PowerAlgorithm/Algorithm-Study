import java.io.*;
import java.util.*;

class Camp {
    int x;
    int y;
    int area;

    public Camp(int x, int y, int area) {
        this.x = x;
        this.y = y;
        this.area = area;
    }
}

class Main {

    public static int Find(int node) {
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if (relation[node] == node)
            return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    static int[] relation;
    static List<Camp> camps;
    static List<List<Integer>> nodes;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스 수
        int testCase = Integer.parseInt(br.readLine());
        for (int test = 0; test < testCase; test++) {
            // 적군의 진영 수
            int campCount = Integer.parseInt(br.readLine());

            // 처음에는 각 진영들은 독립적인 진영
            result = campCount;
            camps = new ArrayList<>();
            relation = new int[campCount];

            // 진영 입력
            for (int i = 0; i < campCount; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int area = Integer.parseInt(st.nextToken());
                camps.add(new Camp(x, y, area));
                relation[i] = i;
            }

            // 각 진영들의 범위가 겹치는지 어떻게 확인해야할지 모르겠다...
            // 참고 링크 https://steady-coding.tistory.com/110
            for (int i = 0; i < campCount; i++) {
                Camp c1 = camps.get(i);
                for (int j = i + 1; j < campCount; j++) {
                    Camp c2 = camps.get(j);
                    int x_dif = c1.x - c2.x;
                    int y_dif = c1.y - c2.y;
                    int sumArea = c1.area + c2.area;
                    if (Math.pow(x_dif, 2) + Math.pow(y_dif, 2) <= Math.pow(sumArea, 2)) {
                        // Find
                        int c1_root = Find(i);
                        int c2_root = Find(j);
                        // Union
                        if (c1_root != c2_root) {
                            relation[c1_root] = c2_root;
                            result--;
                        }
                    }
                }
            }
            sb.append(result).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}