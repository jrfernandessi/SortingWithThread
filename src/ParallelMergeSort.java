
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;


class SortThread extends Thread
{
	private int left;
	private int right;
	private int[] arr;

	public SortThread(int[] arr, int left, int right)
	{
		this.left = left;
		this.right = right;
		this.arr = arr;
	}

	@Override
	public void run()
	{
		MergeSort.Sort(arr, left, right);
	}
}

public class ParallelMergeSort
{
	public static int v[] = new int[10000];
	public static int indice;
	public int[] GenerateRandomNum(int length, int max)
	{
		fileGeneretad(1000);

		MyThread[] thread = new MyThread[10];

        for(int i=0;i<10;i++){
        	thread[i]=new MyThread("arquivo"+i+".txt");
        	thread[i].start();
		}

        
        return v;
	}
	public static void fileGeneretad(int tamanho) {
        int count = 0;
        try {
            FileWriter fw = null;
            for (int i = 0; i < 10; i++) {
                fw = new FileWriter("./arquivo" + i + ".txt");
                for (int j = 0; j < tamanho; j++) {
                    Random r = new Random();
                    if (j == tamanho - 1)
                        fw.write(r.nextInt(10000)+ "\t");
                    else
                        fw.write(r.nextInt(10000) + "\n");
                }
                fw.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
//        System.out.println("Files genereted...");
    }



	public long MultiThread(int arrSize, int qtdThreads, boolean showResults)
	{
		if (qtdThreads < 1 || arrSize < qtdThreads)
		{
			System.out.println("Número ilegal de Threads");
		}

		int[] arr = GenerateRandomNum(arrSize, arrSize);
		Thread[] threadsOrdenacao = new Thread[qtdThreads];

		// create threads
		int left = 0, right = arrSize / qtdThreads - 1;
		for (int i = 0; i < threadsOrdenacao.length; i++)
		{
			if (qtdThreads - 1 == i)
				right = arrSize - 1;
			threadsOrdenacao[i] = new SortThread(arr, left, right);
			left = right + 1;
			right = arrSize / qtdThreads * (i + 2) - 1;

		}

		// start cada thread
		long startTime1 = System.currentTimeMillis();
		for (Thread thread : threadsOrdenacao)
		{
			thread.start();
		}

		// espere as subthreads terminar
		try
		{
			for (Thread thread : threadsOrdenacao)
			{
				thread.join();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		left = 0;
		right = arrSize / qtdThreads - 1;

		// junte os resultados
		for (int i = 0; i < threadsOrdenacao.length; i++)
		{
			if (qtdThreads - 1 == i)
				right = arrSize - 1;
			MergeSort.Merge(arr, 0, left - 1, right);
			left = right + 1;
			right = arrSize / qtdThreads * (i + 2) - 1;

		}

		// statistic the results
		long endTime1 = System.currentTimeMillis();

		long totalTime = endTime1 - startTime1;
		// show results
		if (showResults)
		{
			for (int i = 0; i < arr.length; i++)
			{
				System.out.println(arr[i]);
			}
			System.out.println("Multithread(" + qtdThreads
					+ ") performance: \t" + totalTime + "ms");
		}
		return totalTime;
	}

}
