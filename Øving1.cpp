#include <thread>

#include <iostream>
#include <vector>
#include <thread>
#include <mutex>
#include <bits/stdc++.h>

using namespace std;


bool isPrime(int num){
    if(num < 2){
        return false;
    }
    for(int i = 2; i<= num/2 + 1; i++){
        if (num % i == 0 && num!=2)
        {
            return false;
        }
    }
    return true;
}

void primesInInterval(int start, int end, vector<int> *primes, int intervall){
    for (int i = start; i <= end; i+=intervall)
    {
        if (isPrime(i))
        {
            primes -> emplace_back(i);
        }
    }
}

int main(){
    
  
    int start = -10;
    int end = 101;
    int numberOfThreads = 3;
    vector<thread> threads;
    vector<int> arr[numberOfThreads];
    vector<int> primes;
    

      for (int i = 0; i < numberOfThreads; i++)
    {
        threads.emplace_back(primesInInterval, start + i, end, &arr[i], numberOfThreads);   
    }
    
  
  for (auto &thread : threads)
    thread.join();
    
    //Printer ut verdiene i de indivduelle trådene
   int sum = 0;
   for (int i = 0; i < numberOfThreads; i++)
   {
       cout<<"Thread: " << i + 1 << endl;
       for (int j = 0; j < arr[i].size(); j++)
       {
           sum++;
           cout<< arr[i].at(j) << " ";
       }
        cout<<"\nEnd thread: " << i + 1 <<endl;
   }
   cout<<"Sum: " << sum << endl;

 

     //Combine all vectors into one
  for (int i = 0; i < numberOfThreads; i++){
    primes.insert(primes.end(), arr[i].begin(), arr[i].end());
  }

  sort(primes.begin(), primes.end());

   
    for (int i = 0; i < primes.size(); i++)
    {
        cout << primes.at(i) << " ";
    }
    
}