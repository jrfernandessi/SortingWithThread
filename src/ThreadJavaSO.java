import java.util.ArrayList;

public class ThreadJavaSO extends Thread {
	
	public static final int PRODUTOR = 0;
	public static final int CONSUMIDOR = 1;
	public static final int MAX = 10;
	
	private int tipo;
	
	private ArrayList<String> lista;
	private boolean running;
	private int id;
	private static int counter = 0;
	
	public ThreadJavaSO( ArrayList<String> lista, int tipo ) {
		super();
		this.lista = lista;
		this.running = false;
		this.id = counter++;
		this.tipo = tipo;
	}

	public void run(){
		
		running = true;
		
		while( running ){
		
			//lista identifica a regiao critica
			if( tipo == PRODUTOR ){
				
				synchronized (lista) {
					
					if( lista.size() < MAX ) {
						produzir();
						lista.notifyAll();
					}
					else{
						System.out.println( "Lista cheia. Dormindo ...");
						
						try {
							lista.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					 
				}
				
			} else {
				
				synchronized ( lista ) {
					
					if( lista.size() > 0 ) {
						String texto = consumir();
						System.out.println( "Thread " + id + " - " + texto );
						lista.notifyAll();
					}
					else{
						try {
							lista.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				try {
					Thread.sleep( 300 );
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}
				 
			}
				
		}
		
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private void produzir(){
		String texto = "" + System.currentTimeMillis();		
		lista.add( texto );
	}
	
	private String consumir(){
		String texto = "";
		texto = lista.remove( 0 );
		return texto;
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> lista = new ArrayList<String>();
		
		ThreadJavaSO threadProdutor1 = new ThreadJavaSO( lista, PRODUTOR );
		ThreadJavaSO threadConsumidor1 = new ThreadJavaSO( lista, CONSUMIDOR );
		ThreadJavaSO threadConsumidor2 = new ThreadJavaSO( lista, CONSUMIDOR );
		ThreadJavaSO threadConsumidor3 = new ThreadJavaSO( lista, CONSUMIDOR );
		ThreadJavaSO threadConsumidor4 = new ThreadJavaSO( lista, CONSUMIDOR );
		ThreadJavaSO threadConsumidor5 = new ThreadJavaSO( lista, CONSUMIDOR );
		
		threadProdutor1.start();
		
		try {
			Thread.sleep( 500 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadConsumidor1.start();
		threadConsumidor2.start();
		threadConsumidor3.start();
		threadConsumidor4.start();
		threadConsumidor5.start();
		
		threadConsumidor1.setRunning( false );
		
		
		
	}

}
