// 3584번 가장 가까운 공통 조상 [해시] [DFS - 풀이 아직] [LCA (최소 공통 조상 알고리즘)]
/*
<문제 정보>
 1.트리의 두 노드가 주어질 때 가장 가까운 공통조상 출력

<변수 범위>
 1. 1초 / 128MB
 2. 2<=N<=10,000



<프로그램 진행>
 1.

<필요 함수>
 1.

 */

/*
 1. 노드 개수만큼의 parent 배열 -> 각 노드의 parent를 저장
 2. 첫번째 노드의 parent를 탐색하며 순서대로 HashSet 에 넣는다.
 3. 두번째 노드의 parent를 탐색하며 앞서 저장해 둔 HashSet에 있는지 살펴본다. 있으면 그게 정답

 ** root 노드를 제외한 나머지 노드는 모두 부모가 있음
    -> root 노드의 배열 값은 초기화 상태 그대로! (0)
 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;

public class Q3584 {
    static int N;
    static int[] parentOfNode;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 테스트 케이스 입력 ********************
        int T = Integer.parseInt(br.readLine());

        // ******************** Main ********************
        while(T-- >0) {
            // ******************** 입력 & 초기화 ********************
            N = Integer.parseInt(br.readLine());
            parentOfNode = new int[N+1];
            int parent, child;
            for (int i=0;i<N-1;i++) {
                st = new StringTokenizer(br.readLine());
                parent = Integer.parseInt(st.nextToken());
                child = Integer.parseInt(st.nextToken());
                parentOfNode[child] = parent;
            }

            int firstNode, secondNode;
            st = new StringTokenizer(br.readLine());
            firstNode = Integer.parseInt(st.nextToken());
            secondNode = Integer.parseInt(st.nextToken());

            // ******************** 메인 로직 ********************

            // 1. 첫번째 노드의 부모들을 거슬러 올라가며 HashSet에 담아줌
            HashSet<Integer> firstNodeToRoot = new HashSet<>();
            int parent_tmp = firstNode;
            while(parent_tmp != 0) {
                firstNodeToRoot.add(parent_tmp);
                parent_tmp = parentOfNode[parent_tmp];
            }

            // 2. 두번째 노드의 부모들을 거슬러 올라가며 탐색하며 HashSet에 있는지 확인
            int firstCommonParent = secondNode;
            while(firstCommonParent != 0) {
                if (firstNodeToRoot.contains(firstCommonParent)) break;
                firstCommonParent = parentOfNode[firstCommonParent];
            }

            // ******************** 정답 출력 ********************
            sb.append(firstCommonParent).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}




















