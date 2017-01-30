// A divide and conquer program in C/C++ to find the smallest distance from a
// given set of points.
#include<iostream>
#include <stdio.h>
#include <float.h>
#include <stdlib.h>
#include <math.h>

using namespace std;


// A structure to represent a Point in 2D plane
typedef struct Point
{
	int x, y;
}Point;

int compareY(const void* a,const void *b)
{
	return ((Point *)a)->y-((Point *)b)->y;
}


int compareX(const void* a,const void *b)
{
	return ((Point *)a)->x-((Point *)b)->x;
}


// A utility function to find the distance between two points
float dist(Point p1, Point p2)
{
	return sqrt( (p1.x - p2.x)*(p1.x - p2.x) +
				(p1.y - p2.y)*(p1.y - p2.y)
			);
}

pair<float,pair<Point,Point> > bruteForce(Point P[], int n)
{
	float min = FLT_MAX;
	pair<float,pair<Point,Point> > ptr;
	ptr.first = min;
	pair<Point,Point> pts;
	ptr.second = pts;

	for (int i = 0; i < n; ++i)
	{

		for (int j = i+1; j < n; ++j)
		{
			if (dist(P[i], P[j]) < min)
			{

				min = dist(P[i], P[j]);
				pts.first= P[i];
				pts.second = P[j];
				ptr.first = min;
				ptr.second = pts;
			}
		}
	}
	return ptr;
}

float min(float x, float y)
{
	return (x < y)? x : y;
}



pair<float,pair<Point,Point> > stripClosest(Point strip[], int size, float d)
{
	float min = d; // Initialize the minimum distance as d
	pair<float,pair<Point,Point> > ptr;
	ptr.first = min;
	pair<Point,Point> pts;
	ptr.second = pts;

	for (int i = 0; i < size; ++i)
	{
		for (int j = i+1; j < size && (strip[j].y - strip[i].y) < min; ++j)
		{
			if (dist(strip[i],strip[j]) < min)
			{

				min = dist(strip[i], strip[j]);
				pts.first= strip[i];
				pts.second = strip[j];
				ptr.first = min;
				ptr.second = pts;
			}
		}		
	}


	return ptr;
}

pair<float,pair<Point,Point> > closestUtil(Point PX[], int n,Point PY[])
{
	if(n<=3)
	{
		return bruteForce(PX,n);	
	}
	int mid = n/2;
	Point midPoint = PX[mid];
	pair<float,pair<Point,Point> > dl = closestUtil(PX, mid,PY);
	pair<float,pair<Point,Point> > dr = closestUtil(PX + mid, n-mid,PY);
	pair<float,pair<Point,Point> > min;
	if(dl.first<dr.first)
	{
		min = dl;
	} 
	else
	{
		min = dr;
	}

	Point strip[n];
	int j = 0;
	for (int i = 0; i < n; i++)
	{
		if (abs(PY[i].x - midPoint.x) < min.first)
		{
			strip[j] = PY[i];
			j++;
		}
	}

	pair<float,pair<Point,Point> >  q= stripClosest(strip, j, min.first);
	if(q.first<min.first)
	{
		return q;
	}
	else
	{
		return min;
	}

}


pair<float,pair<Point,Point> > closest(Point PX[], int n,Point PY[])
{
	qsort(PX, n, sizeof(Point), compareX);
	qsort(PY, n, sizeof(Point), compareY);
	return closestUtil(PX, n,PY);
}

int main()
{
	Point PX[] = {{2, 3}, {3, 4}, {5, 1}, {12, 10},{12, 30}, {40, 50} };
	Point PY[] = {{2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}};
	int n = sizeof(PX) / sizeof(PX[0]);
	pair<float,pair<Point,Point> > distPair;
	distPair=closest(PX, n,PY);
	printf("The smallest distance is %f between points (%d,%d) and (%d,%d)\n", distPair.first,distPair.second.first.x,distPair.second.first.y,distPair.second.second.x,distPair.second.second.y);
	return 0;
}