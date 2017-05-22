package com.github.hnefatl.interactiondesign;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClicker extends MouseAdapter{
	
	boolean MarkerBool =false;
	public MouseClicker(){
		MarkerBool=true;
		System.out.println("2");
	}
        public void MousePressed(MouseEvent e){
        	
        }
        public void mouseClicked(MouseEvent e) {
        	if (MarkerBool==false){ return;}
        	System.out.println("3");
        	Float[] cords= new Float[2];
            cords[0] =  e.getX();
            cords[1] =  e.getY(); 
            EmbeddedProc nEmbed = new EmbeddedProc();
            System.out.println("3");
            nEmbed.placeMarker(cords);
            MarkerBool=false;
        }
        public void mouseEntered(MouseEvent e) {

        }
        public void mouseExited(MouseEvent e) {

        }        
        public void mouseReleased(MouseEvent e) {

        }


}
