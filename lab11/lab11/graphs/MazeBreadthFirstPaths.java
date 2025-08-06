package lab11.graphs;

import java.util.ArrayDeque;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s, e;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = m.xyTo1D(sourceX, sourceY);
        e = m.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        boolean targetFind = false;
        ArrayDeque<Integer> nextNodes = new ArrayDeque<>();
        nextNodes.addLast(s);
        while (!targetFind) {
            int takenNode = nextNodes.removeFirst();
            /*  add adjacent nodes in our array deque if this node hasn't been visited  */
            for (int adjN : maze.adj(takenNode)) {
                if (!marked[adjN]) {
                    marked[adjN] = true;
                    edgeTo[adjN] = takenNode;
                    distTo[adjN] = distTo[takenNode] + 1;
                    announce();
                    nextNodes.addLast(adjN);
                    if (adjN == e) {
                        targetFind = true;
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

