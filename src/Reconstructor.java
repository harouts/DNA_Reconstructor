import java.awt.Dimension;
import java.util.Stack;

import javax.swing.JApplet;
import javax.swing.JFrame;

import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedPseudograph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;


public class Reconstructor extends JApplet {
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
	private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

	public static void main(String[] args) throws InterruptedException {
		String sequence = "ATGTGCCGCA";
		int k = 3;
		
		Stack<String> edgeStack = fragment(sequence, k);
		DirectedPseudograph<String, DefaultEdge> graph = new DirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
		
		//Populate graph
		while (!edgeStack.isEmpty()) {
			String temp = edgeStack.pop();
			String v1 = temp.substring(0, k-1);
			String v2 = temp.substring(1, k);
			graph.addVertex(v1);
			graph.addVertex(v2);
			graph.addEdge(v1, v2);
		}
		
		//Visualization
		Reconstructor applet = new Reconstructor();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
		
		
		
		
		System.out.println("Vertices: " + graph.vertexSet());
		System.out.println("Edges: " + graph.edgeSet());
		
		System.out.println("Path: " + findEulerPath(graph));
		
		
	}
	
	public void init(DirectedPseudograph<String, DefaultEdge> graph) {
		// create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(graph);

        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);
        
     // positioning via jgraphx layouts
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());
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
		
		System.out.println("Outside of while loop, Current vertex: " + currentVertex);
		
		while (!stack.isEmpty() || graph.outDegreeOf(currentVertex) != 0) {
			if (graph.outDegreeOf(currentVertex) != 0) {
				//sets temp to one of currentVertex's neighbors
				System.out.println("edges of current vertex : " + graph.edgesOf(currentVertex));
				temp = graph.getEdgeTarget(graph.outgoingEdgesOf(currentVertex).iterator().next());
				System.out.println("in while loop temp vertex: " + temp);
				stack.push(currentVertex);
				System.out.println("in while loop Current vertex: " + currentVertex);
				graph.removeEdge(currentVertex, temp);
				currentVertex = temp;
				System.out.println("in while loop current should be temp Current vertex: " + currentVertex);
			} else {
				pathStack.push(currentVertex);
				System.out.println(currentVertex);
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
