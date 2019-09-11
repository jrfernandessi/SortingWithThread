
import java.io.FileWriter;
import java.util.Random;


class SortThread implements Runnable
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

        MyThread thread1 = new MyThread("arquivo0.txt");
        MyThread thread2 = new MyThread("arquivo1.txt");
        MyThread thread3 = new MyThread("arquivo2.txt");
        MyThread thread4 = new MyThread("arquivo3.txt");
        MyThread thread5 = new MyThread("arquivo4.txt");
        MyThread thread6 = new MyThread("arquivo5.txt");
        MyThread thread7 = new MyThread("arquivo6.txt");
        MyThread thread8 = new MyThread("arquivo7.txt");
        MyThread thread9 = new MyThread("arquivo8.txt");
        MyThread thread10 = new MyThread("arquivo9.txt");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

//		while(thread1.isAlive() || thread2.isAlive() ||	thread3.isAlive() ||
//				thread4.isAlive() || thread5.isAlive() ||	thread6.isAlive() ||
//				thread7.isAlive() ||	thread8.isAlive() ||
//				thread9.isAlive() ||
//				thread10.isAlive()) {
//
//		}
        
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

//            List<Integer> list = new ArrayList<>();
//            for(int i=0;i<10;i++) {
//                LineNumberReader line = new LineNumberReader(new FileReader("arquivo" + i + ".txt"));
//                System.out.println("printing file "+i);
//
//                while (true) {
//
//                    try {
//                        if(line.readLine()!=null) {
//                            System.out.println(line.readLine());
//                        }
//                        else
//                            break;
//                    } catch (IOException e) {
//                        System.err.println("ERROR read... \n" + e);
//                    }
//                }
//            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Files genereted...");
    }

	/**
	 * single thread function
	 * 
	 * @param arrSize
	 */
	public long SingleThread(int arrSize, boolean showResults)
	{
		int[] arr = GenerateRandomNum(arrSize, arrSize);

		// start sort
		long startTime = System.currentTimeMillis();
		MergeSort.Sort(arr, 0, arr.length - 1);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;

		if (showResults)
		{
			for (int i = 0; i < arr.length; i++)
			{
				System.out.println(arr[i]);
			}
			System.out.println("Single thread performance: \t" + totalTime
					+ "ms");
		}
		return totalTime;
	}

	/**
	 * multiple thread function
	 * 
	 * @param arrSize
	 * @param threadCount
	 * @throws ThreadNumException
	 */
	public long MultiThread(int arrSize, int threadCount, boolean showResults)
			throws ThreadNumException
	{
		if (threadCount < 1 || arrSize < threadCount)
		{
			throw new ThreadNumException("Num is illegal!");
		}

		int[] arr = GenerateRandomNum(arrSize, arrSize);
		Thread[] runnable = new Thread[threadCount];

		// create threads
		int left = 0, right = arrSize / threadCount - 1;
		for (int i = 0; i < runnable.length; i++)
		{
			if (threadCount - 1 == i)
				right = arrSize - 1;
			runnable[i] = new Thread(new SortThread(arr, left, right));
			left = right + 1;
			right = arrSize / threadCount * (i + 2) - 1;

		}

		// start to run each thread
		long startTime1 = System.currentTimeMillis();
		for (Thread thread : runnable)
		{
			thread.start();
		}

		// wait the sub threads to be done
		try
		{
			for (Thread thread : runnable)
			{
				thread.join();
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		left = 0;
		right = arrSize / threadCount - 1;

		// merge the results
		for (int i = 0; i < runnable.length; i++)
		{
			if (threadCount - 1 == i)
				right = arrSize - 1;
			MergeSort.Merge(arr, 0, left - 1, right);
			left = right + 1;
			right = arrSize / threadCount * (i + 2) - 1;

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
			System.out.println("Multithread(" + threadCount
					+ ") performance: \t" + totalTime + "ms");
		}
		return totalTime;
	}

}
