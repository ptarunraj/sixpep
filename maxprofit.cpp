#include<bits/stdc++.h>
using namespace std;
 

int mprofit(int index,int buy,int tr,vector<int> &maxi,int n)//tr=transactions remaining

//return 0 only when the no. of days have completed or transactions remaining is 0
{
	if(index == n || tr == 0)
	{
		return 0;
	}
	
	/* we have two options if you have already sold the previous share purchased or you are buying the 1st share

	1.to buy the share- if you buy the share you have to invest money = maxi[index](cost of share) hence - and you cannot buy another 1 efore you sell it ,hence buy = 0 (2ndparameter)
	   in the next recursive call 

	2.not to buy share on that day-if you are not buying the share you wont invest any money hence 0 and the next day you have the chance to buy the share hence buy = 1
	

	 if you decide to sell the sshare you will get money hence maxi[prices] and one transaction will be completed ,hence tr-1 if you dont want to sell on that day you get 0 rs and no
	 change in transaction as you have not sold the share  */

	if(buy == 1) 
	{
		return max(-maxi[index] + mprofit(index+1,0,tr,maxi,n),0 + mprofit(index+1,1,tr,maxi,n));
	}
          //here we are selling the stock hence only maxi[index]
	      return  max(maxi[index] + mprofit(index+1,1,tr-1,maxi,n),0 + mprofit(index+1,0,tr,maxi,n));
}

int maxpro(vector<int> & maxi,int n)
{
	int tr;
	cout<<"Enter max no of transactions"<<endl;
	cin>>tr;

	

	return mprofit(0,1,tr,maxi,n);
}

int main()
{
	int cursum = 0;
	int maxsum = INT_MIN;;
    
	vector<int> maxi;
   cout<<"Enter size of array"<<endl;
   int n;
   cin>>n;
   cout<<"Enter elements into array"<<endl;
	for(int i=0;i<n;i++)
	{
		int p;
		cin>>p;
		maxi.push_back(p);
	}

	cout<<maxpro(maxi,n)<<endl;
   

	

}
