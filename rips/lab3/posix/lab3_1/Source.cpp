#include <cstring>
#include <cstdio>
#include <string>
#include <iostream>
#include <vector>
#include <fstream>
#include <random>
#include <sstream>
#include <pthread.h>
#include <windows.h>
using namespace std;
#pragma warning(disable : 4996)
#define n 2 // kolvo potokov v dannom slyczae 2 proizv i 2 potr
#define N 5000 //kolvo fajlov




vector<string> paths;
int* results;
bool* read;
string* strings;
string word = "here";
string path = "C:\\Users\\valll\\Documents\\programming\\5 сем\\rips\\files\\";// 1.txt; C:\\Users\\valll\\Documents\\programming\\5 сем\\rips\\files\\2.txt; C:\\Users\\valll\\Documents\\programming\\5 сем\\rips\\files\\3.txt; ";
string zapoln[3] = {"here", "I", "am"};


struct parameters
{
    int start;
    int finish;
    int szag;
};



/*void findPathes(string path) {
    string p;
    for (int i = 0; i < path.size(); i++) {
        if (path[i] != ';')
            p += path[i];
        else {

            paths.push_back(p);
            p = "";
        }
    }
}*/


void createFiles() {
    for (int i = 0; i < N; i++) {
        string p = path + to_string(i) + ".txt";
        ofstream f(p);
        for (int k = 0; k < 10; k++)
            f << " " << zapoln[rand()%3];
        paths.push_back(p);
        f.close();
    }
}




void* ThreadFunction1(void* pvParam)
{
    parameters* par = (parameters*)pvParam;
    int kolvo = 0;

    for (int i = par->start; i < par->finish; i += par->szag) {

        while (read[i] == false)
            continue;


        string str;
        kolvo = 0;
        stringstream ss(strings[i]);
        while (ss>>str) {
            if (str == word)
                kolvo++;
        }

        

        results[i] = kolvo;
    }

    return 0;
}


void* ThreadFunction2(void* pvParam)
{
    parameters* par = (parameters*)pvParam;
    int kolvo = 0;

    for (int i = par->start; i < par->finish; i += par->szag) {
        kolvo = 0;
        string line;
        ifstream t(paths[i]);
        stringstream buffer;
        string str;
        buffer << t.rdbuf();
        str = buffer.str();
        strings[i] = str;
        read[i] = true;
    }

    return 0;
}



int main(int argc, char** argv)
{
    pthread_t hThreadsPr[n];
    pthread_t hThreadsPotr[n];
    parameters parameter[n];
    createFiles();
    int kolvo = N;
    int s = kolvo / n;
    results = new int[kolvo];
    strings = new string[kolvo]; 
    read = new bool[kolvo];
    for (int i = 0; i < kolvo; i++)
        read[i] = false;

    LARGE_INTEGER liFrequency, liStartTime, liFinishTime;
    QueryPerformanceFrequency(&liFrequency);
    QueryPerformanceCounter(&liStartTime);


    for (int k = 0; k < n; k++)
    {
        parameter[k].start = k * s;
        if (k != n - 1)
            parameter[k].finish = s * (k + 1);
        else
            parameter[k].finish = kolvo;
        parameter[k].szag = 1;
        int result = pthread_create(&hThreadsPr[k], NULL, ThreadFunction2, &(parameter[k]));
        if (result) // обработка ошибки
        {
            printf("Create Thread %d Error\n", k);
            return -1;
        }
        result = pthread_create(&hThreadsPotr[k], NULL, ThreadFunction1,  & (parameter[k]));
        if (result) // обработка ошибки
        {
            printf("Create Thread %d Error=\n", k);
            return -1;
        }
    }
    for (int i = 0; i < n; i++) {
        pthread_join(hThreadsPotr[i], NULL);
        pthread_join(hThreadsPr[i], NULL);
    }

    QueryPerformanceCounter(&liFinishTime);
    double total = 1000. * (liFinishTime.QuadPart - liStartTime.QuadPart) / liFrequency.QuadPart;

    

    for (int i = 0; i < kolvo; i++)
        cout << paths[i] << ": " << results[i] << ";\n";



    cout << "\nTotal: " << total << "\n";

    return 0;
}
