public class DepthFirstSearch<Vertex> extends Search<Vertex> {
    public DepthFirstSearch(WeightedGraph<Vertex> graph, Vertex source) {
        super(source);
        DFS(graph, source);
    }
    private void DFS(WeightedGraph<Vertex> graph, Vertex current) {
        marked.add(current);
        for (Vertex v : graph.getNeighbours(current)) {
            if (!marked.contains(v)) {
                edgeTo.put(v, current);
                DFS(graph, v);
            }
        }
    }
}