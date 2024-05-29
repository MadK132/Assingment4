import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeightedGraph<Vertex> {
    private boolean undirected;
    private Map<Vertex,Map<Vertex,Double>> map = new HashMap<>();
    public WeightedGraph() {
        this(true);
    }
    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex v) {
        map.put(v, new HashMap<>());
    }

    public void addEdge(Vertex source, Vertex destination, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(destination))
            addVertex(destination);

        if (hasEdge(source, destination) || source.equals(destination))
            return;

        map.get(source).put(destination,weight);
        map.get(destination).put(source,weight);
    }
    private boolean hasVertex(Vertex source) {
        return map.containsKey(source);
    }
    private boolean hasEdge(Vertex source, Vertex destination) {
        if (!hasVertex(source)) return false;
        return map.get(source).containsKey(destination);
    }

    public Set<Vertex> getNeighbours(Vertex vertex) {
        if (!hasVertex(vertex))
            throw new IndexOutOfBoundsException("Vertex doesn't exist");
        return map.get(vertex).keySet();
    }
    public double getEdgeWeight(Vertex source, Vertex destination) {
        if (!hasEdge(source, destination))
            throw new IllegalArgumentException("Edge doesn't exist");
        return map.get(source).get(destination);
    }
    public void printGraph() {
        for (Vertex vertex: map.keySet()) {
            System.out.printf("Vertex %s connected to %s\n", vertex, map.get(vertex));
        }
    }

    private void checkVertex(Vertex vertex) {
        if(!hasVertex(vertex)) throw new IndexOutOfBoundsException("Vertex doesn't exist");
    }
    public void removeVertex(Vertex vertex) {
        checkVertex(vertex);

        for (Vertex local : map.keySet()) {
            map.get(local).remove(vertex);
        }
        map.remove(vertex);
    }
    public void removeEdge(Vertex source, Vertex destination) {
        checkVertex(source);
        checkVertex(destination);

        map.get(source).remove(destination);
        map.get(destination).remove(source);
    }
}