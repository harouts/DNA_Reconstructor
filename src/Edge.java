
public class Edge {
	public Vertex from;
	public Vertex to;
	public String data;
	
	public Edge(Vertex from, Vertex to) {
		this.from = from;
		this.to = to;
		this.data = from.data + "-" + to.data;
	}
	
	public void getEdgeInfo() {
		System.out.println("Edge: " + this.data + "\nFrom: " + this.from.data + "\nTo: " + this.to.data + "\n");
	}

}
