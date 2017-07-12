import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
class GraphSearch
{

	public void bfs(Node startNode)
	{
		if(startNode!=null)
		{
			ArrayList<Node> queue= new ArrayList<>();
			Collection<Node> visited = new HashSet<Node>();
			visited.add(startNode);
			queue.add(startNode);
			while(!queue.isEmpty())
			{
				Node temp = queue.remove(0);
				System.out.print(temp.getData()+"\t");
				if(temp.hasNeighbors())
				{
					for(Node i:temp.getNeighbors())
					{
						if(!visited.contains(i))
						{
							queue.add(i);
							visited.add(i);
						}
					}
				}
			}	
		}
		else
		{
			System.out.println("Start Node == NULL");
		}
	}

	public void dfs(Node startNode)
	{
		if(startNode!=null)
		{
			Collection<Node> visited = new HashSet<Node>();
			visited.add(startNode);
			dfsRoam(startNode,visited);

		}
		else
		{
			System.out.println("Start Node == NULL");
		}	
	}

	public void dfsRoam(Node n,Collection<Node> visited)
	{
		visited.add(n);
		System.out.print(n.data+"\t");
		for(Node i:n.getNeighbors())
		{
			if(!visited.contains(i))
			{
				dfsRoam(i,visited);
			}
		}
	}

	public static void main(String gg[])
	{
		Node nodeOne = new Node(1);
		Node nodeTwo = new Node(2);
		Node nodeThree = new Node(3);
		Node nodeFour = new Node(4);
		Node nodeFive = new Node(5);
		nodeOne.addNeighbor(nodeTwo);
		nodeOne.addNeighbor(nodeThree);
		nodeTwo.addNeighbor(nodeFour);
		nodeThree.addNeighbor(nodeFive);
		nodeFour.addNeighbor(nodeFive);

		GraphSearch gs = new GraphSearch();
		System.out.println("BFS");
		gs.bfs(nodeOne);
		System.out.println("\n-------------------------------------------------");
		System.out.println("DFS");
		gs.dfs(nodeOne);
	}


	static class Node
{
	private int data;
	private ArrayList<Node> neighbors;
	public Node(int data)
	{
		this();
		this.data=data;
	}
	public Node()
	{
		this.neighbors = new ArrayList<>();
	}
	public int getData()
	{
		return this.data;
	}
	public void setData(int data)
	{
		this.data = data;
	}
	public void addNeighbor(Node n)
	{
		neighbors.add(n);
		n.getNeighbors().add(this);
		
	}
	public void removeNeighbor(Node n)
	{
		neighbors.remove(n);
	}
	public ArrayList<Node> getNeighbors()
	{
		return this.neighbors;
	}
	public boolean hasNeighbors()
	{
		return neighbors.size()>0;
	}
}
}