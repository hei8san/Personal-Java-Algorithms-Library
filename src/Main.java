import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


    }

//    public static int gridTraveler(int rowNum, int colNum){
//
//    }

   //iterative way
    public static long fib(int n) {
        if (n <= 2) return 1;
        long[] arr = new long[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n - 1];
    }
    //memoization
    public static long fib(long n, Map<Long, Long> memo){
        if(memo.containsKey(n)) return memo.get(n);
        if (n <= 2) return 1;
        memo.put(n, (fib(n - 1, memo) + fib(n - 2, memo)));
        return memo.get(n);
    }


}

class Grid {
    char[][] grid;

    public Grid(int rowNum, int colNum) {
        this.grid = new char[rowNum][colNum];
    }

    public int minimumIsland(char[][] grid, int startRow, int startCol) {

        if (startRow < 0 || startRow >= this.grid.length || startCol < 0 || startCol >= this.grid[0].length) return 0;
        if (this.grid[startRow][startCol] == 'W' || this.grid[startRow][startCol] == 'V') return 0;
        this.grid[startRow][startCol] = 'V';
        int size = 1;
        size += minimumIsland(this.grid, startRow, startCol - 1);
        size += minimumIsland(this.grid, startRow - 1, startCol);
        size += minimumIsland(this.grid, startRow, startCol + 1);
        size += minimumIsland(this.grid, startRow + 1, startCol);
        return size;

    }

    public boolean countIsland(char[][] grid, int startRow, int startCol) {
        if (startRow < 0 || startRow >= this.grid.length || startCol < 0 || startCol >= this.grid[0].length)
            return false;
        if (this.grid[startRow][startCol] == 'W' || this.grid[startRow][startCol] == 'V') return false;
        this.grid[startRow][startCol] = 'V';
        countIsland(this.grid, startRow, startCol - 1);
        countIsland(this.grid, startRow - 1, startCol);
        countIsland(this.grid, startRow, startCol + 1);
        countIsland(this.grid, startRow + 1, startCol);
        return true;

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
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        if (distance != node.distance) return false;
        return Objects.equals(vertex, node.vertex);
    }

    @Override
    public int hashCode() {
        int result = vertex != null ? vertex.hashCode() : 0;
        result = 31 * result + distance;
        return result;
    }
}

