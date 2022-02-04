#include <iostream>
#include <iomanip>
#include <iterator>
#include <future>
#include <numeric>
#include <map>
#include <string>
#include <algorithm>

using namespace std;

unsigned long const num_threads = 10;




vector<double> results(num_threads);
vector<thread> threads(num_threads);

int a = 0;
int b = 1000;
double sum = 0;
unsigned long const block_size = (b - a) / num_threads;

double Foo(double x)
{
	return x / sqrt(pow(x, 4) + 16);
}

void Comp(double a, double index, double& temp)
{
	if (index < num_threads)
		temp = Foo(a + index * block_size) * block_size;
	else
		temp = Foo(b) * block_size;
}



int main()
{
	for (int i = 0; i <= (num_threads - 1); i++)
	{
		threads[i] = thread(&Comp, a, i, ref(results[i]));
	}
	for_each(threads.begin(), threads.end(), mem_fn(&thread::join));
	for (int i = 0; i < num_threads; i++)
	{
		cout << i + 1 << " Thread " << i+1 << " returned: " << results[i] << endl;
		sum += results[i];
	}
	cout << "Result = " << sum << endl;
	return 0;
}