#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <limits>
#include <algorithm>
using namespace std;

int *dist;
int *indexes;
int imax = std::numeric_limits<int>::max();
void percolateDown(int vertices[],int i,int s);
void heapify(int vertices[],int n);

int main() {

    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    int N,M;
   
    cin>>N>>M;
    dist=new int[N];
    int included[N];
    indexes = new int[N];
    for(int l=0;l<N;l++) 
    {
	    dist[l]=imax;    	
	    indexes[l]=-1;
	    included[l]=0;
    }

    vector<vector<pair<int,int> > > pairs(N);
    int x=0;
    while(x<M)
        {
        int a,b,c;
        cin>>a>>b>>c;
        pair<int,int> node1,node2;
        node1.first=b-1;
        node2.first=a-1;
        node1.second=c;
        node2.second=c;
        
        pairs[a-1].push_back(node1);
        pairs[b-1].push_back(node2);
        x++;
    }
    int S;
    cin>>S;

    vector<pair<int,int> > start = pairs[S-1];
    int heapSize=pairs.size()-1;
    int heap[heapSize]; 
    int h=0;
    for(;h<S-1;h++)
    {
    	heap[h]=h;
    	indexes[h]=h;    	
    }
    h=S-1;
    for(;h<heapSize;h++)
    {
    	heap[h]=h+1;
    	indexes[h+1]=h;
    }

	included[S-1]=1;
	vector<int> inc;
	inc.push_back(S-1);

    int b=1;
    int weight=0;
    while(b!=pairs.size())
        {

        for(int c=0;c<start.size();c++)
        {
        	if(included[start[c].first]!=1)
        	{
	        	dist[start[c].first]=min(dist[start[c].first],start[c].second);        		
        	}
        } 
        heapify(heap,heapSize);
    	int min = heap[0];
    	heap[0]=heap[heapSize-1];
    	heapSize--;
    	weight+=dist[min];
		inc.push_back(min);
		included[min]=1;
		b++;
		start=pairs[min];        
    }
    cout<<weight;
    return 0;
}


int getLeftChild(int i,int s)
{
	return (2*i+1)<s?2*i+1:-1;
}

int getRightChild(int i,int s)
{
	return (2*i+2)<s?2*i+2:-1;
}


void heapify(int vertices[],int n)
{
	for(int j=(n-2)/2;j>=0;j--)
	{
		percolateDown(vertices,j,n);
	}
}

void percolateDown(int vertices[],int i,int s)
{
	int left,right,min;
	left = getLeftChild(i,s);
	right= getRightChild(i,s);
	if(left!=-1 && dist[vertices[left]]<dist[vertices[i]])
	{
		min=left;		
	}
	else
	{
		min=i;
	}
	if(right!=-1 && dist[vertices[right]]<dist[vertices[min]]) 
	{
		min=right;
	}
	if(min!=i)
	{

		indexes[vertices[i]]=min;
		indexes[vertices[min]]=i;

		int temp=vertices[min];
		vertices[min]=vertices[i];
		vertices[i]=temp;
		percolateDown(vertices,min,s);
	}

}


