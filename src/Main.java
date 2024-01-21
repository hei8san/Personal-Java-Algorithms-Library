import java.util.*;

public class Main {
    public static char[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfApartments = sc.nextInt();
        int width = sc.nextInt();
        int length = sc.nextInt();
        int height = sc.nextInt();
        int area = sc.nextInt();
        int m = sc.nextInt();

        int emptySpace = 0;
        for(int i = 0 ; i < m; i++){
            int widthDoor = sc.nextInt();
            int heightDoor = sc.nextInt();
            emptySpace += widthDoor*heightDoor;
        }
        int spaceForRoom = width*height*2+width*length*2+length*height;
        long sum = (long) numOfApartments * spaceForRoom;
        sum -= emptySpace;
        if(sum % area == 0){
            System.out.println(sum/area);
            return;
        }
        System.out.println(sum/area+1);


    }

    public static int solution(int row, int col, char[][] grid) {
        if (row < 0 || col < 0 || row > grid.length - 1 || col > grid[0].length - 1) return 0;
        if (grid[row][col] == '.') return 0;
        grid[row][col] = '.';
        int size = 1;
        size += solution(row - 1, col - 1, grid);
        size += solution(row - 1, col, grid);
        size += solution(row - 1, col + 1, grid);
        size += solution(row, col + 1, grid);
        size += solution(row+1, col + 1, grid);
        size += solution(row + 1, col, grid);
        size += solution(row + 1, col - 1, grid);
        size += solution(row, col - 1, grid);

//        System.out.println(Arrays.deepToString(grid));
        return size;

    }


    public static ArrayList<Integer> bestSum(int target, int[] array) {
        if (target < 0) return null;
        if (target == 0) return new ArrayList<>();

        ArrayList<Integer> shortestCombination = null;
        for (int i : array) {
            int remain = target - i;
            ArrayList<Integer> remainders = bestSum(remain, array);
            if (remainders != null) {
                remainders.add(i);
                if (shortestCombination == null || remainders.size() < shortestCombination.size()) {
                    if (shortestCombination != null) {
                        shortestCombination.clear();
                    }
                    shortestCombination = new ArrayList<>();
                    shortestCombination.addAll(remainders);
                }
            }
        }
        return shortestCombination;
    }

    public static ArrayList<Integer> howSum(int target, int[] array, Map<Integer, ArrayList<Integer>> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target == 0) return new ArrayList<>();
        if (target < 0) return null;

        for (int i : array) {
            int remain = target - i;
            ArrayList<Integer> result = howSum(remain, array, memo);
            if (result != null) {
                result.add(i);
                memo.put(target, result);
                return result;
            }
        }
        memo.put(target, null);
        return null;
    }

    public static boolean canSum(int target, int[] array, Map<Integer, Boolean> memo) {
        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == 0) return true;
        if (target < 0) return false;

        for (int i : array) {
            int remainder = target - i;
            if (canSum(remainder, array, memo)) {
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
    }

//    public static int solution() {
//
//    }


    public static Long gridTraveler(int m, int n, Map<Pair, Long> pairIntegerMap) {
        Pair key = new Pair(m, n);
        if (pairIntegerMap.containsKey(key)) return pairIntegerMap.get(key);
        if (m == 1 && n == 1) return 1L;
        if (m == 0 || n == 0) return 0L;
        pairIntegerMap.put(key, gridTraveler(m - 1, n, pairIntegerMap) + gridTraveler(m, n - 1, pairIntegerMap));
        return pairIntegerMap.get(key);
    }


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
    public static long fib(long n, Map<Long, Long> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 2) return 1;
        memo.put(n, (fib(n - 1, memo) + fib(n - 2, memo)));
        return memo.get(n);
    }


}

class Point {
    public int x; // The x coordinate
    public int y; // The y coordinate

    // Constructor to initialize the point
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method to return a string representation of the point
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}


class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
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

    public void addUndirectedEdge(T node1, T node2) {
        adjList.putIfAbsent(node1, new ArrayList<>());
        adjList.get(node1).add(node2);

        adjList.putIfAbsent(node2, new ArrayList<>());
        adjList.get(node2).add(node1);
    }

    public void addDirectedEdge(T src, T dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.get(src).add(dest);
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

