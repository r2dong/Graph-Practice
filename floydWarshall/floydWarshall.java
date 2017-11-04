package floydWarshall;

import infrastructure.Graph;
import infrastructure.Vertex;
import java.util.ArrayList;
/**
 * Implementation of floydWarshall algorithm
 * 
 * @author Rentian Dong
 */
public class floydWarshall {
	
	private final Graph<Integer> graph;
	
	/**
	 * Constructor
	 * 
	 * @param graph the graph whose minimum distances will be calculated
	 */
	public floydWarshall(Graph<Integer> graph) {
		this.graph = graph;
	}
	
	/**
	 * Compute the minimum path from all vertices to all other vertices.
	 * 
	 * @return an int[][] representing a matrix, the elements presenting the
	 * distances.
	 */
	public int[][] computeMinPathAll() {
		
		// initialize matrix to hold result
		int numVertices = graph.vertexKeys.size();
		int[][] lengthMatrix = new int[numVertices][numVertices];
		
		// iterate through all nodes using floyd-warshall algorithm
		for (int indexOuter = 0; indexOuter < numVertices; indexOuter++) {
			for (int indexInner = 0; indexInner < numVertices; indexInner++) {
				int length = computeMinPath(indexOuter, indexInner, numVertices - 1);
				lengthMatrix[indexOuter][indexInner] = length; 
			}
		}
		
		return lengthMatrix;
	}
	
	/**
	 * The core of floydWarshall algorithm. Deteremines the minimum length from
	 * one vertex to another within a subset of all vertices.
	 * 
	 * @param from starting vertex
	 * @param to destination vertex
	 * @param upTo vertex to be used in computation
	 * @return an integer representing the minimum distance from the starting
	 * vertex to the destination vertex using the subset of vertices.
	 */
	public int computeMinPath(int from, int to, int upTo) {
		
		Vertex<Integer> fromVertex = graph.vertices.get(from);
		int toReturn;
		
		// base case, return cost between from and to
		if (upTo == -1) {
			if (fromVertex.children.contains(to))
				toReturn = fromVertex.costs.get(to);
			else
				// fail safe if there is no path from a to b
				toReturn = Integer.MAX_VALUE;
		}
		// recursive case, find the minimum of shortest path goes through upTo or
		// shortest path does not go through upTo
		else { // length of shortest path not going thourhg upTo'th node
			int notThroughUpTo = computeMinPath(from, to, upTo - 1);
			// length of shortest paht gogin through upTo'th node
			int length1 = computeMinPath(from, upTo, upTo - 1);
			int length2 = computeMinPath(upTo, to, upTo - 1);
			// gurad against overflow
			if (length1 == Integer.MAX_VALUE || length2 == Integer.MAX_VALUE)
				toReturn = notThroughUpTo;
			else			
				toReturn = Integer.min(notThroughUpTo, length1 + length2);
		}
		
		System.err.println("Min path: " + toReturn + "\n");
		return toReturn;
	}
	
	// use main method here for some simple test cases
	public static void main(String args[]) {
		Vertex<Integer> v0 = new Vertex<>(0);
		Vertex<Integer> v1 = new Vertex<>(1);
		Vertex<Integer> v2 = new Vertex<>(2);
		Vertex<Integer> v3 = new Vertex<>(3);
		v0.addChild(1, 1);
		v1.addChild(2, 2);
		v2.addChild(3, 3);
		v3.addChild(0, 4);
		v1.addChild(3, 3);
		v2.addChild(0, 4);
		ArrayList<Vertex<Integer>> verticesList = new ArrayList<>();
		verticesList.add(v0);
		verticesList.add(v1);
		verticesList.add(v2);
		verticesList.add(v3);
		Graph<Integer> g = new Graph<>(verticesList);
		floydWarshall f = new floydWarshall(g);
		int[][] result = f.computeMinPathAll();
		int o = result.length;
		int i = result[0].length;
		for (int j = 0; j < o; j++) {
			for (int k = 0; k < i; k++) {
				System.out.print(j + " to " + k + ": " + result[j][k]);
				System.out.println();
			}
			
		}
	}
}
