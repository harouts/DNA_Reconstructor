import java.awt.Dimension;
import java.util.Scanner;
import java.util.Stack;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;

public class Reconstructor {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);

		String sequence = sc.next();
		int k = 3;

		Stack<String> edgeStack = fragment(sequence, k);
		DirectedPseudograph<String, DefaultEdge> graph = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);

		//Populate graph
		while (!edgeStack.isEmpty()) {
			String temp = edgeStack.pop();
			String v1 = temp.substring(0, k - 1);
			String v2 = temp.substring(1, k);
			graph.addVertex(v1);
			graph.addVertex(v2);
			graph.addEdge(v1, v2);
		}
		System.out.println("Vertices: " + graph.vertexSet());
		System.out.println("Edges: " + graph.edgeSet());

		System.out.println("Path: " + findEulerPath(graph));


	}
	
	public static Stack<String> fragment(String s, int k){
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i <= s.length() - k; i++) {
			stack.push(s.substring(i, i+k));
		}
		
		return stack;
	}
	
	private static String findEulerPath(DirectedPseudograph<String, DefaultEdge> graph) {
		Stack<String> stack = new Stack<String>();
		Stack<String> pathStack = new Stack<String>();
		String currentVertex = "";
		String temp = "";
		StringBuilder finalPath = new StringBuilder();
		for (String s: graph.vertexSet()) {
			if (graph.outDegreeOf(s) - graph.inDegreeOf(s) == 1) {
				currentVertex = s;
			}
		}
		
		//System.out.println("Outside of while loop, Current vertex: " + currentVertex);
		
		while (!stack.isEmpty() || graph.outDegreeOf(currentVertex) != 0) {
			if (graph.outDegreeOf(currentVertex) != 0) {
				//sets temp to one of currentVertex's neighbors
				//System.out.println("edges of current vertex : " + graph.edgesOf(currentVertex));
				temp = graph.getEdgeTarget(graph.outgoingEdgesOf(currentVertex).iterator().next());
				//System.out.println("in while loop temp vertex: " + temp);
				stack.push(currentVertex);
				//System.out.println("in while loop Current vertex: " + currentVertex);
				graph.removeEdge(currentVertex, temp);
				currentVertex = temp;
				//System.out.println("in while loop current should be temp Current vertex: " + currentVertex);
			} else {
				pathStack.push(currentVertex);
				//System.out.println(currentVertex);
				currentVertex = stack.pop();
			}
		}
		pathStack.push(currentVertex);
		
		finalPath.append(pathStack.pop());
		while (!pathStack.isEmpty()) {
			finalPath.append(pathStack.pop().charAt(1));
		}
		return finalPath.toString();
	}

}
