import java.io.*;
import java.util.Random;

public class Principal {

    LinkedList linkedList;

    static int[] v = new int[10000];
    static int indice = 0;

    public static void main(String[] args) {
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


        for (int i = 0; i < v.length; i++)
            System.out.println(v[i]);

    }

//    public void run() {
//        LineNumberReader line = null;
//        try {
//            line = new LineNumberReader(new FileReader(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println(file);
//        int indice = 0;
//            while (true) {
//                try {
//                    if (line.readLine() != null) {
//                        synchronized (v) {
//                            v[indice] = Integer.parseInt(line.readLine());
//                        }
//                        System.out.println(">>>>>>>>>>>>>>>>>>"+indice);
//                        indice++;
//                    } else
//                        break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


    public static void fileGeneretad(int tamanho) {
        int count = 0;
        try {
            FileWriter fw = null;
            for (int i = 0; i < 10; i++) {
                fw = new FileWriter("arquivo" + i + ".txt");
                for (int j = 0; j < tamanho; j++) {
                    Random r = new Random();
                    if (j == tamanho - 1)
                        fw.write(r.nextInt(1000)+ "\t");
                    else
                        fw.write(r.nextInt(1000) + "\n");
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
}
