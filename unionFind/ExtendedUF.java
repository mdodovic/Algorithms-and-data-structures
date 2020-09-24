package unionFind;

public class ExtendedUF extends UnionFind {

	int[] id;
	int[] sz;
	int[] max;

	public ExtendedUF(int N) {
		id = new int[N];
		sz = new int[N];
		max = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
			max[i] = i;
			sz[i] = 1;
		}
	}
	
	private int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j)
			return;
		if(sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
			max[j] = Math.max(max[i], max[j]);
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
			max[i] = Math.max(max[i], max[j]);
		}
	}
	
	public int find(int i) {
		return max[root(i)];
	}
	
}
