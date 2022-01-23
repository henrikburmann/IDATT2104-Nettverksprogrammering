#include <thread>

#include <iostream>
#include <vector>
#include <thread>
#include <mutex>
#include <bits/stdc++.h>

using namespace std;


bool isPrime(int num){
    for(int i = 2; i<= num/2 + 1; i++){
        if (num % i == 0 && num!=2)
        {
            return false;
        }
    }
    return true;
}

void primesInInterval(int start, int end, vector<int> *primes, int intervall){
    for (int i = start; i < end; i+=intervall)
    {
        if (isPrime(i))
        {
            primes -> emplace_back(i);
        }
    }
}

void swap(int *xp, int *yp) 
{ 
    int temp = *xp; 
    *xp = *yp; 
    *yp = temp; 
} 
  
// A function to implement bubble sort 
void bubbleSort(int arr[], int n) 
{ 
    int i, j; 
    for (i = 0; i < n-1; i++)     
      
    // Last i elements are already in place 
    for (j = 0; j < n-i-1; j++) 
        if (arr[j] > arr[j+1]) 
            swap(&arr[j], &arr[j+1]); 
} 


int main(){
    
  
    int start = 2;
    int end = 100;
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

    /*
   for (int i = 0; i < numberOfThreads; i++)
   {
       for (int j = 0; j < arr[i].size();  j++)
       {
           primes.emplace_back(arr[i].at(j));
       }
   }*/

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