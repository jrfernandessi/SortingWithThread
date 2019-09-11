import java.util.Scanner;

public class TestParallelMergeSort
{
	// define the size to run, suggest range [1 million, 10 million]
	public final static int arrSize = 10000;


	// the best number of thread is the number of the core of your processors

	private static int threadCount;

	// decide whether to print the merge result
	private final static boolean showSingleResults = false;
	private final static boolean showMultiResults = false;
	private final static int runTime = 1;

	public static void main(String[] args)
	{
		Scanner ler = new Scanner(System.in);
		System.out.println("quantidade de threads");
		threadCount = ler.nextInt();

		ParallelMergeSort pms = new ParallelMergeSort();
		long singleTotal = 0;
		long multiTotal = 0;

		for (int i = 0; i < runTime; i++)
		{
			// single thread result
			singleTotal += pms.SingleThread(arrSize, showSingleResults);

			// multiple thread result
			try
			{
				multiTotal += pms.MultiThread(arrSize, threadCount,
						showMultiResults);
			}
			catch (ThreadNumException e)
			{
				System.out.println("Thread number is illegal!");
			}
		}

		System.out.println("Running type\tTotal\tAverage");
		System.out.println("Single thread\t" + singleTotal + "ms\t"
				+ String.format("%.2f", (double) singleTotal / runTime)
				+ "ms\t");

		System.out.println("Multithread(" + threadCount + ")\t" + multiTotal
				+ "ms\t" + String.format("%.2f", (double) multiTotal / runTime)
				+ "ms\t\n");
		System.out.println(String.format("%.2f", (double) singleTotal
				/ multiTotal)
				+ " times faster!");
		
//		ParallelMergeSort m = new ParallelMergeSort();
//
//		m.GenerateRandomNum(arrSize, arrSize);
//		for(int i=0;i<arrSize;i++) {
//			System.out.println(m.v[i]);
//		}
	}

}
