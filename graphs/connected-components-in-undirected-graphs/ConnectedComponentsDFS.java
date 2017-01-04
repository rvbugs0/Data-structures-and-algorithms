import java.util.Scanner;
class ConnectedComponentsDFS
{
	public static boolean[][] visited;
	public static void main(String gg[])
	{
		int a[][]={	{0,0,0,1,1,0,0,0,1,1},
					{0,0,0,1,1,0,0,0,1,1},
					{0,0,1,1,1,0,0,0,1,1},
					{1,0,0,0,0,0,0,0,0,0},
					{1,1,1,1,1,1,0,0,0,0}	};
		int rows = a.length;
		int cols = a[0].length;
		visited=new boolean[rows][cols];
		ConnectedComponentsDFS dfs=new ConnectedComponentsDFS();

		System.out.println(dfs.countConnectedComponents(a,rows,cols));
	}

	public static int countConnectedComponents(int [][] grid,int M,int N)
	{
		int count=0;
		for(int i=0;i<M;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(!visited[i][j] && grid[i][j]!=0) 
				{
					traverseZone(i,j,M,N,grid);
					count++;
				}

			}
		}
	return count;
	}


	public static void traverseZone(int i,int j,int M,int N,int[][] grid)
	{
		if(i<0 || j<0 || i>=M || j>=N) return;
		if(visited[i][j]) return;
		visited[i][j]=true;
		if(grid[i][j]==0) return;
		traverseZone(i,j-1,M,N,grid);
		traverseZone(i,j+1,M,N,grid);
		traverseZone(i-1,j,M,N,grid);
		traverseZone(i+1,j,M,N,grid);
		traverseZone(i-1,j-1,M,N,grid);
		traverseZone(i-1,j+1,M,N,grid);
		traverseZone(i+1,j-1,M,N,grid);
		traverseZone(i+1,j+1,M,N,grid);
	}
}