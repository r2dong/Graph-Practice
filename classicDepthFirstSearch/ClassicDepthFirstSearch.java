package classicDepthFirstSearch;

import infrastructure.*;
import java.util.ArrayList;

public class ClassicDepthFirstSearch<T> {
	
	private Graph<T> graph;
	
	public ClassicDepthFirstSearch(Graph<T> graph) {
		this.graph = graph;
	}

	public void depthFirstSearch(Vertex<T> current) {
		
		ArrayList<T> nextLevel = current.children;
		Vertex<T> next;
		
		for (int index = 0; index < nextLevel.size(); index++) {
			next = graph.vertices.get(nextLevel.get(index));
			if (!next.discovered) {
				next.discovered = true;
				next.discoveredBy = current.getKey();
				depthFirstSearch(next);
			}
		}		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
