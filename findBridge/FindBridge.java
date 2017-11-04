package findBridge;

import java.util.ArrayList;
import infrastructure.*;
import java.util.Hashtable;

public class FindBridge<T> {
	
	private Graph<T> graph;
	
	public FindBridge(Graph<T> graph) {
		this.graph = graph;
	}

	// no need for input argument since searching for bridges does not depend
	// on the starting point. This algorithm uses the first vertex returned 
	// from iteration as starting point, but it could really be any vertex
	public void findBridge() {
		
		Hashtable<T, Integer> orderOfDiscovery = new Hashtable<T, Integer>();
		Hashtable<T, Integer> low = new Hashtable<T, Integer>();
		Hashtable<T, T> parents = new Hashtable<T, T>();
		Vertex<T> start = graph.vertices.get(graph.vertexKeys.get(0));
		
		// set up tables low, parents, and orderOfDiscovery
		findHelper(start, start, orderOfDiscovery, low, parents, 1);
		
		// determine the bridges by checking if low is same as order
		for (int index = 1; index < graph.vertexKeys.size(); index++) {
			if (low.get(graph.vertexKeys.get(index)) == orderOfDiscovery.get(graph.vertexKeys.get(index))) {
				System.out.print(graph.vertexKeys.get(index));
				System.out.print(" and ");
				System.out.print(graph.vertices.get(parents.get(graph.vertexKeys.get(index))).getKey());
				System.out.println(" form a bridge.");
			}
		}
	}
	
	// find Helper determines the order of discovery and the lows of all
	// vertices of the map, the wrapper function then uses the information
	// to determine the critical nodes
	private void findHelper(Vertex<T> current, Vertex<T> start, 
			Hashtable<T, Integer> orderOfDiscovery, Hashtable<T, Integer> low,
			Hashtable<T, T> parents, int order) {

		ArrayList<T> nextLevel = current.children;
		Vertex<T> next;
		
		// update discovery order and low. this will always update previously
		// undiscovered vertices because this function goes into recursion for
		// only undiscovered vertices
		orderOfDiscovery.put(current.getKey(), order);
		low.put(current.getKey(), order);
		current.discovered = true;
		
		// look at all children of current vertex
		for (int index = 0; index < nextLevel.size(); index++) {
			next = graph.vertices.get(nextLevel.get(index));
			// do recursion on undiscovered vertices
			if (!next.discovered) {
				parents.put(next.getKey(), current.getKey());
				findHelper(next, start, orderOfDiscovery, low, parents, ++order);
			}
			// update low of all parents if hit an already discocvered vertex
			else {
				// the parent of a vertex could never be itself, because that means
				// vertex is undiscovered when it discovered itself, meaning that it
				// is already discovered.
				
				// prevent recursion back to the parent (and no need to check parent)
				if (!next.getKey().equals(start.getKey()) && !current.getKey().equals(start.getKey()))
					if (parents.get(current.getKey()).equals(next.getKey()))
						continue;
				
				// update low of the back edge
				int newLow = low.get(next.getKey());
				Vertex<T> working = current;
				boolean isBackEdge = orderOfDiscovery.get(current.getKey()) > orderOfDiscovery.get(next.getKey());
				
				while (!working.getKey().equals(next.getKey()) && isBackEdge) {
					if (low.get(working.getKey()) > newLow)
						low.put(working.getKey(), newLow);
					T parentKey = parents.get(working.getKey());
					working = graph.vertices.get(parentKey);
				}
			}
		}
	}
	
	public static void main(String args[]) {
		Vertex<String> a = new Vertex<>("a");
		Vertex<String> b = new Vertex<>("b");
		Vertex<String> c = new Vertex<>("c");
		Vertex<String> d = new Vertex<>("d");
		Vertex<String> e = new Vertex<>("e");
		Vertex<String> f = new Vertex<>("f");
		// Vertex<String> g = new Vertex<>("g");
		
		/*
		a.addChild("b", 0);
		a.addChild("c", 0);
		
		b.addChild("a", 0);
		b.addChild("c", 0);
		
		c.addChild("a", 0);
		c.addChild("b", 0);
		c.addChild("d", 0);
		c.addChild("e", 0);
		
		d.addChild("c", 0);

		e.addChild("c", 0);
		e.addChild("f", 0);
		e.addChild("g", 0);
		
		f.addChild("e", 0);
		f.addChild("g", 0);
		*/
		
		// g.addChild("f", 0);
		// g.addChild("e", 0);
		
		a.addChild("b", 0);
		a.addChild("c", 0);
		
		b.addChild("a", 0);
		b.addChild("c", 0);
		
		c.addChild("a", 0);
		c.addChild("b", 0);
		c.addChild("d", 0);
		
		d.addChild("c", 0);
		d.addChild("f", 0);
		d.addChild("e", 0);
		
		e.addChild("d", 0);
		e.addChild("f", 0);
		
		f.addChild("e", 0);
		f.addChild("d", 0);
		
		ArrayList<Vertex<String>> list = new ArrayList<Vertex<String>>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		// list.add(g);
		
		FindBridge<String> test = new FindBridge<String>(new Graph<String>(list));
		test.findBridge();
	}
}
