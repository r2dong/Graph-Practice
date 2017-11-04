package infrastructure;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Node represents positions at which streams intersect
 * 
 * @author Rentian Dong
 */
public class Vertex<TKey> {
	
	public ArrayList<TKey> parents; // from which streams flow toward this node
	public ArrayList<TKey> children; // to which streams flow from this node
	public TKey discoveredBy; // from which this vertex is discovered
	public Hashtable<TKey, Integer> costs;
	public final TKey key; // name of this node
	public boolean discovered; // if this node has been traversed in search
	
	/**
	 * Constructor of node
	 * 
	 * @param name name of this node
	 */
	public Vertex (TKey key) {
		parents = new ArrayList<TKey>();
		children = new ArrayList<TKey>();
		this.key = key;
		discovered = false;
		costs = new Hashtable<>();
	}
	
	/**
	 * add a parent to this node
	 * 
	 * @param toAdd name of parent
	 */
	public void addParent (TKey toAdd) {
		parents.add(toAdd);
	}
	
	/**
	 * add a child to this node
	 * 
	 * @param toAdd name of parent
	 */
	public void addChild (TKey toAdd, int cost) {
		children.add(toAdd);
		costs.put(toAdd, cost);
	}
	
	/**
	 * getter method of node name
	 * 
	 * @return name of this node
	 */
	public TKey getKey() {
		return key;
	}
	
	/**
	 * getter method for parents
	 * 
	 * @return a HashSet containing names of parents of this node
	 */
	protected ArrayList<TKey> getParents() {
		return parents;
	}

	/**
	 * getter method for children
	 * 
	 * @return a HashSet containing names of children of this node
	 */
	protected ArrayList<TKey> getChildren() {
		return children;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		
		// false if not the same kind of object
		if (other.getClass() != this.getClass())
			return false;
		else {
			return this.key.equals(((Vertex<TKey>)other).key);
		}
	}
}

