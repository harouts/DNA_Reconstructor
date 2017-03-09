import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class Graph {
	
	private int[][] adjMat;
	private Map<Vertex, Integer> vertexMap;
	private Map<Integer, Vertex> inverseVertexMap;
	private List<Vertex> oddVertices;
	
	public Graph(List<Vertex> vertices, List<Edge> edges) {
		this.vertexMap = new HashMap<Vertex, Integer>();
		int size = vertices.size();
		adjMat = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			this.vertexMap.put(vertices.get(i), i);
			this.inverseVertexMap.put(i, vertices.get(i));
		}
		
		for (Edge e: edges) {
			int from = vertexMap.get(e.from);
			int to = vertexMap.get(e.to);
			adjMat[from][to] += 1;
		}
	}
	
	public void degreeSetter() {
		int outDegree = 0;
		int inDegree = 0;
		for (int i = 0; i < adjMat[0].length; i++) {
			outDegree = IntStream.of(adjMat[0]).sum();
			
		}
	}
	
	public String findEulerPath(Graph g) {
		return null;
	}
	
	public void printAdjMat() {
		System.out.println(Arrays.deepToString(adjMat));
	}
}
