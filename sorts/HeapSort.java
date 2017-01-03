class HeapSort
{
	public static void main(String gg[])
	{
		int a[]={10,2,3,55,6,9,7,11,3,22,5,2};
		System.out.print("Unsorted array is	");
		HeapSort hs=new HeapSort();
		hs.printArray(a);
		hs.heapSort(a);
		System.out.print("Sorted array is		");
		hs.printArray(a);
	}

	public void printArray(int a[])
	{
		int s=a.length;
		for(int i=0;i<s;i++) System.out.print(a[i]+" ");
		System.out.println();
	}


	public void heapSort(int a[])
	{
		heapify(a);
		for(int i=a.length-1;i>0;i--)
		{
			int temp=a[i];
			a[i]=a[0];
			a[0]=temp;
			percolateDown(a,0,i);
		}
	}

	public void heapify(int a[])
	{
		int n=a.length;
		for(int i=(n-2)/2;i>=0;i--)
		{
			percolateDown(a,i,n);
		}
	}

	public int getLeftChild(int i,int m)
	{
		int n=2*i+1;
		return n<m?n:-1;
	}

	public int getRightChild(int i,int m)
	{
		int n=2*i+2;
		return n<m?n:-1;
	}

	public void percolateDown(int a[],int i,int m)
	{

		int leftChild=getLeftChild(i,m);
		int rightChild=getRightChild(i,m);
		int data=a[i];
		int max;
		if(leftChild!=-1 && a[leftChild]>data)
		{
			max=leftChild;	
		}
		else
		{
			max=i;
		}
		if(rightChild!=-1 && a[rightChild]>a[max])
		{
			max=rightChild;
		}
		if(max!=i)
		{
		a[i]=a[max];
		a[max]=data;			
		percolateDown(a,max,m);
		}
	}

}
