import java.util.ArrayList;
class BinarySearchTree
{

	private Node root;
	public BinarySearchTree()
	{

	}
	public BinarySearchTree(Node root)
	{
		this();
		this.root = root;
	}
	public Node getMin()
	{
		Node temp =root;
		Node prev = null;
		while(temp!=null)
		{
			prev = temp;
			temp = temp.getLeft();
		}
		return prev;
	}
	public Node getMax()
	{
		Node temp =root;
		Node prev = null;
		while(temp!=null)
		{
			prev = temp;
			temp = temp.getRight();
		}
		return prev;
	}


	private Node contains(int k)
	{
		Node temp =root;
		while(temp!=null)
		{
			if(k>temp.getData())
			{
				temp= temp.getRight();
			}
			else if(k<temp.getData())
			{
				temp = temp.getLeft();
			}
			else
			{
				return temp;
			}

		}
		return null;
	}

	public boolean exists(int k)
	{
		if(contains(k)!=null) return true;
		return false;
	}

	public Node add(int k)
	{
		Node n = contains(k);
		if(n!=null) return n;
		if(root==null)
		{
			root = new Node(k);
			return root;
		}
		else
		{
			Node temp = root;
			Node p=null;
			while(temp!=null)
			{
				if(k<temp.getData())
				{
					p=temp;
					temp = temp.getLeft();					
					
				}
				else
				{
					p=temp;
					temp = temp.getRight();
				}
			}
			Node newNode = new Node(k);
			if(p.getData()<k)
			{
				p.setRight(newNode);
			}
			else
			{
				p.setLeft(newNode);
			}
			return newNode;
		}

	}

	public void printTree()
	{
		System.out.println("\n--------Inorder traversal---\n");
		inorder(root);
		System.out.println("\n\n----------------------------");
	}

	private void inorder(Node root)
	{
		if(root==null) return;
		inorder(root.getLeft());
		System.out.print(root.getData()+"\t");
		inorder(root.getRight());
	}

	public Node predecessor(int k)
	{
		if(root == null) return null;
		ArrayList<Node> stack = new ArrayList<Node>();
		Node temp = root;
		while(temp!=null)
		{
			stack.add(temp);
			if(temp.getData()> k)
			{
				temp=temp.getLeft();
			}
			else if(temp.getData()<k)
			{
				temp = temp.getRight();
			}
			else 
			{
				if(temp.getLeft()!=null)
				{
					Node rt = null;
					Node rightNode = temp.getLeft(); 
					while(rightNode!=null)
					{
						rt = rightNode;
						rightNode = rightNode.getRight();
					}
					return rt;
					
				}else
				{
					while(!stack.isEmpty())
					{
						Node top = stack.remove(stack.size()-1);
						if(top.getData()<k)
						{
							return top;
						}
					}
					return null;
				}
			}
		}
		return null;
	}

	public void delete(int k)
	{
		if(root==null) return;
		ArrayList<Node> stack = new ArrayList<Node>();
		Node temp = root;
		while(temp!=null)
		{
			stack.add(temp);
			if(temp.getData()> k)
			{
				temp=temp.getLeft();
			}
			else if(temp.getData()<k)
			{
				temp = temp.getRight();
			}
			else 
			{
				stack.remove(stack.size()-1);
				break;	
			}

		}
		if(temp==null) return;

		//case-1 when node has no children
		if(temp.getLeft()==null && temp.getRight()==null)
		{
			
			if(!stack.isEmpty())
			{
				Node top = stack.remove(stack.size()-1);
				if(top.getLeft().getData()==k)
				{
					top.setLeft(null);
				}
				else
				{
					top.setRight(null);
				}
			}
		}
		//case-2 when node has one children
		else if((temp.getRight()==null && temp.getLeft()!=null) || (temp.getRight()!=null && temp.getLeft()==null)) 
		{
			Node t = temp.getLeft();
			if(t==null)
			{
				t= temp.getRight();
			}
			if(!stack.isEmpty())
			{
				Node top = stack.remove(stack.size()-1);
				if(top.getLeft().getData()==k)
				{
					top.setLeft(t);
				}
				else
				{
					top.setRight(t);
				}
			}

		}else
		{
			//case-3 : when node has both left and right child
			Node predPrev = null;
			Node pred = temp.getLeft();
			while(pred.getRight()!=null  && pred.getRight().getRight()!=null)
			{
				predPrev = pred;
				pred = pred.getRight();
			}

			Node tempParent = null;
			if(!stack.isEmpty())
			{
				tempParent = stack.remove(stack.size()-1);
				if(tempParent.getLeft()==temp)
				{
					tempParent.setLeft(pred);							
				}
				else
				{
					tempParent.setRight(pred);
				}							
				pred.setRight(temp.getRight());
				if(predPrev!=null)
				{
					pred.setLeft(predPrev);
					predPrev.setRight(pred.getLeft());
				}
			}
			else
			{
				root = pred;
				pred.setRight(temp.getRight());
				if(predPrev!=null)
				{
					pred.setLeft(predPrev);
					predPrev.setRight(pred.getLeft());
				}

			}

		}
	}


	public static void main(String gg[])
	{
		Node root = new Node(22);
		BinarySearchTree bst = new BinarySearchTree(root);

		int ar[]  = new int[]{5,66,33,88,77,4,2,21,65};
		for( int a :ar)
		{
			bst.add(a);
		} 
		bst.printTree();
		System.out.println("Min :\t"+bst.getMin().getData());
		System.out.println("Max :\t"+bst.getMax().getData());
		System.out.println("Predecessor of 66:\t"+bst.predecessor(66).getData());
		System.out.println("Predecessor of 5:\t"+bst.predecessor(5).getData());
		bst.delete(5);
		bst.delete(2);
		bst.delete(33);
		bst.printTree();
	}



	static class Node
	{
		private int data;
		private Node left;
		private Node right;
		public Node()
		{

		}
		public Node(int data)
		{
			this();
			this.data = data;
		}
		public void setData(int data)
		{
			this.data = data;
		}
		public int getData()
		{
			return this.data;
		}
		public void setLeft(Node left)
		{
			this.left = left;
		}
		public Node getLeft()
		{
			return this.left;
		}
		public void setRight(Node right)
		{
			this.right = right;
		}
		public Node getRight()
		{
			return this.right;
		}
	}
}