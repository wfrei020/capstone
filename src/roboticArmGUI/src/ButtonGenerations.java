/*
 *   Author: Walter Freire              
 *   Student Number: 6399015                       
 *   Course: ELG4912          
 *   Interface
 * */
package roboticArmGUI.src;


//import java.awt.*;
import javax.swing.*;
//import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
/*
 * 
 * 
 * */

@SuppressWarnings("serial")
public class ButtonGenerations extends JButton{
//==================================================================================================================
// Instance Variables
//==================================================================================================================
 
 // private JButton actionButton = new JButton();
  
  private MouseListener listener;
  
  private String buttonLabel;
  
  //private static int i = 0;
  
//==================================================================================================================
// Constructor
//==================================================================================================================
  
  public ButtonGenerations(MouseListener listener , String buttonLabel){
    super(buttonLabel);
    this.buttonLabel = buttonLabel;
    this.listener = listener;
// setOpaque(false);
//setContentAreaFilled(false);
//setBorderPainted(false);
//pickColor();
  }
//==================================================================================================================
// Picking Color
//==================================================================================================================
  
  /*public void pickColor(){
    if(this.i == 0){
      setBackground(Color.YELLOW);
      i++;
    }
    else if(this.i == 1){
      setBackground(Color.RED);
      i++;
    }}
    */
//==================================================================================================================
// adding listener
//==================================================================================================================
   
  public void addListener(){this.addMouseListener(this.listener);}
//==================================================================================================================
// getting label
//==================================================================================================================

  public String getString(){return buttonLabel;} 
}