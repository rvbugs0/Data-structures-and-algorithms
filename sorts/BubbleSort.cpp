#include<iostream>
using namespace std;

void bubbleSort(int a[],int n)
{
	for(int i=n-1;i>=0;i--)
	{
		for(int j=0;j<i;j++)
		{
			if(a[j]>a[j+1])
			{
				int temp = a[j+1];
				a[j+1]=a[j];
				a[j]= temp;
			}
		}
		
	}

	cout<<"The sorted list is : ";
	for(int i=0;i<n;i++)
	{
		cout<<a[i]<<"\t"; 
	}
	cout<<"\n";
}

int main()
{
	int n;
	cout<<"Total Numbers :\t";
	cin>>n;
	int a[n];
	for(int i=0;i<n;i++)
	{
		cout<<"Enter Number : "<<(i+1)<<"\t";
		cin>>a[i];
	}
	bubbleSort(a,n);
	return 0;
}

