package boggle;

import infrastructure.*;
import java.util.ArrayList;

public class Boggle {
	
	private Graph<String> graph;
	
	public Boggle(Graph<String> graph) {
		this.graph = graph;
	}

	public boolean isString(String toContain) {
		Vertex<String> startVertex;
		String startKey;
		for (int index = 0; index < graph.vertexKeys.size(); index++) {
			startKey = graph.vertexKeys.get(index);
			startVertex = graph.vertices.get(startKey);
			graph.undiscoverAll();
			if (isStringHelper(toContain, startVertex))
				return true;
		}
		return false;
	}
	
	// undiscover on the return path
	private boolean isStringHelper(String toContain, Vertex<String> current) {

		boolean toReturn = false;
		int index;
		String key = current.getKey();
		String nextChar = toContain.substring(0, 1);
		
		if (!current.discovered) {
			current.discovered = true;
			// base case, if toContain has only one letter and matches that on the
			// current vertex, the string we are looking for is on the boggle
			if (toContain.length() == 1 && key.equals(nextChar)) {
				return true;
			}
			// recursive case, if letter on current vertex matches but more
			// additional letters still need to be matahced
			else if (toContain.length() > 1 && key.equals(nextChar)) {
				Vertex<String> next;
				for (index = 0; index < current.children.size(); index++) {
					next = graph.vertices.get(current.children.get(index));
					toReturn = isStringHelper(toContain.substring(1, toContain.length()), next);
					if (toReturn)
						return true;
				}
			}
			// if the current key does not match the next letter to match
			else {
				current.discovered = false;
			}
		}
		// if the vertex is already discovered
		else
			toReturn = false;
		
		return toReturn;
	}
	
	public static void main(String[] args) {
		Vertex<String> g = new Vertex<>("g");
		Vertex<String> i = new Vertex<>("i");
		Vertex<String> z = new Vertex<>("z");
		Vertex<String> u = new Vertex<>("u");
		Vertex<String> e1 = new Vertex<>("e1");
		Vertex<String> k = new Vertex<>("k");
		Vertex<String> q = new Vertex<>("q");
		Vertex<String> s = new Vertex<>("s");
		Vertex<String> e2 = new Vertex<>("e2");
		
		g.addChild("i", 0);
		g.addChild("e1", 0);
		g.addChild("u", 0);
		
		i.addChild("g", 0);
		i.addChild("u", 0);
		i.addChild("e1", 0);
		i.addChild("k", 0);
		i.addChild("z", 0);
		
		z.addChild("i", 0);
		z.addChild("e1", 0);
		z.addChild("k", 0);
		
		u.addChild("g", 0);
		u.addChild("i", 0);
		u.addChild("e1", 0);
		u.addChild("s", 0);
		u.addChild("q", 0);
		
		e1.addChild("g", 0);
		e1.addChild("i", 0);
		e1.addChild("z", 0);
		e1.addChild("k", 0);
		e1.addChild("e2", 0);
		e1.addChild("s", 0);
		e1.addChild("q", 0);
		e1.addChild("u", 0);
		
		k.addChild("z", 0);
		k.addChild("i", 0);
		k.addChild("e1", 0);
		k.addChild("s", 0);
		k.addChild("e2", 0);
		
		q.addChild("u", 0);
		q.addChild("e1", 0);
		q.addChild("s", 0);
		
		s.addChild("q", 0);
		s.addChild("u", 0);
		s.addChild("e1", 0);
		s.addChild("k", 0);
		s.addChild("e2", 0);
		
		e2.addChild("k", 0);
		e2.addChild("e1", 0);
		e2.addChild("s", 0);
		
		ArrayList<Vertex<String>> list = new ArrayList<Vertex<String>>();
		list.add(g);
		list.add(i);
		list.add(z);
		list.add(u);
		list.add(e1);
		list.add(k);
		list.add(q);
		list.add(s);
		list.add(e2);
		
		Graph<String> myGraph = new Graph<>(list);
		System.err.println(myGraph);
		
		Boggle boggle = new Boggle(myGraph);
		System.out.println(boggle.isString("qskig"));
	}

}
