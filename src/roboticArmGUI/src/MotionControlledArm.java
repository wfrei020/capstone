package roboticArmGUI.src;
import java.io.IOException;


import com.fazecast.jSerialComm.*;


public class MotionControlledArm {

    public static void main(String[] args) throws IOException {
    	@SuppressWarnings("rawtypes")
		LinkedQueue commands = new LinkedQueue();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	 SerialPort[]  portarray = SerialPort.getCommPorts();
                	 
                	 int i = 0;
                	 while(i < portarray.length ){
                		 System.out.println(portarray[i].getDescriptivePortName());
                		 String ss[] = portarray[i].getDescriptivePortName().split(" ", 4);
                		 if(ss[0].equals("USB")){
                			 if(ss[1].equals("Serial")){
                				 if(ss[2].equals("Port")){break;}
                			 }
                		 }
                		 i++;
                	 }
                	 
                	(new SerialCommunication(commands)).connect(portarray[i].getSystemPortName());
                	
            		
                } catch (Exception e) {e.printStackTrace();
                }
            }
        });

        

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	  
                	  String newTextFile;
             	   newTextFile ="Data.txt";
             	   
             	   new ControllerUI(newTextFile, commands); 
                      
                    	
                    }
                catch (Exception e) {
                }
            }
        });

        thread1.start();
       
        thread2.start();
       
    }
}