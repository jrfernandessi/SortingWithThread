import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;


public class MyThread extends Thread {
    String file;

    public MyThread(String file) {

        this.file = file;
    }

    public void run() {
        LineNumberReader line = null;
        try {
            line = new LineNumberReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        while (true) {
            try {
                String value;


                synchronized (ParallelMergeSort.v) {
                    value = line.readLine();
                    if (value != null) {

                        if(ParallelMergeSort.indice<10000) {
                            ParallelMergeSort.v[ParallelMergeSort.indice] = Integer.parseInt(value.trim());
                            ParallelMergeSort.indice++;
                        }


                    } else {
                        break;

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
