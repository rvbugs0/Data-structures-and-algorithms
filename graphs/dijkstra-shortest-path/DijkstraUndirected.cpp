/*

Given a graph consisting  nodes (labelled  to ) where a specific given node  represents the starting position
and an edge between two nodes is of a given length, which may or may not be equal to other lengths in the graph.

It is required to calculate the shortest distance from the start position (Node ) to all of the other nodes in the graph.
Note: If a node is unreachable , the distance is assumed as .

The first line contains , denoting the number of test cases. 
First line of each test case has two integers , denoting the number of nodes in the graph and , 
denoting the number of edges in the graph.

The next  lines each consist of three space-separated integers   , 
where  and  denote the two nodes between which the undirected edge exists,  denotes the length of edge between these corresponding nodes.
The last line has an integer , denoting the starting position.


*/


#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <limits>
#include <algorithm>
using namespace std;

int *dist;
int *indexes;
int *heap;
int imax = std::numeric_limits<int>::max();
void percolateDown(int i,int s);
void heapify(int n);

int main() {

    int T;
    cin>>T;
    while(T--)
    {
    int N,M;
   
    cin>>N>>M;
    int d[N];
    dist=d;
    int included[N];

    //indexes  : index of node i in heap (basically array)
    indexes = new int[N];
    for(int l=0;l<N;l++) 
    {
        dist[l]=imax;       
        indexes[l]=-1;
        included[l]=0;
    }
    //pair <node,cost>
    vector<vector<pair<int,int> > > pairs(N);
    int x=0;
    while(x<M)
        {
        int a,b,c;
        cin>>a>>b>>c;
        pair<int,int> node;
        node.first=b-1;
        node.second=c;
        pairs[a-1].push_back(node);
        node.first=a-1;
        pairs[b-1].push_back(node);        
        x++;
    }
    int S;
    cin>>S;

    vector<pair<int,int> > start = pairs[S-1];
    int heapSize=pairs.size()-1;

    //heap stores vertices
    int hp[heapSize]; 
    heap=hp;
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

    //what vertices we've included till now 
    included[S-1]=1;
    int StartIndex=S-1;

    //maintains distances of every node from start
    dist[StartIndex]=0;
    int b=1;
    while(b!=pairs.size())
        {

        for(int c=0;c<start.size();c++)
        {
            if(!included[start[c].first])
            {
                dist[start[c].first]=min(dist[start[c].first],dist[StartIndex]+start[c].second);                
            }
        } 
        heapify(heapSize);
        // vertex with min edge cost
        int min = heap[0];
        
        // cout<<"min edge from "<<StartIndex<<" to "<<min<<endl;
        //swapping with the last element
        heap[0]=heap[heapSize-1];
        //updating index information
        indexes[heap[heapSize-1]]=0;
        heapSize--;
        included[min]=1;
        b++;
        StartIndex=min;
        start=pairs[min];        
    }

    for(int g=0;g<N;g++)
    {
        if(dist[g]==imax || dist[g]<0  ) cout<<"-1 ";
        else if(dist[g]==0) ;
        else cout<<dist[g]<<" ";    
    }
    cout<<endl;
}

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


void heapify(int n)
{
    for(int j=(n-2)/2;j>=0;j--)
    {
        percolateDown(j,n);
    }
}

void percolateDown(int i,int s)
{
    int left,right,min;
    left = getLeftChild(i,s);
    right= getRightChild(i,s);
    if(left!=-1 && dist[heap[left]]<dist[heap[i]])
    {
        min=left;       
    }
    else
    {
        min=i;
    }
    if(right!=-1 && dist[heap[right]]<dist[heap[min]]) 
    {
        min=right;
    }
    if(min!=i)
    {

        //maintaining indexes of which element is at which index in heap
        //the extra book keeping
        indexes[heap[i]]=min;
        indexes[heap[min]]=i;

        int temp=heap[min];
        heap[min]=heap[i];
        heap[i]=temp;
        percolateDown(min,s);
    }

}

