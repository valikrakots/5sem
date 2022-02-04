#define _CRT_SECURE_NO_WARNINGS
#include <windows.h>
#include <process.h>
#include <stdio.h>
#include <chrono>
#include <iostream>
#include <random>
#define p 4
#define N 15000



int** matrix;
int* b;
int* res;


struct parameters
{
	int start;
	int finish;
	int szag;
};



void nepr() {
	for (int i = 0; i < N; i++) {
		int s = 0;
		for (int j = 0; j < N; j++) {
			s += matrix[i][j] * b[j];
		}
		res[i] = s;
	}
}



DWORD WINAPI ThreadFunction1(LPVOID pvParam)
{
	parameters* par = (parameters*)pvParam;



	for (int i = par->start; i < par->finish; i += par->szag) {
		int s = 0;
		for (int j = 0; j < N; j++) {
			s += matrix[i][j] * b[j];
		}
		res[i] = s;
	}

	return 0;
}


int nepr_nab(){
	HANDLE hThreads[p];
	parameters parameter[p];
	int s = N / p;
	for (int k = 0; k < p; ++k)
	{
		parameter[k].start = k * s;
		if (k != p - 1)
			parameter[k].finish = s * (k + 1);
		else
			parameter[k].finish = N;
		parameter[k].szag = 1;
		hThreads[k] = CreateThread(NULL, 0, ThreadFunction1, (LPVOID) & (parameter[k]), 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	for (int k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	return 0;
}


int cikl_nab() {
	HANDLE hThreads[p];
	parameters parameter[p];
	int s = N / p;
	for (int k = 0; k < p; k++)
	{
		parameter[k].start = k * s;
		parameter[k].finish = N;
		parameter[k].szag = p;
		hThreads[k] = CreateThread(NULL, 0, ThreadFunction1, (LPVOID) & (parameter[k]), 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	for (int k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	return 0;
}






int main() {
	matrix = (int**)malloc(N * sizeof(int*));
	res = (int*)malloc(N * sizeof(int*));
	b = (int*)malloc(N * sizeof(int*));

	for (int i = 0; i < N; i++) {
		matrix[i] = (int*)malloc(N * sizeof(int));
		for (int j = 0; j < N; j++)
			matrix[i][j] = rand() % 1000;		
	}


	for (int i = 0; i < N; i++) {
		b[i] = rand() % 1000;
	}
	
	

	clock_t start = clock();
	nepr_nab();
	clock_t end = clock();
	double seconds = (double)(end - start) / CLOCKS_PER_SEC;
	printf("The time: %f seconds\n", seconds);
																		

	

	return 0;
}



/*printf("%s", "Your matrix: \n");
for (int i = 0; i < N; i++) {
	for (int j = 0; j < N; j++)
		printf("%d ", matrix[i][j]);
	printf("\n");
}*/

/*printf("%s", "\nYour vector: \n");
for (int i = 0; i < N; i++) {
	printf("%d\n",b[i]);
}*/



/*printf("%s", "\nYour result: \n");
for (int i = 0; i < N; i++) {
	printf("%d\n", res[i]);
}*/



