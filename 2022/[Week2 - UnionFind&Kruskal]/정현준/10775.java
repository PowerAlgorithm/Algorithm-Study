
import java.io.*;
import java.util.*;

class Main {

    public static int Find(int node) {
        // 배열의 인덱스와 해당 인덱스의 값고 똑같다면 해당 노드는 루트 취급한다.
        if (relation[node] == node)
            return node;
        // 다르다면 , 해당 집합의 루트를 찾는다.
        // Find의 리턴값을 relation[node]에 담아 경로를 압축한다.
        return relation[node] = Find(relation[node]);
    }

    public static void Union(int a, int b) {
        int root_a = Find(a);
        int root_b = Find(b);

        if (root_a != root_b) {
            relation[root_b] = root_a;
        }
    }

    static int[] relation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // DSU
        // 게이트 , 비행기 수 입력
        int gate = Integer.parseInt(br.readLine());
        int airplaneCount = Integer.parseInt(br.readLine());

        // 관계 배열 초기화
        relation = new int[gate + 1];
        for (int i = 0; i <= gate; i++) {
            relation[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < airplaneCount; i++) {
            int airplane = Integer.parseInt(br.readLine());
            // 현재 항공기가 도킹할 수 있는 게이트 번호를 찾는다 (root)
            int gateNumber = Find(airplane);
            if (gateNumber != 0) {
                // 도킹할 수 있는 게이트 번호가 0 번이 아니라면
                // 현재 gateNumber 와 gateNumber - 1을 Union 한다
                // 그럼 현재 gateNumber 는 gateNumber - 1을 바라보고 있게 된다
                Union(gateNumber - 1, gateNumber);
                answer++;
            } else
                break;
        }

        bw.append(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}