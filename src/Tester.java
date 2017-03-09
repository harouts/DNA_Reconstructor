import java.util.ArrayList;
import java.util.List;


public class Tester {

	public static void main(String[] args) throws Exception {
	
		Vertex A = new Vertex("A");
		Vertex B = new Vertex("B");
		Vertex C = new Vertex("C");
		
		Edge AB = new Edge(A, B);
		
		Edge BC = new Edge(B, C);
		
		AB.getEdgeInfo();
		
		BC.getEdgeInfo();
		
		List edgeList = new ArrayList<Edge>();
		
		List vertexList = new ArrayList<Vertex>();
		
		edgeList.add(AB);
		edgeList.add(BC);
		
		vertexList.add(A);
		vertexList.add(B);
		vertexList.add(C);
		
		Graph myGraph = new Graph(vertexList, edgeList);
		
		myGraph.printAdjMat();

	}

}
