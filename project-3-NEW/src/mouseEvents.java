import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.*;

public class mouseEvents{

	static MouseEvent[] getEvents = new MouseEvent[2];
	static int count=0;

	public static void setTwoEvents(MouseEvent e){


		if(count==0){
			getEvents[0]=e;
			getEvents[1]=null;
			count++;
		}
		else if(count==1){
			getEvents[1]=e;
			count=0;
		}

		}

	public static MouseEvent[] getTwoEvents(){
		return getEvents;
	}

	public static int getCurrentClick(){
		return count;
	}



}
