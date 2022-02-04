//1_6
#include <windows.h>
#include <process.h>
#include <stdio.h>
#include <iostream>
#include <chrono>

#define p 5 // количество дочерних потоков
int n = 100000000;
double pi = 0.; // требуется взаимоисключающий доступ
CRITICAL_SECTION cs;
DWORD WINAPI ThreadFunction(LPVOID pvParam)
{
	int nParam = (int)pvParam;
	int i, start;
	double h, sum, x;
	h = 1. / n;
	sum = 0.;
	start = nParam;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += 4. / (1. + x * x);
	}
	// монопольный доступ
	EnterCriticalSection(&cs);
	pi += h * sum;
	LeaveCriticalSection(&cs);
	return 0;
}
int main()
{
	HANDLE hThreads[p];
	int k;
	InitializeCriticalSection(&cs);
	// создание дочерних потоков
	auto begin = std::chrono::high_resolution_clock::now();
	for (k = 0; k < p; ++k)
	{
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0,
			_beginthreadex_proc_type(ThreadFunction), (LPVOID)k, 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}
	// ожидание завершения дочерних потоков
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	auto end = std::chrono::high_resolution_clock::now();
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	// освобождение ресурсов, занятых критической секцией
	DeleteCriticalSection(&cs);
	printf("PI = %.16f\n", pi);
	std::cout << std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count();
	return 0;
}










//1_4
/*#include <windows.h>
#include <process.h>
#include <chrono>
#include <iostream>
#include <stdio.h>
#define p 5 // количество дочерних потоков
int n = 100000000;
// тип параметра, передаваемого функции потока
struct SThreadParam
{
	int k;
	double sum;
};
DWORD WINAPI ThreadFunction(LPVOID pvParam)
{
	SThreadParam* param = (SThreadParam*)pvParam;
	int i, start;
	double h, sum, x;
	h = 1. / n;
	sum = 0.;
	start = param->k;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += 4. / (1. + x * x);
	}
	// к глобальной переменной не следует часто обращаться
	param->sum = h * sum;
	return 0;
}

int main()
{
	HANDLE hThreads[p]; // массив дескрипторов потоков
	// массив параметров потоковых функций
	SThreadParam params[p];
	int k;
	double sum;
	// создание дочерних потоков
	auto begin = std::chrono::high_resolution_clock::now();
	for (k = 0; k < p; ++k)
	{
		params[k].k = k;
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0,
			_beginthreadex_proc_type(ThreadFunction), (LPVOID) & (params[k]), 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}
	// ожидание завершения дочерних потоков
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	auto end = std::chrono::high_resolution_clock::now();
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	sum = 0.;
	for (k = 0; k < p; ++k)
		sum += params[k].sum;
	printf("PI = %.16f\n", sum);
	std::cout << std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count();
	return 0;
}*/






//1_3
/*#include <windows.h>
#include <process.h>
#include <iostream>
#include <stdio.h>
#include <chrono>
#define p 5 // количество дочерних потоков
double pi[p];
int n = 100000000;

DWORD WINAPI ThreadFunction(LPVOID pvParam)
{
	int nParam = (int)pvParam;
	int i, start;
	double h, sum, x;
	h = 1. / n;
	sum = 0.;
	start = nParam;
	for (i = start; i < n; i += p)
	{
		x = h * i;
		sum += 4. / (1. + x * x);
	}
	pi[nParam] = h * sum;
	return 0;
}
int main()
{
	HANDLE hThreads[p];
	int k;
	double sum;
	// создание дочерних потоков
	auto begin = std::chrono::high_resolution_clock::now();
	for (k = 0; k < p; ++k)
	{
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0,
			_beginthreadex_proc_type(ThreadFunction), (LPVOID)k, 0, NULL);
		if (hThreads[k] == NULL) // обработка ошибки
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}
	// ожидание завершения дочерних потоков
	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);
	auto end = std::chrono::high_resolution_clock::now();
	for (k = 0; k < p; ++k)
		CloseHandle(hThreads[k]);
	sum = 0.;
	for (k = 0; k < p; ++k)
		sum += pi[k];
	printf("PI = %.16f\n", sum);
	std::cout << std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count();
	return 0;
}*/
