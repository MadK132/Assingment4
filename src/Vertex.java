import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjVertices;
    public Vertex(V data) {
        this.data = data;
        adjVertices = new HashMap<>();
    }
    public void addAdjVert(Vertex<V> destination, double weight) {
        adjVertices.put(destination,weight);
    }
    public V getData() {
        return data;
    }
    public Map<Vertex<V>,Double> getAdjVert() {
        return adjVertices;
    }
    public boolean hasAdjVert(Vertex<V> destination) {
        return adjVertices.containsKey(destination);
    }
    public double getWeightTo(Vertex<V> destination) {
        return adjVertices.get(destination);
    }
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}

