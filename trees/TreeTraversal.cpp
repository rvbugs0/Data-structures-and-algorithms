#include<iostream>
#include<vector>
using namespace std;


class Node
{
	public:
		int data;
		Node *left,*right;
		Node(int data)
		{
			this->data=data;
			left=NULL;
			right=NULL;
		}
};
void inOrderTraveral(Node *head);
void preOrderTraveral(Node *head);
void levelOrderTraversal(Node *head);
void postOrderNonRecrusive(Node *head);
int main()
{

	Node one(1),two(2),three(3),four(4),five(5);
	one.left=&two;
	one.right= &three;
	two.left= &four;
	two.right = &five;
	char s[]="    1\n   / \\\n  2   3\n / \\\n4   5\n";
	cout<<s<<endl;

	inOrderTraveral(&one);
	preOrderTraveral(&one);
	levelOrderTraversal(&one);
	postOrderNonRecrusive(&one);
	return 0;
}


void inOrderTraveral(Node *head)
{
	cout<<"Inorder Traversal\t:\t";
	Node *root =head;
	vector<Node*> stack;
	while(1)
	{
		while(root)
		{
			stack.push_back(root);
			root = root->left;
		}
		if(!stack.size())
		{
			break;
		}
		root = stack[stack.size()-1];
		stack.erase(stack.begin()+stack.size()-1);
		cout<<root->data<<" ";
		root = root->right;  
	}
	cout<<endl;
}

void postOrderNonRecrusive(Node *root){
	cout<<"Post order non-recursive:\t";
	Node *previous = NULL;
	vector<Node*> stack;
	stack.push_back(root);
	while(stack.size()){
		
		//  top
		Node* current = stack[stack.size()-1];
		// stack.erase(stack.begin()+stack.size()-1);


		if(!previous || previous->left==current || previous->right == current)
		{
			
			if(current->left){
				stack.push_back(current->left);
			}
			else if(current->right){
				stack.push_back(current->right);
			}
		}else if(current->left==previous){
			if(current->right){
				stack.push_back(current->right);
			}
		}else{
			cout<<current->data<<" ";
			stack.erase(stack.begin()+stack.size()-1);
		}
		previous = current;

	}
	cout<<"\n";


}


void preOrderTraveral(Node *head)
{
	cout<<"Preorder Traversal\t:\t";
	Node *root =head;
	vector<Node*> stack;
	while(1)
	{
		while(root)
		{
			stack.push_back(root);
			cout<<root->data<<" ";
			root = root->left;
		}
		if(!stack.size())
		{
			break;
		}
		root = stack[stack.size()-1];
		stack.erase(stack.begin()+stack.size()-1);
		root = root->right;  
	}
	cout<<endl;
}


void levelOrderTraversal(Node *head)
{
	cout<<"Level Order Traversal\t:\t";
	Node *temp;
	vector<Node *> queue;
	queue.push_back(head);
	while(queue.size())
	{
		temp = queue[0];
		queue.erase(queue.begin());
		cout<<temp->data<<" ";
		if(temp->left)
		{
			queue.push_back(temp->left);
		}
		if(temp->right)
		{
			queue.push_back(temp->right);
		}
	}
	cout<<endl;
}