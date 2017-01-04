class TopologicalSort
{

	public static int current_label;
	public static boolean[][] visited;
	public static void main(String gg[])
	{
		int adj[][]={	{0,1,1,0},
						{0,0,0,1},
						{0,0,0,1},
						{0,0,0,0}};
		int v=adj.length;
		visited=new boolean[v][adj[0].length];
		int[] ordering=new int[v];
		TopologicalSort ts=new TopologicalSort();
		ts.order(adj,ordering);
		System.out.print("Topological order of vertices is ");
		ts.printArray(ordering);
	}

	public void printArray(int a[])
	{
		int s=a.length;
		for(int i=0;i<s;i++) System.out.print(a[i]+" ");
		System.out.println();
	}

	public void order(int adj[][],int[] ordering)
	{
		current_label=adj.length;
		for(int i=0;i<adj.length;i++)
		{
			if(!visited[i][i])
			{
				DFS(adj,i,ordering);
			}
		} 

	}

	public void DFS(int adj[][],int s,int[] ordering)
	{
		visited[s][s]=true;
		for(int m=0;m<adj[s].length;m++)
		{
			if(adj[s][m]!=0 && !visited[m][m])
			{
				
				DFS(adj,m,ordering);
			}
		}
		ordering[s]=current_label;
		current_label=current_label-1;
	}


}