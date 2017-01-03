class QuickSort{
	public static void main(String gg[])
	{

		int a[]={10,2,55,6,9,7,11,3,22,5,2,3};
		System.out.print("Unsorted array is	");
		QuickSort qs=new QuickSort();
		qs.printArray(a);
		qs.quickSort(a,0,a.length-1);
		System.out.print("Sorted array is		");
		qs.printArray(a);

	}

	public void printArray(int a[])
	{
		int s=a.length;
		for(int i=0;i<s;i++) System.out.print(a[i]+" ");
		System.out.println();
	}

	public void quickSort(int a[],int low,int high)
	{
		if(low<high)
		{
			int pivot=partition(a,low,high);
			quickSort(a,low,pivot-1);
			quickSort(a,pivot+1,high);
		}
	}

	public int partition(int a[],int low,int high)
	{
		int i,j,temp;
		i=j=low+1;
		int mid=(low+high)/2;
		//swap pivot element with a[low]
		temp=a[low];
		a[low]=a[mid];
		a[mid]=temp;

		int pivot=a[low];
		for(;j<=high;j++)
		{
			if(a[j]<=pivot)
			{
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				i++;
			}
		}

		//swap pivot with first element
		temp=a[i-1];
		a[i-1]=a[low];
		a[low]=temp;
		return i-1;
	}


}