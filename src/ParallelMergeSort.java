
import java.io.FileWriter;
import java.io.IOException;
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
	//tamanho do arquivo 10*tamanho do arquivo
	public static int v[] = new int[Principal.tamanho*10];
	public static int indice;
	public int[] GenerateRandomNum(int length, int max)
	{


		MyThread[] threads = new MyThread[10];

        for(int i=0;i<10;i++){
        	threads[i]=new MyThread("arquivo"+i+".txt");
        	threads[i].start();
		}
		for(Thread thread: threads){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

        
        return v;
	}
	public static void gerarArquivos(int tamanho) {
        int count = 0;
        try {
            FileWriter fw = null;
            for (int i = 0; i < 10; i++) {
                fw = new FileWriter("./arquivo" + i + ".txt");
                for (int j = 0; j < tamanho; j++) {
                    Random r = new Random();
                    if (j == tamanho - 1)
                        fw.write(r.nextInt(1000000)+ "\t");
                    else
                        fw.write(r.nextInt(1000000) + "\n");
                }
                fw.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
//        System.out.println("Files genereted...");
    }



	public long MultiThread(int arrSize, int qtdThreads, boolean gerarArquivo, boolean mostrarVetor)
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
		if (gerarArquivo)
		{
		    try {
                FileWriter output = new FileWriter("output.txt");
                for (int i = 0; i < arr.length; i++) {
                    output.write(arr[i]+"\n");
                }
                output.close();
            }catch (IOException e){
		        e.printStackTrace();
            }
		}
		if (mostrarVetor)
		{

				for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
				}

		}
		return totalTime;
	}

}
