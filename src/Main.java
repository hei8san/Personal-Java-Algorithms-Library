import java.util.*;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Graph<Character> graph = new Graph<>();
        graph.addEdge('w', 'x');
        graph.addEdge('x', 'y');
        graph.addEdge('z', 'y');
        graph.addEdge('z', 'v');
        graph.addEdge('w', 'v');

        

    }

}

class Graph<T> {
    public Map<T, List<T>> adjList;
    public Set<T> visited;

    public Graph() {
        adjList = new HashMap<>();
        visited = new HashSet<>();
    }

    public void addEdge(T node1, T node2) {
        adjList.putIfAbsent(node1, new ArrayList<>());
        adjList.get(node1).add(node2);

        adjList.putIfAbsent(node2, new ArrayList<>());
        adjList.get(node2).add(node1);
    }

    public void makeVisited(T node) {
        this.visited.add(node);
    }

    public void addVertex(T vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public boolean hasPath(Graph<T> graph, T src, T dst) {
        if (src.equals(dst)) return true;
        if (graph.visited.contains(src)) return false;
        graph.makeVisited(src);
        for (T neighbors : graph.adjList.get(src)) {
            if (hasPath(graph, neighbors, dst)) {
                return true;
            }
        }
        return false;
    }

    public boolean explore(Graph<T> graph, T current) {
        if (graph.visited.contains(current)) return false;
        graph.makeVisited(current);
        for (T neighbors : graph.adjList.get(current)) {
            explore(graph, neighbors);
        }
        return true;

    }

    public int largestGraph(Graph<T> graph, T current) {
        if (graph.visited.contains(current)) return 0;
        graph.makeVisited(current);
        int size = 1;

        for (T neighbors : graph.adjList.get(current)) {
            size += largestGraph(graph, neighbors);
        }
        return size;

    }


    public int shortestPath(Graph<T> graph, T start, T end) {
        Queue<Node<T>> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        queue.add(new Node<>(start, 0));

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.remove();
            if (currentNode.vertex == end) {
                return currentNode.distance;
            }
            for (T neighbor : graph.adjList.get(currentNode.vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new Node<>(neighbor, currentNode.distance + 1));
                }
            }

        }
        return -1; // return -1 if no path found

    }


}

class Node<T> {
    T vertex;
    int distance;

    public Node(T vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if(distance != node.distance) return false;
        return Objects.equals(vertex, node.vertex);
    }

    @Override
    public int hashCode() {
        int result = vertex != null ? vertex.hashCode() : 0;
        result = 31 * result + distance;
        return result;
    }
}

