import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.*;


@SuppressWarnings("serial")



public class gameGUI extends JFrame implements ActionListener{

	private JMenuBar topMenu;
	private JMenu gameMenu, helpMenu;
	private JMenuItem gReset, gExit, hHelp, hAbout;


	private JLabel bScoreLabel, bestScore, moveLabel, moves;
	private JButton hint, reset, solve;
	private level[] gameLevels = new level[12];
	private button gameButtons[][] = new button[6][6];


	public gameGUI(){



		super("Sliding Block Puzzles");

		setSize(500,350);

		File[] fileArray = fileSetup.setFiles();


		arrayContainer[] dispArrays = new arrayContainer[12];



		for(int numFiles = 0; numFiles < 1; numFiles++){
			gameLevels[numFiles] = new level();
			gameLevels[numFiles].initLevel(fileArray[numFiles]);
			gameLevels[numFiles].getBlocks();
			char[][] arrayTemp=gameLevels[numFiles].getArrayWithBlocks();
			dispArrays[numFiles] = new arrayContainer();
			dispArrays[numFiles].setArray(arrayTemp);

			}







































		topMenu = new JMenuBar();
		setJMenuBar(topMenu);

		gameMenu = new JMenu("Game");
		topMenu.add(gameMenu);

		helpMenu = new JMenu("Help");
		topMenu.add(helpMenu);

		gReset = new JMenuItem("Reset");
		gameMenu.add(gReset);
		gReset.setMnemonic(KeyEvent.VK_R);
		gReset.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.ALT_MASK));
		gReset.addActionListener(this);
		gameMenu.addSeparator();

		gExit = new JMenuItem("Exit");
		gameMenu.add(gExit);
		gExit.setMnemonic(KeyEvent.VK_E);
		gExit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_E, ActionEvent.ALT_MASK));
		gExit.addActionListener(this);


		hHelp = new JMenuItem("Help");
		helpMenu.add(hHelp);
		hHelp.setMnemonic(KeyEvent.VK_H);
		hHelp.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_H, ActionEvent.ALT_MASK));
		hHelp.addActionListener(this);
		helpMenu.addSeparator();

		hAbout = new JMenuItem("About");
		helpMenu.add(hAbout);
		hAbout.setMnemonic(KeyEvent.VK_A);
		hAbout.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.ALT_MASK));
		hAbout.addActionListener(this);

		board[] panels = new board[12];

		panels[0] = new board();

		panels[0].setLayout(new GridLayout(6,6));




		JPanel infoPanel = new JPanel();

		JPanel uiPanel = new JPanel();
		uiPanel.setLayout(new BoxLayout(uiPanel, BoxLayout.Y_AXIS));





		moveLabel = new JLabel("Move: ");
		infoPanel.add(moveLabel);
		moves = new JLabel("0");
		infoPanel.add(moves);

		hint = new JButton("Hint");
		infoPanel.add(hint);

		reset = new JButton("Reset");
		infoPanel.add(reset);

		solve = new JButton("Solve");
		infoPanel.add(solve);

		bScoreLabel = new JLabel("Best Score: ");
		infoPanel.add(bScoreLabel);
		bestScore = new JLabel("0");
		infoPanel.add(bestScore);


		char[][] convArray=dispArrays[0].getArray();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				gameButtons[i][j] = new button();

				panels[0].add(gameButtons[i][j]);
				gameButtons[i][j].setX(i);
				gameButtons[i][j].setY(j);
				String buttonString = Character.toString(convArray[i][j]);
				char buttonChar =convArray[i][j];
				gameButtons[i][j].setText(buttonString);
				int whichBlock=letterToBlock(buttonChar);
				gameButtons[i][j].setBlock(whichBlock);





			}
		}


		for(int a=0; a < 6; a++){
			for(int b=0; b<6; b++){
				gameButtons[a][b].addMouseListener(new buttonListener(gameButtons));

			}
		}











		uiPanel.add(infoPanel);
		uiPanel.add(panels[0]);


		add(uiPanel);










		setVisible(true);

	}


	public int letterToBlock(char a){
		int num=-1;
		if(a=='Z'){
			num=0;
		}
		else if(a=='A'){
			num=1;
		}
		else if(a=='B'){
			num=2;
		}
		else if(a=='C'){
			num=3;
		}
		else if(a=='D'){
			num=4;
		}
		else if(a=='E'){
			num=5;
		}
		else if(a=='F'){
			num=6;
		}
		else if(a=='G'){
			num=7;
		}
		else if(a=='.'){
			num=-1;
		}
		else{
			System.out.println("Problem Converting");
		}

		return num;
	}






	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == gReset){
			System.out.println("Reset Pressed");
		}
		else if(e.getSource() == gExit){
			System.out.println("Exiting");
			System.exit(0);
		}
		else if(e.getSource() == hHelp){
			JOptionPane.showMessageDialog(gameGUI.this,
					"Coming Soon","Help", JOptionPane.PLAIN_MESSAGE );
		}
		else if(e.getSource() == hAbout){
			JOptionPane.showMessageDialog(gameGUI.this,
					"Sliding Block Puzzles \n"+
				    "CS 342-Project 3 \n"+
					"Authors \n"+
				    "Joshua Rodriguez \n"+
					"Ryan Szymkiewicz \n"
						,"About", JOptionPane.PLAIN_MESSAGE );
		}
		else{
			System.out.println("Problem with menu button actions");
		}



	}

}





class buttonListener implements MouseListener{

	button[][] buttonArray;

	buttonListener(button[][] tempArray){
		buttonArray=tempArray;

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub


		MouseEvent[] currEvents;

		mouseEvents.setTwoEvents(e);

		if(mouseEvents.getCurrentClick() == 1){
			currEvents=mouseEvents.getTwoEvents();
			button currButton = (button)currEvents[0].getSource();
			System.out.println(currButton.getBlock());

		}
		else if(mouseEvents.getCurrentClick() == 0){
			currEvents=mouseEvents.getTwoEvents();
			button currButton1 = (button)currEvents[0].getSource();
			button currButton2 = (button)currEvents[1].getSource();

			if(currButton2.getBlock() != -1){
			System.out.println("You can't move a block if another block is in that location");	
			}

		}






	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}

class fileSetup{

	public static File[] setFiles(){

		File[] fileArray;

		fileArray = new File[12];

		fileArray[0] = new File("src/level1.data");
		fileArray[1] = new File("src/level2.data");
		fileArray[2] = new File("src/level3.data");
		fileArray[3] = new File("src/level4.data");
		fileArray[4] = new File("src/level5.data");
		fileArray[5] = new File("src/level6.data");
		fileArray[6] = new File("src/level7.data");
		fileArray[7] = new File("src/level8.data");
		fileArray[8] = new File("src/level9.data");
		fileArray[9] = new File("src/level10.data");
		fileArray[10] = new File("src/level11.data");
		fileArray[11] = new File("src/level12.data");

		for(int fileCount=0; fileCount < 12; fileCount++){
		try(FileInputStream fis = new FileInputStream(fileArray[fileCount])){
			System.out.println("Successful read");
			}
		catch(IOException e){
			System.out.println(e);
			System.out.println("Exiting game.");
			System.exit(0);
		}
		}

		return fileArray;
	}

}