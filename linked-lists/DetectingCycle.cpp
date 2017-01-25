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
	node one,two,three,four;
	one.data = 1;
	one.next = &two;
	two.data = 2;
	two.next = &three;
	three.data = 3;
	three.next=&four;
	four.data = 4;
	four.next=&two;
	if(hasCycle(&one))
	{
		cout<<"cycle found\n";
	}
	else
	{
		cout<<"cycle not found\n";
	}
	return 0;
}


int hasCycle(node* head)
{
	node *slowPtr=head,*fastPtr=head;
	while(slowPtr && fastPtr && fastPtr->next)
	{
		slowPtr = slowPtr->next;
		fastPtr = fastPtr->next->next;
		if(slowPtr==fastPtr) return 1;
	}
	return 0;
}