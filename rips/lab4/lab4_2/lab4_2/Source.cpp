#include <iostream>
#include <iomanip>
#include <iterator>
#include <future>
#include <numeric>
#include <map>
#include <string>
#include <algorithm>
using namespace std;

const int N = 15000; // == razmer matricy i kolvo potokov
const int kolvo = 4;
const int block = N / kolvo;

vector<double> results(N);
vector<thread> threads(kolvo - 1);



void Avrg(double** matrix, vector<double> v, int start, int finish)
{
	int temp = 0;
	for (int k = start; k < finish; k++){
		for (int i = 0; i < N; i++){
			temp += matrix[k][i] * v[i];
		}
		results[k] = temp;
	}
}

void ParalCalculate(double** matrix, vector<double>& v)
{
	for (int i = 0; i < (kolvo - 1); i++)
	{
		threads[i] = thread(&Avrg, matrix, v, i*block, (i+1)*block);
	}
	Avrg(matrix, v, kolvo*block, N);
	for_each(threads.begin(), threads.end(), mem_fn(&thread::join));
}

int main()
{
	double** matrix = new double* [N];
	for (int i = 0; i < N; i++)
		matrix[i] = new double[N];
	vector<double> v(N);
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++){
			matrix[i][j] = rand() % 1000;
		}
	}
	for (int i = 0; i < N; i++)
	{
		v[i] = rand() % 1000;
	}
	clock_t start = clock();
	ParalCalculate(matrix, v);
	clock_t end = clock();
	double seconds = (double)(end - start) / CLOCKS_PER_SEC;
	printf("\nThe time: %f seconds\n", seconds);
	return 0;
}