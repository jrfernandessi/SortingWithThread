import java.util.Scanner;

public class Principal {
    // define o tamanho do array = 10*tamanho do arquivo
    public static int tamanho;


    private static int qtdThread;

    // decide se mostra ou não o vetor ordenado
    private final static boolean gerarArquivo = true;
    private final static boolean mostrarVetor = false;
    private final static int rodadas = 30;


    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("tamanho dos arquivos");
        tamanho = ler.nextInt();
        ParallelMergeSort.gerarArquivos(tamanho);
        while (true) {
            System.out.println("quantidade de threads");
            qtdThread = ler.nextInt();

            ParallelMergeSort pms = new ParallelMergeSort();
            long multiTotal = 0;

            for (int i = 0; i < rodadas; i++) {


                multiTotal += pms.MultiThread(tamanho * 10, qtdThread,
                        gerarArquivo, mostrarVetor);

            }


            System.out.println("Multithread(" + qtdThread + ")\t" + multiTotal
                    + "ms\t Média: " +multiTotal/rodadas+ "ms\n");
        }
    }

}
