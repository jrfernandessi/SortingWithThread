import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class Principal {
    // define o tamanho do array = 10*tamanho do arquivo
    public static int tamanho;


    private static int qtdThread;

    // decide se mostra ou não o vetor ordenado
    private final static boolean gerarArquivo = true;
    private final static boolean mostrarVetor = false;
    private static int rodadas;
    static OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();


    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.println("tamanho dos arquivos");
        tamanho = ler.nextInt();
        System.out.println("número de vezes que deseja executar o algoritmo");
        rodadas = ler.nextInt();
        ParallelMergeSort.gerarArquivos(tamanho);

//        System.out.println(operatingSystemMXBean.getSystemCpuLoad());
        while (true) {
            System.out.println("quantidade de threads");
            qtdThread = ler.nextInt();

            ParallelMergeSort pms = new ParallelMergeSort();
            long multiTotal = 0;

            for (int i = 0; i < rodadas; i++) {
                double antes = operatingSystemMXBean.getFreePhysicalMemorySize();
//                System.out.println("Uso de CPU antes "+operatingSystemMXBean.getSystemCpuLoad());
//                System.out.println(antes);
                multiTotal += pms.MultiThread(tamanho * 10, qtdThread,
                        gerarArquivo, mostrarVetor);
//                System.out.println("Uso de CPU depois "+operatingSystemMXBean.getSystemCpuLoad());
                double depois = operatingSystemMXBean.getFreePhysicalMemorySize();
                System.out.println("Consumo da memória: "+((antes-depois)/1028)/1028 + "MB");

            }


            System.out.println("Multithread(" + qtdThread + ")\t" + multiTotal
                    + "ms\t Média: " +multiTotal/rodadas+ "ms\n");
        }
    }

}
