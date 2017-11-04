package prim;

import infrastructure.*;
import java.util.ArrayList;


/**
 * finds edges of a minimum sapnning tree using Prim's algorithm
 * 
 * @author Rentian Dong
 */
public class Prim<T> {
	
	public ArrayList<Vertex<T>> vList;
	public ArrayList<Edge<T>> allEdges;
	// graph gradually constructed to determine if a new edge is useful
	public Graph<T> spanTree;
	public Graph<T> in;
	
	public Prim(Graph<T> in) {
		
		this.in = in;
		vList = new ArrayList<>();
		allEdges = new ArrayList<>();
		spanTree = new Graph<T>();
		
		// Construct the list of edges
		Edge<T> tempEdge;
		Vertex<T> currentVertex;
		Vertex<T> tempParent;
		Vertex<T> tempChild;
		int tempCost;
		for (int index = 0; index < in.vertexKeys.size(); index++) {
			currentVertex = in.vertices.get(in.vertexKeys.get(index));
			tempParent = currentVertex;
			for (int index2 = 0; index2 < currentVertex.children.size(); index2++) {
				tempChild = in.vertices.get(currentVertex.children.get(index2));
				tempCost = currentVertex.costs.get(tempChild.key);
				// for Prim's algorithm we consider only non-directed graph
				tempEdge = new Edge<T>(tempParent, tempChild, tempCost, false);
				allEdges.add(tempEdge);
			}
		}
	}
	
	/**
	 * sort all edges in increasing cost
	 */
	private void sortEdges() {
		
	}
}
