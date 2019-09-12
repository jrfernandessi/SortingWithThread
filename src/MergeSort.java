
// mergeSort algorithm
public class MergeSort
{
	public static void Sort(int[] arr, int inicio, int fim)
	{
		if(inicio < fim)
		{
			int meio = (inicio + fim) / 2;
			Sort(arr, inicio, meio);
			Sort(arr, meio + 1, fim);
			Merge(arr, inicio, meio, fim);
		}
	}
	
	public static void Merge(int[]arr,int inicio, int meio ,int fim)
	{
		int i = inicio, j = meio + 1, k = 0;
		final int SIZE = fim - inicio + 1;
		int[] temp = new int[SIZE];
		
		while(i <= meio && j <= fim)
		{
			if(arr[i] < arr[j])
				temp[k++] = arr[i++];
			else
				temp[k++] = arr[j++];
		}
		
		while(i <= meio)
			temp[k++] = arr[i++];
		while(j <= fim)
			temp[k++] = arr[j++];
		
		for (int l = 0; l < SIZE; l++)
		{
			arr[inicio + l] = temp[l];
		}
	}

}
