package ch.fhnw.algd2.graphs;

import java.util.ArrayList;

public class Graph implements IGraph {

	private int load;
	private Node[] nodearray;

	public Graph(int size) {
		nodearray = new Node[size];
		this.load = 0;
	}

	@Override
	public int appendNode() {
		nodearray[load] = new Node(load);
		load++;
		return load;
	}

	@Override
	public int getNumberOfNodes() {
		return load;
	}

	@Override
	public void addEdge(int source, int dest, int weight) {

	}

	@Override
	public void addUndirectedEdge(int n1, int n2, int weight) {
		// TODO Auto-generated method stub

	}

	@Override
	public void markNode(int node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unmarkNode(int node) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isMarked(int node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetAllMarkers() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNeighbour(int node, int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWeight(int node, int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfNeighbours(int node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void show() {

	}

	protected class Node {
		protected int key;
		protected ArrayList<Edge> edges;
		protected boolean mark;

		protected Node(int key) {
			this.key = key;
			this.edges = new ArrayList<Edge>();
			this.mark = false;
		}
	}

	protected class Edge {
		protected Node source;
		protected Node target;
		protected int weight;
		protected boolean isDirected;

		protected Edge(Node a, Node b, int w, boolean d) {
			source = a;
			target = b;
			weight = w;
			isDirected = d;
		}
	}

}
