package unionFind;

public class Main {
	
	public static void algorithm(UnionFind uf, int[][] input) {		
		for(int i = 0; i < input.length; i++) {
			int p = input[i][0];
			int q = input[i][1];
			if (!uf.connected(p, q))
			{
				uf.union(p, q);
				// System.out.println(p + " " + q);
			}
			else {
			    // System.out.println(p + " and " + q + " are in the same component");
			}
		}		
		
	}
	
	public static void main(String[] args) {
		int N = 10000;
		
		QuickFindUF ufQF = new QuickFindUF(N);
		QuickUnionUF ufQU = new QuickUnionUF(N);
		WeightedQuickUnionUF ufWQU = new WeightedQuickUnionUF(N);
		WQUPC ufWQUPC = new WQUPC(N);
		
		int[][] input = new int[N][2];
		
		for(int i = 0; i < N; i++){
			int a;
			int b;
			while(true) {
				a = (int)(N * Math.random());
				b = (int)(N * Math.random());
				if(a != b)
					break;
			}
			input[i][0] = a;
			input[i][1] = b;
		}
		long timeA, timeB;
		timeA = System.currentTimeMillis();		
		algorithm(ufQF, input);
		timeB = System.currentTimeMillis();
		System.out.println("Quick find algorithm time: " + (timeB - timeA));

		timeA = System.currentTimeMillis();		
		algorithm(ufQU, input);
		timeB = System.currentTimeMillis();
		System.out.println("Quick union algorithm time: " + (timeB - timeA));		
		
		timeA = System.currentTimeMillis();		
		algorithm(ufWQU, input);
		timeB = System.currentTimeMillis();
		System.out.println("Weighted quick uind algorithm time: " + (timeB - timeA));
		
		timeA = System.currentTimeMillis();		
		algorithm(ufWQUPC, input);
		timeB = System.currentTimeMillis();
		System.out.println("Weighted quick union with path comparison algorithm time: " + (timeB - timeA));

		N = 10;
		ExtendedUF uf = new ExtendedUF(N);
		input = new int[][] {{4, 3}, {3, 8}, {6, 5}, 
							 {9, 4}, {2, 1}, {8, 9},
							 {5, 0}, {7, 2}, {6, 1}, 
							 {1, 0}, {6, 7}};
		for(int i = 0; i < input.length; i++) {
			int p = input[i][0];
			int q = input[i][1];
			
			if (!uf.connected(p, q))
			{
				uf.union(p, q);
				System.out.println(p + " " + q);
			}
			else {
				System.out.println(p + " and " + q + " are in the same component");
			}
		}
		
		for(int element = 0; element < N; element++)
			System.out.print(uf.find(element) + " ");
			
	}
}
