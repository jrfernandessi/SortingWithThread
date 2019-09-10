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
//        System.out.println(file);


        while (true) {
            try {
                String value;


                synchronized (Principal.v) {
                    value = line.readLine();
                    if (value != null) {
//
//                        System.out.println(">>>>>>>>>>>>> Indice: " + Principal.indice);

//                        System.out.println(">>>>>>>>>>>>> " + file);

                        Principal.v[Principal.indice] = Integer.parseInt(value.trim());
                        Principal.indice++;

//                    System.out.println(">>>>>>>>>>>>>>>>>>"+indice);

                    } else {
//                        notifyAll();
                        System.out.println(">>>>>>>>>> terminou "+ file);
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
