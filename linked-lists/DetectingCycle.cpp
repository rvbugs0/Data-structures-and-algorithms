#include<iostream>
using namespace std;

typedef struct node
{
	int data;
	struct node* next;
} node;
int hasCycle(node* head);
int main()
{
	//		  -----3 
	// 1-----2	   \
	//		\___4__/
	node one,two,three,four;
	one.data = 1;
	one.next = &two;
	two.data = 2;
	two.next = &three;
	three.data = 3;
	three.next=&four;
	four.data = 4;
	four.next=&two;
	int z;
	if(z=hasCycle(&one))
	{
		cout<<"cycle found, starts at "<<z<<"\n";
	}
	else
	{
		cout<<"cycle not found\n";
	}
	return 0;
}


int hasCycle(node* head)
{
	int cycleExists =0; 
	node *slowPtr=head,*fastPtr=head;
	while(slowPtr && fastPtr && fastPtr->next)
	{
		slowPtr = slowPtr->next;
		fastPtr = fastPtr->next->next;
		if(slowPtr==fastPtr) 
			{
				cycleExists=1;
				break;	
			}
	}
	if(cycleExists)
	{
		slowPtr=head;
		while(slowPtr!=fastPtr)
		{
			fastPtr=fastPtr->next;
			slowPtr=slowPtr->next;
		}
		return slowPtr->data;
	}
	return 0;


}