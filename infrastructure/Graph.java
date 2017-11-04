package infrastructure;

import java.util.ArrayList; // to store the list of vertices
import java.util.Hashtable;
import java.util.List;

/**
 * Graph is the Graph referred to constantly in algorithms
 * 
 * @author Rentian Dong
 */
public class Graph<TKey> {

	public Hashtable<TKey, Vertex<TKey>> vertices;
	public ArrayList<TKey> vertexKeys; // ArrayList with all vertexKeys
	
	/**
	 * NodeMap Constructor. The connection of vertices is established while 
	 * constructing the NodeMap. This constructor was written specifically for
	 * Let's Do Lunch.
	 * 
	 * @param vertexPairs the ArrayList<ArrayList<String>> returned by calling
	 * getMapList on a parser instance.
	 */
	public Graph(ArrayList<ArrayList<TKey>> vertexPairs) {
		
		final int PARENT = 0; // index for parent and child node
		final int CHILD = 1;
		
		vertices = new Hashtable<TKey, Vertex<TKey>>();
		vertexKeys = new ArrayList<TKey>();
		
		Vertex<TKey> tempParent; // temporary reference to vertices during consturction
		Vertex<TKey> tempChild;
		TKey parentKey; // " " node names " "
		TKey childKey;
		
		// create the vertices and store them
		for (int index = 0; index < vertexPairs.size(); index++) {
			
			parentKey = vertexPairs.get(index).get(PARENT);
			childKey = vertexPairs.get(index).get(CHILD);
			
			// create not pre-existent parent node
			if (!vertexKeys.contains(parentKey)) {
				tempParent = new Vertex<TKey>(parentKey);
				vertices.put(parentKey, tempParent);
				vertexKeys.add(parentKey);
			}
			// otherwise refer to the one already craeted
			else
				tempParent = vertices.get(parentKey);
				
			// repeat for child node
			if (!vertexKeys.contains(childKey)) {
				tempChild = new Vertex<TKey>(childKey);
				vertices.put(childKey, tempChild);
				vertexKeys.add(childKey);
			}
			else
				tempChild = vertices.get(childKey);
			
			// establish up-down stream relationship. Costs does not matter for
			// Let's Do Lunch so just use 0 as a place holder.
			tempParent.addChild(childKey, 0);
			tempChild.addParent(parentKey);
		}
	}
	
	/**
	 * Constructor that generates a graph using a list of precomposed vertices
	 * 
	 * @param verticesList the list of vertices to generate graph from
	 */
	public Graph(List<Vertex<TKey>> verticesList) {
		
		vertices = new Hashtable<TKey, Vertex<TKey>>();
		vertexKeys = new ArrayList<TKey>();
		
		for(int index = 0; index < verticesList.size(); index++) {
			vertexKeys.add(verticesList.get(index).getKey());
			vertices.put(vertexKeys.get(index), verticesList.get(index));
		}
	}
	
	/**
	 * default constructor, use this to gradually build a graph at run time
	 */
	public Graph() {
		vertices = new Hashtable<>();
		vertexKeys = new ArrayList<>();
	}
	
	/**
	 * return all vertices to the undiscovered state
	 */
	public void undiscoverAll() {
		TKey tempKey;
		for (int index = 0; index < vertexKeys.size(); index++) {
			tempKey = vertexKeys.get(index);
			vertices.get(tempKey).discovered = false;
		}
	}
	
	/**
	 * return a String representation of this map, for debugging
	 */
	public String toString() {
		
		String stringRep = ""; // to store final String representation
		
		for (TKey name : vertexKeys) {
			
			// name of current node
			stringRep += "Node Name:\n";
			stringRep += name;
			
			// all parents of current node
			stringRep += "\n";
			stringRep += "Parents Name:\n";
			for (TKey parentKey : vertices.get(name).getParents()) {
				stringRep += parentKey;
				stringRep += " ";
			}

			// all children of current node
			stringRep += "\n";
			stringRep += "Children Name:\n";
			for (TKey childKey : vertices.get(name).getChildren()) {
				stringRep += childKey;
				stringRep += " ";
			}
			
			stringRep += "\n\n";
		}
		
		return stringRep;
	}
}