/* 
 * ****************************************************************************
 * Robotarm v3 - ROBOT BASE EXAMPLES
 * ****************************************************************************
 * Example: Robotarm uart example 2
 * Author(s): Hein wielink
			  Huy Nguyen 
 * ****************************************************************************
 * Description:
 * This sample program shows how to use the Robotarm Library functions to receive
 * data with the serial interface. It is a "Question and Answer" program. 
 *
 * ############################################################################
 * The Robot does NOT move in this example! You can simply put it on a table
 * next to your PC and you should connect it to the PC via the USB Interface!
 * ############################################################################
 * ****************************************************************************
 */
 
/*****************************************************************************/
#include "RobotArmBaseLib.h"// The Robotarm Robot Library.
								// Always needs to be included!

int main(void)
{
	
	
	initRobotBase(); // Always call this first! The Processor will not work correctly otherwise.
	
	Servo_Power_And_Start();  //Use this function to power the servos and set them in the center position. When you want to power off the servos, you need to call function Power_Off_Servos();  

	mSleep(1000);
	


	
	uint8_t bytesToReceive = 1;

	char receiveBuffer[bytesToReceive]; 
	receiveBytes(bytesToReceive); 
	waitUntilReceptionComplete(); 
	copyReceivedBytesToBuffer(&receiveBuffer[0]);
	
	int max, min, max7, min7 ,displace;
	max = 1000;
	min = -1000;
	max7 = 700;
	min7 = -700;
	displace= 100;
	
	
	int position [6];
	
	position[0] = 0;
    position[1] = 0;
	position[2] = 0;
    position[3] = 0;
    position[4] = 0;
    position[5] = 0;
     
     
 

while (receiveBuffer[0] != 'x')
{
		
	mSleep(800);
	setLEDs(0b0000);
	
	if(receiveBuffer[0] == 'a' ) 
	{
		setLEDs(0b0001);
		if(position[0] < max7)
		{
		position[0] = position[0] + displace;
		//system
		s_Move(1, position[0], 3);	
		}
		else
		s_Move(1, position[0], 3);
	}
		
	else if(receiveBuffer[0] == 'b') 
	{
		setLEDs(0b0001);
		if(position[0] > min7)
		{
		position[0] = position[0] - displace;
		s_Move(1, position[0],3);	
		}
		else 
		s_Move(1, position[0],3);
	}
		// new code
		
	else if(receiveBuffer[0] == 'c') 
	{
		setLEDs(0b0001);
		if(position[1] <= max7)
		{
		position[1] = position[1] + displace;
		s_Move(2, position[1],3);	
		}
		else 
		s_Move(2, position[1],3);	
	}
	
	else if(receiveBuffer[0] == 'd') 
	{
		setLEDs(0b0001);
		if (position[1] > min7)
		{
		position[1] = position [1] - displace;
		s_Move(2, position[1],3);
		}
		else 
		s_Move(2, position[1],3);
	}
		
	
	else if(receiveBuffer[0] == 'e') 
	{
		setLEDs(0b0001);
		if (position[2] < max)
		{
		position[2] = position [2] + displace;
		s_Move(3, position [2],3);	
		}
		else
		s_Move(3, position [2],3);	
	}
		
	else if(receiveBuffer[0] == 'f') 
	{
		setLEDs(0b0001);
		if (position[2] > min)
		{
		position[2] = position [2] - displace;
		s_Move(3, position[2], 3);	
		}
		else 
		s_Move(3, position[2], 3);	
	}
		
	else if(receiveBuffer[0] == 'g') 
	{
		setLEDs(0b0001);
		if (position[3] < max)
		{
		position[3] = position [3] + displace;
		s_Move(4, position [3],3);	
		}
		else 
		s_Move(4, position [3],3);	
	}
		
	else if(receiveBuffer[0] == 'h') 
		{
		setLEDs(0b0001);
		if (position[3] > min)
		{
		position[3] = position [3] - displace;
		s_Move(4, position[3],3);	
		}
		else 
		s_Move(4, position[3],3);
	}
	
	else if(receiveBuffer[0] == 'i') 
	{
		setLEDs(0b0001);
		if (position[4] < max)
		{
		position[4] = position [4] + displace;
		s_Move(5, position [4],3);	
		}
		else 
		s_Move(5, position [4],3);
	}
	else if(receiveBuffer[0] == 'j') 
	{
		setLEDs(0b0001);
		if (position[4] > min)
		{
		position[4] = position [4] - displace;
		s_Move(5, position[4],3);	
		}
		else 
		s_Move(5, position[4],3);
	}
	else if(receiveBuffer[0] == 'k') 
	{
		setLEDs(0b0001);
		if (position[5] < max)
		{
		position[5] = position [5] + displace;
		s_Move(6, position[5],3);	
		}
		else 
		s_Move(6, position[5],3);
	}
		
	else if(receiveBuffer[0] == 'l') 
	{
		setLEDs(0b0001);
		if (position [5] > min)
		{
		position[5] = position [5] - displace;
		s_Move(6, position[5],3);	
		}
		else 
		s_Move(6, position[5],3);	
	}
		

		
		
	setLEDs(0b1111);
	

	receiveBytes(bytesToReceive); 
	waitUntilReceptionComplete(); 
	copyReceivedBytesToBuffer(&receiveBuffer[0]);

    
	
}	

	return 0;
}




