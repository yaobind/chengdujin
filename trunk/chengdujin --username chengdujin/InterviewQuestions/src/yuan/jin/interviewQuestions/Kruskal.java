package yuan.jin.interviewQuestions;

import java.util.Set;
import java.util.HashSet;
import java.util.Vector;
import java.util.Iterator;
import java.lang.Comparable;
import java.util.Collections;

/**
 * An implementation of Kruskal's Minimum Spanning Tree Algorithm. Including an
 * example implementation of Node, Edge, and Graph.
 * 
 * http://www.dreamincode.net/code/snippet1619.htm
 * 
 * @author Yuan
 * 
 */
public class Kruskal {
	public Kruskal() {
	} // empty constructor

	public Graph createGraph() {
		Graph graph = new Graph();

		KNode n1, n2;

		n1 = new KNode("A");
		graph.addNode(n1);
		n2 = new KNode("B");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 1));

		n1 = new KNode("C");
		graph.addNode(n1);
		n2 = new KNode("D");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 2));

		n1 = new KNode("E");
		graph.addNode(n1);
		n2 = new KNode("F");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 3));

		n1 = new KNode("G");
		graph.addNode(n1);
		n2 = new KNode("H");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 4));

		// box
		graph.addEdge(new Edge((KNode) graph.getNode("B"), (KNode) graph
				.getNode("C"), 5));
		graph.addEdge(new Edge((KNode) graph.getNode("D"), (KNode) graph
				.getNode("E"), 6));
		graph.addEdge(new Edge((KNode) graph.getNode("F"), (KNode) graph
				.getNode("G"), 7));
		graph.addEdge(new Edge((KNode) graph.getNode("H"), (KNode) graph
				.getNode("A"), 8));

		// cross
		graph.addEdge(new Edge((KNode) graph.getNode("A"), (KNode) graph
				.getNode("G"), 9));
		graph.addEdge(new Edge((KNode) graph.getNode("C"), (KNode) graph
				.getNode("G"), 10));
		graph.addEdge(new Edge((KNode) graph.getNode("C"), (KNode) graph
				.getNode("E"), 11));

		/*
		 GRAPH: 
				 (A)---1---(B)---5---(C)---2---(D)
				  | \		     / \	|
				  |  \		    /   \	|
				  |   \		   /	 \	|
				  8    9         10	  11	6
				  |      \      /	    \	|
				  |       \    /	     \	|
				  |        \  /		      \	|
				 (H)---4---(G)---7---(F)---3---(E)
				 
		  MST: 
				 (A)---1---(B)---5---(C)---2---(D)
				     		  		|
				  		       		|
				  	 	  		|
				  	     		    	6
				  	    		    	|
				  	     		     	|
				  			  	|
				 (H)---4---(G)---7---(F)---3---(E)
		 */

		return graph;
	}

	public Graph createGraph2() {
		Graph graph = new Graph();

		KNode n1, n2;

		n1 = new KNode("A");
		graph.addNode(n1);
		n2 = new KNode("B");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 4));

		n1 = new KNode("C");
		graph.addNode(n1);
		n2 = new KNode("D");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 7));

		n1 = new KNode("E");
		graph.addNode(n1);
		n2 = new KNode("F");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 10));

		n1 = new KNode("G");
		graph.addNode(n1);
		n2 = new KNode("H");
		graph.addNode(n2);
		graph.addEdge(new Edge(n1, n2, 1));

		n1 = new KNode("I");
		graph.addNode(n1);

		graph.addEdge(new Edge((KNode) graph.getNode("B"), (KNode) graph
				.getNode("C"), 8));
		graph.addEdge(new Edge((KNode) graph.getNode("D"), (KNode) graph
				.getNode("E"), 9));
		graph.addEdge(new Edge((KNode) graph.getNode("D"), (KNode) graph
				.getNode("F"), 14));
		graph.addEdge(new Edge((KNode) graph.getNode("C"), (KNode) graph
				.getNode("F"), 4));
		graph.addEdge(new Edge((KNode) graph.getNode("F"), (KNode) graph
				.getNode("G"), 2));
		graph.addEdge(new Edge((KNode) graph.getNode("B"), (KNode) graph
				.getNode("H"), 11));
		graph.addEdge(new Edge((KNode) graph.getNode("H"), (KNode) graph
				.getNode("A"), 8));
		graph.addEdge(new Edge((KNode) graph.getNode("G"), (KNode) graph
				.getNode("I"), 6));
		graph.addEdge(new Edge((KNode) graph.getNode("H"), (KNode) graph
				.getNode("I"), 7));
		graph.addEdge(new Edge((KNode) graph.getNode("C"), (KNode) graph
				.getNode("I"), 2));

 		/*
		 GRAHP:
		        (B)---8---(C)---7---(D)
		       / |        /  \       | \
		     4   |      2     \      |  9
		   /     |     /       \     |    \
		 (A)     11  (I)        4    14   (E)
		   \     |   /  \        \   |    /
		     8   |  7    6        \  |  10
		      \  | /      \        \ | /
		        (H)---1---(G)---2---(F)
		 
		        
		 MST:
		        (B)---8---(C)---7---(D)
		       /          /  \         \
		     4          2     \         9
		   /           /       \          \
		 (A)         (I)        4         (E)
		                         \
		                          \
		                           \ 
		        (H)---1---(G)---2---(F)
		        
		 */

		return graph;
	}

	/**
	 * Does not handle Graphs of 1 or fewer nodes
	 * 
	 */
	public Graph kruskal(Graph g) {
		boolean add = false;
		Graph mst = new Graph(); // initialize graph
		Vector edges = g.getEdges(); // get edges

		Collections.sort(edges); // sort edges

		// find mst
		for (int i = 0; i < edges.size(); ++i) {
			Edge edge = (Edge) edges.get(i); // get edge
			KNode[] ends = edge.getEnds(); // get ends
			if (!mst.containsNode(ends[0])) {
				mst.addNode(ends[0]); // check for first end
				add = true;
			}
			if (!mst.containsNode(ends[1])) {
				mst.addNode(ends[1]); // check other end
				add = true;
			}

			if (add) // if either end is new add edge
			{
				mst.addEdge(edge);
				add = false;
			} else // ensure trees are connected if nodes are contained
			{
				if (!mst.pathBetween(ends))
					mst.addEdge(edge);
			}
		}

		return mst;
	}
}

