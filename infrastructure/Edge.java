package infrastructure;

/**
 * the Edge class represents an edge in a graph
 * 
 * @author Rentian Dong
 */
public class Edge<T> {
	
	public Vertex<T> parent;
	public Vertex<T> child;
	public int cost;
	public final boolean isDirected;
	
	public Edge(Vertex<T> parent, Vertex<T> child, int cost, boolean isDirected) {
		this.parent = parent;
		this.child = child;
		this.cost = cost;
		this.isDirected = isDirected;
	}
	
	@Override
	public boolean equals(Object other) {
		
		// if object comparing with is not even an edge
		if (other.getClass() != this.getClass())
			return false;
		else {
			@SuppressWarnings("unchecked")
			Edge<T> otherEdge = (Edge<T>) other;
			boolean b1 = this.parent.equals(otherEdge.parent);
			boolean b2 = this.child.equals(otherEdge.child);
			boolean b3 = this.cost == otherEdge.cost;
			boolean b4 = this.isDirected == otherEdge.isDirected;
			return b1 && b2 && b3 && b4;
		}
	}
 }
