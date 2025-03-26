import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class Hilos{
	public static void main(String args[]) throws InterruptedException{
		(new Thread(new HelloRunnable())).start();
		(new HelloThread()).start();
		new ThreadDemo();
		String info [] = {"los caballos comen avena","los caballos comen?","los corderos comen hiedras"};
		for(int i=0;i<info.length;i++){
			Thread.sleep(2000);
			System.out.println(info[i]);
		}
		for(int i=0;i<info.length;i++){
			try{
				Thread.sleep(2000);
			}catch(InterruptedException e){
				return;
			}
			System.out.println(info[i]);
		}
		//nuevo codigo 
		PrioridadMultiple hilo1 = new PrioridadMultiple();
		PrioridadMultiple hilo2 = new PrioridadMultiple();
		hilo1.setPriority(Thread.MAX_PRIORITY);
		hilo2.setPriority(Thread.MIN_PRIORITY);
		hilo1.start();
		hilo2.start();
		try{
			hilo1.join();
			hilo2.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

class HelloRunnable implements Runnable{
	public void run(){
		System.out.println("dentro de run HelloRunnable");
	}
}

class HelloThread extends Thread{
	public void run(){
		System.out.println("dentro de run HelloThread");
	}
}

class ThreadDemo implements Runnable{
	ThreadDemo(){
		Thread hiloActual = Thread.currentThread();
		Thread t = new Thread(this,"hilo admin");
		System.out.println("referencia a hilo actual "+hiloActual);
		System.out.println("hilo creado "+t);
		t.start();
	}
	public void run(){
		System.out.println("metodo run");
	}
}

class PrioridadMultiple extends Thread{
	public void run(){
		System.out.println("nombre de Hilo actual "+Thread.currentThread().getName());
		System.out.println("prioridad de hilo actual "+Thread.currentThread().getPriority());

	}
}
