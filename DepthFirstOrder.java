import java.util.Queue;
import java.util.Stack;

//Code taken from Algorithms by Sedgewick and Wayne, I edited portions of it

public class DepthFirstOrder {
    private boolean[] marked;
    private int[] pre;
    private int[] post;
    private int[] reversePost;  // vertices in reverse postorder
    private int preCounter;
    private int postCounter;
    private int reversePostCounter;

    public DepthFirstOrder(Digraph G) {
        int V = G.V();
        pre = new int[V];
        post = new int[V];
        reversePost = new int[V];
        preCounter = 0;
        postCounter = 0;
        reversePostCounter = 0;

        for (int v = 0; v < V; v++)
                dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
    	boolean[] marked = new boolean[G.V()];
        marked[v] = true;
        int count = 0;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
            	count++;
                dfs(G, w);
            }
        }
        reversePost[v] = count;
    }

    public int[] pre() {
        return pre;
    }

    public int[] post() {
        return post;
    }

    public int[] reversePost() {
        return reversePost;
    }
}
