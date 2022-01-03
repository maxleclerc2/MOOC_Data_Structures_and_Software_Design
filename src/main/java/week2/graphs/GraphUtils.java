package week2.graphs;

import java.util.*;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null || src == null || dest == null) return -1;
		if (!graph.containsElement(src) || !graph.containsElement(dest)) return -1;

		Node start = graph.getNode(src);
		Set<Node> marked = new HashSet<Node>();
		int dist = 0;

		if (start.getElement().equals(dest)) {
			return dist;
		}

		Queue<Node> toExplore = new LinkedList<Node>();
		marked.add(start);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			dist++;
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.contains(neighbor)) {
					if (neighbor.getElement().equals(dest)) {
						return dist;
					}
					marked.add(neighbor);
					toExplore.add(neighbor);
				}
			}
		}

		return -1;
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		if (graph == null || src == null || distance < 1) return null;
		if (!graph.containsElement(src)) return null;

		HashSet<String> set = new HashSet<>();

		Node start = graph.getNode(src);
		Queue<Node> toExplore = new LinkedList<Node>();
		toExplore.add(start);

		while (distance > 0) {
			distance--;
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!set.contains(neighbor.getElement())) {
					if (neighbor != start) {
						set.add(neighbor.getElement());
						toExplore.add(neighbor);
					}
				}
			}
		}

		return set;
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/* IMPLEMENT THIS METHOD! */
		
		return true; // this line is here only so this code will compile if you don't modify it
	}
	
}
