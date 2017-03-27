package roboticArmGUI.src;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This version of the TwoWaySerialComm example makes use of the
 * SerialPortEventListener to avoid polling.
 *
 */

public class SerialCommunication {

	
	//Inserted -----------------------------------------------------------------------------------------------------
	@SuppressWarnings("rawtypes")
	private LinkedQueue commands;
	
	
	
	@SuppressWarnings("rawtypes")
	public SerialCommunication(LinkedQueue data) {
		super();
		commands = data;
	}
	
	//Inserted ------------------------------------------------------------------------------------------------------

	public void connect(String portName) throws Exception {
		
		
		
		
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			CommPort commPort = portIdentifier.open(this.getClass().getName(),
					2000);

			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(38400, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();
				serialPort.addEventListener(new SerialReader(in));
				serialPort.notifyOnDataAvailable(true);

				(new Thread(new SerialWriter(out,commands))).start();

			} else {
				System.out
						.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	/**
	 * Handles the input coming from the serial port. A new line character is
	 * treated as the end of a block in this example.
	 */
	public static class SerialReader implements SerialPortEventListener {
		private InputStream in;
		private byte[] buffer = new byte[1024];

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void serialEvent(SerialPortEvent arg0) {
			int data;
		
			buffer = new byte[1024];
			try {
				int len = 0;
				
				while ((data = in.read()) > -1) {
					if (data == '\n') {
						break;
					}
					buffer[len++] = (byte) data;
					
			//		System.out.print(data + "|" + ((byte) data) + " ");
				}
				
				System.out.println("\n\n-----------------------------------");
				
				StringBuilder sb = new StringBuilder();
			    for (byte b : buffer) {
			        sb.append(String.format("%02X ", b));
			    }

				System.out.println(new String(buffer, 0, len));

				//System.out.println("-----------------------------------");
				 
				 
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

	}

	/** */
	public static class SerialWriter implements Runnable {
		OutputStream out;
		@SuppressWarnings("rawtypes")
		LinkedQueue commands;
		public SerialWriter(OutputStream out, @SuppressWarnings("rawtypes") LinkedQueue commands) {
			this.out = out;
			this.commands = commands;
		}

		public void run() {
			try {
				
				while (true) {
					
					if(!commands.isEmpty()){
					
					this.out.write((int) commands.dequeue());
					
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

}
