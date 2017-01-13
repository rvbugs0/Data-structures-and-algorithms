#include<iostream>
#include<string>
#include<vector>
using namespace std;
int main()
{
	// t : # of test cases
	int t;
	cin>>t;
	while(t--)
	{
		// s : #input string
		string s;
		cin>>s;
		char open[]={'[','{','('};
		char close[]={']','}',')'};
		vector<char> stack;
		int i=0;
		int n=s.length();
		int printed=0;
		while(i<n)
		{

			if(s.at(i)==open[0] || s.at(i)==open[1] || s.at(i)==open[2])
			{
				stack.push_back(s.at(i));
							
			}
			else
			{

				if(stack.size()==0) 
				{
					cout<<"NO"<<endl;
					printed=1;
					break;
				}
				char c =stack[stack.size()-1];
				for(int ii=0;ii<3;ii++)
				{
					if(c==open[ii])
					{
						c=close[ii];
						break;	
					}
				}
				if(s.at(i)!=c)
				{
					cout<<"NO"<<endl;
					printed=1;
					break;

				}
				else 
				{
					stack.erase(stack.begin()+stack.size()-1);
				}	

			}

			i++;			
		}
				if(!printed)
			{
				if(stack.size()==0)
				{
					cout<<"YES"<<endl;
				}
				else
				{
					cout<<"NO"<<endl;
				}
			}

	
	}
	return 0;
}


