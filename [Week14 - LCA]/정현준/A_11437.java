import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {
	static int[] parent , depth;
	static boolean[] visited;
	static final int ROOT = 1;
	static List<List<Integer>> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int size = Integer.parseInt(br.readLine());
        
        parent = new int[size + 1];
        depth = new int[size + 1];
        visited = new boolean[size + 1];
        
        edges = new ArrayList<List<Integer>>();
        for(int i = 0 ; i <= size ; i++) {
        	edges.add(new ArrayList<Integer>());
        }
        
        StringTokenizer st;
        for(int i = 0 ; i < size - 1 ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	edges.get(node1).add(node2);
        	edges.get(node2).add(node1);
        }

        parentInit(ROOT , 0 , 0);

        int findSize = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(findSize-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	int node1 = Integer.parseInt(st.nextToken());
        	int node2 = Integer.parseInt(st.nextToken());
        	sb.append(find(node1 , node2)).append("\n");
        }
        
        System.out.println(sb.toString());
	}
    
    public static void parentInit(int node , int parentNode , int depthCount) {
    	if(!visited[node]) {
        	visited[node] = true;
        	parent[node] = parentNode;
        	depth[node] = depthCount;
        	for(int nearNode : edges.get(node)) {
        		parentInit(nearNode , node , depthCount + 1);
        	}
    	}
    }
    
    public static int find(int node1 , int node2) {
    	int node1Depth = depth[node1];
    	int node2Depth = depth[node2];
    	
    	while(node1Depth < node2Depth) {
    		node2Depth--;
    		node2 = parent[node2];
    	}

    	while(node1Depth > node2Depth) {
    		node1Depth--;
    		node1 = parent[node1];
    	}
    	
    	while(node1 != node2) {
    		node1 = parent[node1];
    		node2 = parent[node2];
    	}
    	
    	return node1;
    }
}