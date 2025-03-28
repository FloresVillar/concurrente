import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//===============================================================
public class Hilos{
	//-------------------------------------------------------
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
		PruebaDaemon d1 = new PruebaDaemon();
		PruebaDaemon d2 = new PruebaDaemon();
		d1.setDaemon(true);
		d1.start();//no asignar Daemon luego de inciiar
		d2.start();
		try{
			d1.join();
			d2.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
//===============================================================
class HelloRunnable implements Runnable{
	//----------------------------------
	public void run(){
		System.out.println("dentro de run HelloRunnable");
	}
}
//================================================================
class HelloThread extends Thread{
	//----------------------------------
	public void run(){
		System.out.println("dentro de run HelloThread");
	}
}
//================================================================
class ThreadDemo implements Runnable{
	ThreadDemo(){
		Thread hiloActual = Thread.currentThread();
		Thread t = new Thread(this,"hilo admin");
		System.out.println("referencia a hilo actual "+hiloActual);
		System.out.println("hilo creado "+t);
		t.start();
	}
	//----------------------------------
	public void run(){
		System.out.println("metodo run");
	}
}
//===========================================================
class PrioridadMultiple extends Thread{
	//-----------------------------------
	public void run(){
		System.out.println("nombre de Hilo actual "+Thread.currentThread().getName());
		System.out.println("prioridad de hilo actual "+Thread.currentThread().getPriority());

	}
}
//============================================================
class PruebaDaemon extends Thread{
	//---------------------------------
	public void run(){
		if(Thread.currentThread().isDaemon()){
			System.out.println("el hilo es daemon");
		}else{
			System.out.println("el hilo no es daemon");
		}
	}

}
//==============================================================
class MensajeDormido {

    MensajeDormido(){
        String mensaje [] = {"comer avena","pueden comer avena","nananan"};
        for(String i : mensaje){
            try{
            Thread.sleep(4000);}
            catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(i);
        }

    }
}
//=============================================================
class SumaFinita{
    double[] stotal;
    public void inicio(){
        int n = 10000,h = 12, seg = (int)(n/h) ,a ,b; 
        trabajo[] htrab = new trabajo[90]; 
        Thread[] t = new Thread[90];
        stotal  = new double[90];
        for (int i = 1; i <= h; i++) {
            a = (i-1)*seg+1;
            b = (i)*seg;
            htrab[i] = new  trabajo(a,b,i); 
            t[i] = new Thread(htrab[i]);
            t[i].start();
        } 
        try {
            for (int i = 1; i <= h; i++) {
                t[i].join();
            }  
        } catch (Exception e) {} 
        double total=0;
        for (int i = 1; i <= h; i++) {
            total = total +  stotal[i];
        }
        System.out.println("TOTAL: "+total);  

    }

class trabajo extends Thread{
        int ini,fin,tmp;
        double sum=0;
        public trabajo(int ini_,int fin_,int tmp_){
            ini=ini_;fin=fin_;tmp=tmp_;}
        public void run(){
            for (int i = ini; i <= fin; i++) {
                for (int j = 0; j < 100000; j++) {
                    sum = sum + Math.sin(i);
                } 
            } 
            stotal[tmp]=sum;
            System.out.println("hilo nro:" + tmp + "  tiene rpta:"+ sum);            
        }            
    }
}
//==================================================================
class Suma{
    public int sum[] = new int[40];
    void inicio() {
        int N = 100000000;
        int H = 6;
        int d = (int) ((N) / H);
        Thread todos[] = new Thread[40];
        for (int i = 0; i < (H - 1); i++) {
            todos[i] = new tarea0101((i * d + 1), (i * d + d), i);
            todos[i].start();
        }
        //Thread Hilo;
        todos[H - 1] = new tarea0101(((d * (H - 1)) + 1), N, H - 1);
        todos[H - 1].start();

        for (int i = 0; i < H; i++) {
            try {
                todos[i].join();
            } catch (InterruptedException ex) {
                System.out.println("error" + ex);
            }
        }
        int sumatotal = 0;
        for (int i = 0; i < H; i++) {
            sumatotal = sumatotal + sum[i];
        }
        System.out.println("suma total:" + sumatotal);
    }

    public class tarea0101 extends Thread {
        public int max, min, id;
            tarea0101(int min_, int max_, int id_) {
            max = max_;
            min = min_;
            id = id_;
            System.out.println("id" + id + " min: " + min_ + " max " + max_);
        }
        public void run() {
            int suma = 0;
            for (int i = min; i <= max; i++) {
                suma = suma + i;
//                try {
//                    sleep(1);
//                } catch (InterruptedException ex) {
//                    System.out.println("error " + ex);
//                }
            }
            sum[id] = suma;
            System.out.println("id" + id + " suma:" + suma);
        }
    }

}
//=====================================================================
class SumaBig{
    public BigInteger sum[] = new BigInteger[40];
    void inicio() {
        int N = 100000000;
        int H = 9;
        int d = (int) ((N) / H);
        Thread todos[] = new Thread[40];//array de hilos
        for (int i = 0; i < (H - 1); i++) {
            todos[i] = new tarea0101((i * d + 1), (i * d + d), i);// run 
            todos[i].start();
        }
        //Thread Hilo;
        todos[H - 1] = new tarea0101(((d * (H - 1)) + 1), N, H - 1);//el ultimo
        todos[H - 1].start();

        for (int i = 0; i < H; i++) {
            try {
                todos[i].join(); //esperando a que cada hilo termine
            } catch (InterruptedException ex) {
                System.out.println("error" + ex);
            }
        }
        BigInteger sumatotal = 0;
        for (int i = 0; i < H; i++) {
            sumatotal = sumatotal + sum[i]; //suma total
        }
        System.out.println("suma total:" + sumatotal);
    }

    public class tarea0101 extends Thread {
        public int max, min, id;
            tarea0101(int min_, int max_, int id_) {
            max = max_;
            min = min_;
            id = id_;
            System.out.println("id" + id + " min: " + min_ + " max " + max_);
        }
        public void run() {
            BigInteger suma = 0;
            for (BigInteger i = min; i <= max; i++) {
                suma = suma + i;
//                try {
//                    sleep(1);
//                } catch (InterruptedException ex) {
//                    System.out.println("error " + ex);
//                }
            }
            sum[id] = suma;
            System.out.println("id" + id + " suma:" + suma);
        }
    }

}
