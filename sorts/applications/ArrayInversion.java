class ArrayInversion
{
	public static int count=0;

	public int countArrayInversions(int a[])
	{
		mergeSort(a,0,a.length-1);
		return count;
	}

	public void mergeSort(int a[],int low,int high)
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
				for(int ll=i;ll<=mid;ll++) System.out.println("Pair : "+c[ll]+" - "+c[j] );
				count+=mid-i+1;
				j++;
			}
		}
	}

	public void printArray(int a[])
	{
		int s=a.length;
		for(int i=0;i<s;i++) System.out.print(a[i]+" ");
		System.out.println();
	}

	public static void main(String gg[])
	{
		int a[]={4,11,3,6,8,7,5,2,22};
		
		ArrayInversion arrayInversion=new ArrayInversion();
		System.out.println("Number of Inversions : "+arrayInversion.countArrayInversions(a));
		
	}

}




