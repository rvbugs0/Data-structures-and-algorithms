import java.util.ArrayList;
class ConnectedComponentsInUndirectedGraphs
{
	public static void main(String gg[])
	{
		int a[][]={	{0,0,0,1,1,0,0,0,1,1},
					{0,0,0,1,1,0,0,0,1,1},
					{0,0,1,1,1,0,0,0,1,1},
					{1,0,0,0,0,0,0,0,0,0},
					{1,1,1,1,1,1,0,0,0,0}	};
		int rows = a.length;
		int cols = a[0].length;
		ConnectedComponentsInUndirectedGraphs ccug=new ConnectedComponentsInUndirectedGraphs();
		System.out.println(ccug.countConnectedComponents(a,rows,cols));
	}

	class Pair
	{
		public int i,j;
		public Pair(int i,int j)
		{
			this.i=i;
			this.j=j;
		}		
	}

	public int countConnectedComponents(int[][] a,int rows, int cols)
	{
		boolean[][] visited=new boolean[rows][cols];
		int count=0;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				if(!visited[i][j] && a[i][j]!=0)
				{
					count++;
					Pair pair=new Pair(i,j);
					visited[i][j]=true;
					ArrayList<Pair> queue=new ArrayList<>();
					queue.add(pair);
					while(!queue.isEmpty())
					{
						Pair t=queue.remove(0);
						int x=t.i;
						int y=t.j;
						//left
						if(x<rows && x>=0 && y-1<cols && y-1>=0 && !visited[x][y-1] && a[x][y-1]!=0)
						{
							visited[x][y-1]=true;
							queue.add(new Pair(x,y-1));	
						} 
						//right
						if(x<rows && x>=0 && y+1<cols && y+1>=0 && !visited[x][y+1] && a[x][y+1]!=0)
						{
							visited[x][y+1]=true;
							queue.add(new Pair(x,y+1));
						} 
						//up
						if(x-1<rows && x-1>=0 && y<cols && y>=0 && !visited[x-1][y] && a[x-1][y]!=0)
						{
							visited[x-1][y]=true;
							queue.add(new Pair(x-1,y));
						} 
						//down
						if(x+1<rows && x+1>=0 && y<cols && y>=0 && !visited[x+1][y] && a[x+1][y]!=0)
						{
							visited[x+1][y]=true;
							queue.add(new Pair(x+1,y));
						} 
						//up left
						if(x-1<rows && x-1>=0 && y-1<cols && y-1>=0 && !visited[x-1][y-1] && a[x-1][y-1]!=0)
						{
							visited[x-1][y-1]=true;
							queue.add(new Pair(x-1,y-1));
						} 
						//up right
						if(x-1<rows && x-1>=0 && y+1<cols && y+1>=0 && !visited[x-1][y+1] && a[x-1][y+1]!=0)
						{
							visited[x-1][y+1]=true;
							queue.add(new Pair(x-1,y+1));
						} 
						//down left
						if(x+1<rows && x+1>=0 && y-1<cols && y-1>=0 && !visited[x+1][y-1] && a[x+1][y-1]!=0)
						{
							visited[x+1][y-1]=true;
							queue.add(new Pair(x+1,y-1));
						} 
						//up right
						if(x+1<rows && x+1>=0 && y+1<cols && y+1>=0 && !visited[x+1][y+1] && a[x+1][y+1]!=0)
						{
							visited[x+1][y+1]=true;
							queue.add(new Pair(x+1,y+1));
						} 
					}


				}
			}
		}
		return count;
	}


}