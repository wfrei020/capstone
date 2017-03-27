	/*
	 *   Author1: Walter Freire; Student Number: 6399015; Course: ELG4912; Interface
	 *   Author2: Jason Edwards; Independent Developer; Macros
	 * */
package roboticArmGUI.src;
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.*;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	/*Class : Controller UI : This is the Main screen in the Interface
	 * update: So far the base works perfectly with the new modification on the Jframe using PANELS   reference to that
	 * 
	 * */
	
	@SuppressWarnings("serial")
	public class ControllerUI extends JFrame implements MouseListener{
		   
	//==================================================================================================================
	// Instance Variables; ASCII codes for letters recognized by C code firmware
	//==================================================================================================================
	  final int rotateLeft = 107;	//k : controls the base (left rotation)
	  final int rotateRight = 108;	//l : controls the base (right rotation)
	  final int moveMotor1Up = 102;		//e : controls the top arm joint
	  final int moveMotor1Down = 101;	//f
	  final int moveMotor2Up = 103;		//g : controls the middle joint
	  final int moveMotor2Down = 104;	//h
	  final int moveMotor3Up = 106;		//i : controls the lower joint
	  final int moveMotor3Down = 105;	//j
	  final int closeGrip = 97;   //a : controls the grip
	  final int openGrip = 98;    //b
	  final int rotateWristRight = 99;  //c : controls the wrist
	  final int rotateWristLeft = 100;  //d
	  final int macroReady = 109;//m returns arm to starting position
	  final int macroMiddle = 110;//n reaches forward to contact
	  final int macroRight = 112;//p reaches right to contact
	  final int macroLeft = 113;//q reaches left to contact
	  final int macroDrawLeft = 114;//r left to contact then draw to center
	  final int macroDrawRight = 115;//s right to contact then draw to center
	  final int macroDrawMiddle = 116;//t forward to contact then draw to center
	  final int macroTest = 117; //u Test macro for new stuff
		
	  private ButtonGenerations[] homeButtons = new ButtonGenerations[20];//[x]=#buttons
	 

	  
	  private JFrame programGUI;
	  
	  private int buttonIcr = 0;
	 
	  private int firstTimeStart = 0;
	  
	  private boolean[] enteredB  = new boolean[20];//[x]=#buttons
	  
	  private boolean hoveringOverButton ;
	  
	  private boolean mouseclick;
	  
	  private boolean exited;
	    
	  private boolean runningProgram;
	  
	  
	  @SuppressWarnings("rawtypes")
	private LinkedQueue commands;
	 

	  
	//==================================================================================================================
	//Constructor
	//==================================================================================================================
	  
	  @SuppressWarnings("rawtypes")
	public ControllerUI(String fileName , LinkedQueue queue){
		commands = queue;
	    programGUI = new JFrame("Robot Controller Interface");
	    programGUI.getContentPane().add(homeWindow());
	  
	    programGUI.setSize( 1000,500 );
	    programGUI.setVisible( true ); 
	    programGUI.setResizable(true);
	    programGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    programGUI.setBackground(new Color(195, 209, 223));
	    setButtonColor(false,null);
	    setButtonFont();
	    while(true){
	    	runProgram();
	    }
	    
	  }
	  
	
	/*==================================================================================================================
	* The system will Run forever until the user closes the window;
	* The run Program will check if the cursor is hovering over a button and check if it has not exited
	* If time over button is sufficient a new action by the arm is initiated.
	*==================================================================================================================
	*/
	  
	  
	  public void runProgram(){
	   mouseclick = false;
	   exited = false;
	   runningProgram = true;
	    while(runningProgram){
	      hoveringOverButton = true;
	     
	      try{
	    	  while(hoveringOverButton && !exited ){
	    		  int runningTime = currentTime();
	    		  hoveringOverButton = generateNewAction(firstTimeStart, runningTime);  
	    	  }}
	    	catch(Exception ex1){}
	      }
	  }
	
	/*==================================================================================================================
	//Using time to generate a newAction after being in a button for a certain time 
	//==================================================================================================================
	*/  
	  @SuppressWarnings("unchecked")
	public boolean generateNewAction(int timestarted, int runningTime){
	    int j;
	
	    if(runningTime - timestarted >1500 || mouseclick == true ){
	    	mouseclick = false;
	    	//the for loop needs a possible action for all buttons
	    	//i.e. 16 buttons requires if statements for j=0 to 15
	      for(j = 0 ; j < homeButtons.length  ; j++){
	    	  this.hoveringOverButton = true;
	        if(enteredB[j]){
	          if(j ==0){
	          commands.enqueue( rotateRight);
	          setButtonColor(true,j);
	          this.firstTimeStart = currentTime();
	        	  }
	          if(j ==1){
		          commands.enqueue( rotateLeft);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==2){
		          commands.enqueue( rotateWristRight);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==3){
		          commands.enqueue( rotateWristLeft);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==4){
		          commands.enqueue( closeGrip);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==5){
		          commands.enqueue( openGrip);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==6){
		          commands.enqueue( moveMotor1Up);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==7){
		          commands.enqueue( moveMotor1Down);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	                    
	          if(j ==8){
		          commands.enqueue( moveMotor2Up);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          
	          if(j ==9){
		          commands.enqueue( moveMotor2Down);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==10){
		          commands.enqueue( moveMotor3Up);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==11){
		          commands.enqueue( moveMotor3Down);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==12){
		          commands.enqueue( macroReady);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==13){
		          commands.enqueue( macroTest);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==14){
		          commands.enqueue( macroRight);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==15){
		          commands.enqueue( macroDrawRight);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==16){
		          commands.enqueue( macroLeft);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==17){
		          commands.enqueue( macroDrawLeft);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==18){
		          commands.enqueue( macroMiddle);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          if(j ==19){
		          commands.enqueue( macroDrawMiddle);
		          setButtonColor(true,j);
		          this.firstTimeStart = currentTime();
        	  }
	          }
	        }
	     }
	    if(this.exited){
	      this.hoveringOverButton = false;
	      setButtonColor(false,null);
	    }
	    return this.hoveringOverButton;
	  }
	  
	  public void settingPanel(JPanel panel){
	      programGUI.getContentPane().removeAll();
	      programGUI.getContentPane().add(panel);
	      programGUI.revalidate();
	  
	  }
	//==================================================================================================================
	//Used to generate the main Frame
	//==================================================================================================================
	  public JPanel homeWindow(){
	    JPanel joints = new JPanel();
	    joints.setLayout(new GridLayout(2,3,5,5));
	    joints.setBackground(new Color(195, 209, 223));
	    //the order of these methods ultimately determines GUI button assignment
	    joints.add(jointAction("Base Right" , "Base Left"));
	    joints.add(jointAction("Wrist Right" , "Wrist Left"));
	    joints.add(jointAction("Grip Open" , "Grip Close"));
	    joints.add(jointAction("Upper Elbow Up" , "Upper elbow Down"));
	    joints.add(jointAction("Middle Elbow Up" , "Middle Elbow Down"));
	    joints.add(jointAction("Lower Elbow Up" , "Lower Elbow Down"));
	    joints.add(jointAction("*Ready" , "*Test"));
	    joints.add(jointAction("*Right" , "*Draw Right"));
	    joints.add(jointAction("*Left", "*Draw Left"));
	    joints.add(jointAction("*Middle","*Draw Middle"));
	   // add(joints);
	
	    return joints;
	  }
	//==================================================================================================================
	//Used to generate the buttons
	//==================================================================================================================
	  public JPanel jointAction(String movement1 , String movement2){
	    JPanel jointPanel = new JPanel();
	   
	    jointPanel.setLayout(new GridLayout(2,1,3,3));
	    jointPanel.setBackground(new Color(195, 209, 223));
	    homeButtons[buttonIcr] = new ButtonGenerations(this , movement1);
	    //button assignment done here
	    jointPanel.add(homeButtons[buttonIcr]);
	    homeButtons[buttonIcr].addListener();
	    buttonIcr++;
	    homeButtons[buttonIcr]= new ButtonGenerations(this, movement2);
	    jointPanel.add(homeButtons[buttonIcr]);
	    homeButtons[buttonIcr].addListener();
	    buttonIcr++;
	    // jointPanel.setBackground(Color.YELLOW);
	    return jointPanel;
	  }
	//==================================================================================================================
	//Method used to detect when a cursor has entered a button
	//==================================================================================================================
	  public void mouseEntered(MouseEvent e){
	    this.exited = false;
	    this.hoveringOverButton = true;
	    this.firstTimeStart = currentTime();
	    int i = 0;
	    for(i=0 ; i<enteredB.length ; i++){
	      if(e.getComponent() == homeButtons[i]){
	        this.enteredB[i] = true;  
	      }}}
	//==================================================================================================================
	//Method used to detect when a cursor has exited a button
	//==================================================================================================================
	  public void mouseExited(MouseEvent e){
	    this.firstTimeStart = 0;
	    this.exited = true;
	    int i = 0;
	    while(i<homeButtons.length){
	      enteredB[i] = false;
	      i++;
	    }}
	  public void mousePressed(MouseEvent e){}
	  public void mouseReleased(MouseEvent e){}
	  public void mouseClicked(MouseEvent e){
		  mouseclick = true;
		    int i = 0;
		    for(i=0 ; i<enteredB.length ; i++){
		      if(e.getComponent() == homeButtons[i]){
		        this.enteredB[i] = true;  
		      }}
		      
	  }
	  
	  
	//==================================================================================================================
	//Method used to return the current time
	//==================================================================================================================
	  public int currentTime() {
	    int newTime;
	    Calendar cal = Calendar.getInstance();
	    cal.getTime();
	    SimpleDateFormat sdfHours = new SimpleDateFormat("HH");
	    SimpleDateFormat sdfMins = new SimpleDateFormat("mm");
	    SimpleDateFormat sdfSecs = new SimpleDateFormat("ss");
	    SimpleDateFormat sdfMilli = new SimpleDateFormat("S");
	    newTime = Integer.parseInt(sdfHours.format(cal.getTime()))*60*60*1000 + Integer.parseInt(sdfMins.format(cal.getTime()))*60*1000 + Integer.parseInt(sdfSecs.format(cal.getTime()))*1000 + Integer.parseInt(sdfMilli.format(cal.getTime()));
	    return newTime;
	  }
	  
	//==================================================================================================================
	//Design buttons layout including colour and print and any pictures
	//==================================================================================================================
	//"Windows" light blue = RGB 195, 209, 223 = C3D1DF
	//Analogous red = RGB 242,176,144 = F2B090
	//Analogous deep blue = RGB 98,128,157 = 62809D
	public void setButtonColor(boolean buttonPressed, Integer myButtonID){
		if (buttonPressed==true){
		homeButtons[myButtonID].setBackground(new Color(242,176,144));
		
		}
		
		else if (buttonPressed==false){
		int counter;
		for(counter = 0 ; counter < homeButtons.length ; counter++){
			homeButtons[counter].setBackground(new Color(98,128,157));			
		}
		}
	}
	public void setButtonFont(){
		int counter;
		for(counter = 0 ; counter < homeButtons.length ; counter++){
			homeButtons[counter].setFont(new Font("Arial", Font.PLAIN , 30));
		}
	
	}
	
	  
	  
	//==================================================================================================================
	//Main method to initiate program for testing
	//==================================================================================================================
	  @SuppressWarnings("rawtypes")
	public static void main(String[] args){
	    String newTextFile;
	    newTextFile ="Data.txt";
		  LinkedQueue queue = new LinkedQueue();
	    new ControllerUI(newTextFile , queue); 
	  }
	  
	  
	  
// End of Class	  
	}