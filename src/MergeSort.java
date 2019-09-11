
// mergeSort algorithm
public class MergeSort
{
	public static void Sort(int[] arr, int left, int right)
	{
		if(left < right)
		{
			int mid = (left + right) / 2;
			Sort(arr, left, mid);
			Sort(arr, mid + 1, right);
			Merge(arr, left, mid, right);
		}
	}
	
	public static void Merge(int[]arr,int left, int mid ,int right)
	{
		int i = left, j = mid + 1, k = 0;
		final int SIZE = right - left + 1;
		int[] temp = new int[SIZE];
		
		while(i <= mid && j <= right)
		{
			if(arr[i] < arr[j])
				temp[k++] = arr[i++];
			else
				temp[k++] = arr[j++];
		}
		
		while(i <= mid)
			temp[k++] = arr[i++];
		while(j <= right)
			temp[k++] = arr[j++];
		
		for (int l = 0; l < SIZE; l++)
		{
			arr[left + l] = temp[l];
		}
	}

}
