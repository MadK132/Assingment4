import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DijkstraSearch<Vertex> extends Search<Vertex> {
    private final Set<Vertex> unsettledNodes;
    private Map<Vertex, Double> distances;
    private final WeightedGraph<Vertex> graph;

    public DijkstraSearch(WeightedGraph<Vertex> graph, Vertex source) {
        super(source);
        this.graph = graph;
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();
        dijkstra();
    }

    public void dijkstra() {
        distances.put(source, 0.0);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex currentNode = getVertexWithMinimumWeight(unsettledNodes);
            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (Vertex neighbor : graph.getNeighbours(currentNode)) {
                double newDistance = getShortestDistance(currentNode) + graph.getEdgeWeight(currentNode, neighbor);

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private Vertex getVertexWithMinimumWeight(Set<Vertex> vertices) {
        Vertex min = null;
        for (Vertex vertex : vertices) {
            if (min == null || getShortestDistance(vertex) < getShortestDistance(min)) {
                min = vertex;
            }
        }
        return min;
    }

    private double getShortestDistance(Vertex destination) {
        return distances.getOrDefault(destination, Double.MAX_VALUE);
    }
}