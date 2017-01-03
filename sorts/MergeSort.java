class MergeSort
{
	public static void main(String gg[])
	{
		int a[]={10,2,55,6,9,7,11,3,22,5,2,3};
		System.out.print("Unsorted array is	");
		MergeSort ms=new MergeSort();
		ms.printArray(a);
		ms.mergeSort(a,0,a.length-1);
		System.out.print("Sorted array is		");
		ms.printArray(a);
	}

	public void printArray(int a[])
	{
		int s=a.length;
		for(int i=0;i<s;i++) System.out.print(a[i]+" ");
		System.out.println();
	}

	public void mergeSort(int[] a,int low, int high)
	{
		if(low<high)
		{
			int mid=(low+high)/2;
			mergeSort(a,low,mid);
			mergeSort(a,mid+1,high);
			merge(a,low,mid,high);
		}
	}

	public void merge(int a[],int low, int mid, int high)
	{
		int c[]=new int[a.length];
		for(int i=low;i<=high;i++)
		{
			c[i]=a[i];
		}
		int i,j;
		i=low;
		j=mid+1;
		for(int m=low;m<=high;m++)
		{
			if(i>mid)
			{
				a[m]=c[j];
				j++;
			}
			else if(j>high)
			{
				a[m]=c[i];
				i++;
			}
			else if(c[i]<=c[j])
			{
				a[m]=c[i];
				i++;
			}
			else
			{
				a[m]=c[j];
				j++;
			}
		}
	}
}