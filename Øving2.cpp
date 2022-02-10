#include <functional>
#include <iostream>
#include <list>
#include <mutex>
#include <thread>
#include <vector>
#include <condition_variable>
#include <chrono>
#include <sstream>
using namespace std;

class Workers{
    private:
        mutex taskLock;
        condition_variable cv;
        list<function<void()>> tasks;
        vector<thread> threads;
        int numberOfThreads;
        bool run = true;
        bool waiting = false;
    public:

    explicit Workers(int numberOfThreads) {
        this->numberOfThreads = numberOfThreads;
    };

        void post(function<void()> function){
            unique_lock<mutex> lock(taskLock);
            tasks.emplace_back(function);
            lock.unlock();
            waiting = false;
            cv.notify_one();
        };

         void post_with_delay(const function<void()> f, long timeout_ms) {
        post([f, timeout_ms] {
            this_thread::sleep_for(chrono::milliseconds(timeout_ms));
            f();
        });
     };

        void start(){
            for (int i = 0; i < numberOfThreads; i++)
            {
                threads.emplace_back([this]{
                    while(run){
                        function<void()> task;
                        unique_lock<mutex> lock(taskLock);
                        while(waiting){
                            cv.wait(lock);
                        }
                        waiting = true;
                        if(!tasks.empty()){
                            task = *tasks.begin();
                            tasks.pop_front();
                        }
                        lock.unlock();
                        waiting = false;
                        cv.notify_one();
                    if(task){
                        task();
                    }
                    }
                });
            }
            cv.notify_one();
        };

        void stop(){
            run = false;
            for (auto &thread: threads)
            thread.join();
        };
};


int main(){
        Workers worker_threads(5);
        Workers event_loop(1);


    worker_threads.start(); 
    event_loop.start(); 
    worker_threads.post([] {
        string s = "AAA  ";
        stringstream ss;
        auto i = this_thread::get_id();
        ss << i;
        string id = ss.str();
        string resultat = s + id + "";
        cout << resultat << endl;
    });
    worker_threads.post([] {
        string s = "BBB  ";
        stringstream ss;
        auto i = this_thread::get_id();
        ss << i;
        string id = ss.str();
        string resultat = s + id + "";
        cout << resultat << endl;

    // Might run in parallel with task A
    });

    worker_threads.post([]{
        string s = "CCC  ";
        stringstream ss;
        auto i = this_thread::get_id();
        ss << i;
        string id = ss.str();
        string resultat = s + id + "";
        cout << resultat << endl;
        });
    
    worker_threads.post_with_delay([]{
        string s = "DDD  ";
        stringstream ss;
        auto i = this_thread::get_id();
        ss << i;
        string id = ss.str();
        string resultat = s + id + "";
        cout << resultat << endl;
        }, 3000);


    event_loop.post([] {
        string s = "EEE  ";
        stringstream ss;
        auto i = this_thread::get_id();
        ss << i;
        string id = ss.str();
        string resultat = s + id + "";
        cout << resultat << endl;
    // Might run in parallel with task A and B
    });
    event_loop.post([] {
        string s = "FFF  ";
        stringstream ss;
        auto i = this_thread::get_id();
        ss << i;
        string id = ss.str();
        string resultat = s + id + "";
        cout << resultat << endl;
    // Will run after task C
    // Might run in parallel with task A and B
    });


    this_thread::sleep_for(chrono::seconds(1));
    worker_threads.stop();
    event_loop.stop();
    return 1;
}