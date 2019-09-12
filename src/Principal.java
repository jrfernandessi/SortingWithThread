import java.util.Scanner;

public class Principal
{
	// define o tamanho do array = 10*tamanho do arquivo
	public final static int tamanho = 10000;



	private static int qtdThread;

	// decide se mostra ou não o vetor ordenado
	private final static boolean gerarArquivo = false;
	private final static boolean mostrarVetor = false;
	private final static int rodadas = 1;


	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		while (true) {
			System.out.println("quantidade de threads");
			qtdThread = ler.nextInt();

			ParallelMergeSort pms = new ParallelMergeSort();
			long multiTotal = 0;

			for (int i = 0; i < rodadas; i++) {


				multiTotal += pms.MultiThread(tamanho, qtdThread,
						gerarArquivo, mostrarVetor);

			}


			System.out.println("Multithread(" + qtdThread + ")\t" + multiTotal
					+ "ms\t" + String.format("%.2f", (double) multiTotal / rodadas)
					+ "ms\t\n");
		}
	}

}
