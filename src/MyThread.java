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
                Integer value = Integer.parseInt((line.readLine().trim()));


                synchronized (Principal.v) {
                    if (value != null) {
//
//                        System.out.println(">>>>>>>>>>>>> Indice: " + Principal.indice);

//                        System.out.println(">>>>>>>>>>>>>" + file);
                        Principal.v[Principal.indice] = value;
                        Principal.indice++;

//                    System.out.println(">>>>>>>>>>>>>>>>>>"+indice);

                    } else {

                        break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {

            }
        }


    }
}