class Graph {
	Vector nodes;
	Vector edges;

	public Graph() {
		nodes = new Vector();
		edges = new Vector();
	}

	public Vector getNodes() {
		return nodes;
	}

	public Vector getEdges() {
		return edges;
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public void addNode(KNode n) {
		nodes.add(n);
	}

	public KNode getNode(Object o) {
		for (int i = 0; i < nodes.size(); ++i) {
			if (((KNode) nodes.get(i)).getValue().equals(o))
				return (KNode) nodes.get(i);
		}
		return null;
	}

	public boolean containsNode(KNode n) {
		for (int i = 0; i < nodes.size(); ++i) {
			if (((KNode) nodes.get(i)).getValue().equals(n.getValue()))
				return true;
		}
		return false;
	}

	public Vector getEdges(KNode n) {
		Vector edgeSet = new Vector();

		for (int i = 0; i < edges.size(); ++i) {
			if (((Edge) edges.get(i)).connectsNode(n))
				edgeSet.add((Edge) edges.get(i));
		}
		return edgeSet;
	}

	public String printEdges(KNode n) {
		Vector edgeSet = new Vector();

		for (int i = 0; i < edges.size(); ++i) {
			if (((Edge) edges.get(i)).connectsNode(n))
				edgeSet.add((Edge) edges.get(i));
		}

		if (edgeSet.size() == 0)
			return "-none-";

		String value = "";
		for (int i = 0; i < edgeSet.size(); ++i) {
			Edge e = (Edge) edgeSet.get(i);
			value += n + "" + e + "" + e.otherEnd(n) + "\n";
		}
		return value;
	}

	public boolean pathBetween(KNode[] ends) {
		Set nodes = pathBetween(ends[0], edges.size()); // get all nodes this
														// node can visit

		Iterator it = nodes.iterator();

		while (it.hasNext()) {
			KNode[] n = ((Edge) it.next()).getEnds();
			if ((n[0].getValue().equals(ends[1].getValue()))
					|| (n[1].getValue().equals(ends[1].getValue())))
				return true;
		}
		return false;
	}

	private Set pathBetween(KNode start, int count) {
		if (count == 0)
			return new HashSet();

		Set nodes = new HashSet();

		Vector edgeSet = getEdges(start); // next nodes to check
		nodes.addAll(edgeSet);

		for (int i = 0; i < edgeSet.size(); ++i) {
			nodes.addAll(pathBetween(((Edge) edgeSet.get(i)).otherEnd(start),
					count - 1));
		}
		return nodes;
	}

	public void draw() {
		for (int i = 0; i < nodes.size(); ++i) {
			KNode n = (KNode) nodes.get(i);
			System.out.println(n);
			System.out.println(printEdges(n));
		}
	}
}

class KNode {
	private Object value;

	public KNode() {
		value = null;
	}

	public KNode(Object o) {
		value = o;
	}

	public Object getValue() {
		return value;
	}

	public String getRep() {
		return value.toString();
	}

	public String toString() {
		return "(" + value + ")";
	}
}

class Edge implements Comparable {
	private KNode node1, node2;
	private int weight;

	public Edge() {
	}

	public Edge(KNode n1, KNode n2, int w) {
		node1 = n1;
		node2 = n2;
		weight = w;
	}

	public int getWeight() {
		return weight;
	}

	public KNode otherEnd(KNode n) {
		if (n == null)
			return null; // do not handle this

		if (node1 == n && node2 != n) {
			return node2;
		} else if (node2 == n && node1 != n) {
			return node1;
		} else {
			return null;
		}
	}

	public KNode[] getEnds() {
		return new KNode[] { node1, node2 };
	}

	public int compareTo(Object o2) {
		Edge e2 = (Edge) o2;

		if (this.getWeight() < e2.getWeight())
			return -1;
		else if (this.getWeight() > e2.getWeight())
			return 1;
		else if (this.getWeight() == e2.getWeight())
			return 0;
		return -2;
	}

	public boolean connectsNode(KNode n) {
		return (n == node1) || (n == node2);
	}

	public String toString() {
		return "---" + weight + "---";
	}
}
