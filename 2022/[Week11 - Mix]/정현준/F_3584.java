import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> nodes;
    static boolean[] checked;
    static int[] parentArr, depthArr;
    static int nodeSize, targetLeft, targetRight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        while (testCase-- > 0) {
            nodeSize = Integer.parseInt(br.readLine());
            nodes = new ArrayList<List<Integer>>();
            checked = new boolean[nodeSize + 1]; // root노드를 찾기 위한 배열
            parentArr = new int[nodeSize + 1]; // 노드의 부모 배열
            depthArr = new int[nodeSize + 1]; // 노드의 깊이 배열

            for (int i = 0; i <= nodeSize; i++)
                nodes.add(new ArrayList<Integer>());

            StringTokenizer st;
            for (int i = 0; i < nodeSize; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                if (i == nodeSize - 1) {
                    targetLeft = s;
                    targetRight = e;
                } else {
                    nodes.get(s).add(e);
                    checked[e] = true;
                }
            }
            dfs(findRoot(), 1, 0);
            System.out.println(LCA(targetLeft, targetRight));
        }
    }

    public static String LCA(int target1, int target2) {
        int target_1_depth = depthArr[target1];
        int target_2_depth = depthArr[target2];

        while (target_1_depth > target_2_depth) {
            target1 = parentArr[target1];
            target_1_depth--;
        }

        while (target_2_depth > target_1_depth) {
            target2 = parentArr[target2];
            target_2_depth--;
        }

        while (target1 != target2) {
            target1 = parentArr[target1];
            target2 = parentArr[target2];
        }

        return String.valueOf(target1);
    }

    public static void dfs(int node, int depth, int parent) {
        depthArr[node] = depth;
        parentArr[node] = parent;
        for (int nearNode : nodes.get(node)) {
            dfs(nearNode, depth + 1, node);
        }
    }

    public static int findRoot() {
        for (int i = 1; i <= nodeSize; i++) {
            if (!checked[i])
                return i;
        }
        return 0;
    }
}