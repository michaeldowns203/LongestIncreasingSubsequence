
public class TopoSort {
	private int N;
	private boolean[] marked;
	private int[] ordering;
	private int i;
	
	public TopoSort(Digraph g) {
		this.N = g.V();
		marked = new boolean[N];
		ordering = new int[N];
		this.i = N - 1;
	}
	
	public void sort(Digraph g) {
		for (int j = 0; j < N; j++) {
			if (marked[j] == false) {
				int[] visited = new int[N];
				
			}
		}
	}
	
	private void dfs(Digraph G, int v) {
        marked[v] = true;

        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }
}
