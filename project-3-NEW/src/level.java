import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;


public class level{

	private int numLines=1;
	private int rows;
	private int columns;
	private char[][] inputArray;
	public ArrayList<block> blocks = new ArrayList<block>();
	private char[][] blockArray;



	public void initLevel(File currFile){

		try (FileInputStream fis = new FileInputStream(currFile)) {





			int currChar;
			while ((currChar = fis.read()) != -1) {
				if((char)currChar == '\n'){
					numLines++;
				}
			}



		} catch (IOException e) {
			System.out.println(e);
		}

		inputArray = new char[numLines][20];



		try (FileInputStream fis2 = new FileInputStream(currFile)) {

			int currChar2;
			int currLine=0;
			int currSpace=0;
			while ((currChar2 = fis2.read()) != -1) {
				inputArray[currLine][currSpace]=(char)currChar2;
				if((char)currChar2 != '\n'){

					currSpace++;
				}
				else if((char)currChar2 == '\n'){

					currLine++;
					currSpace=0;
				}
				else{
					System.out.println("Problem translating file to array");
				}

			}



		} catch (IOException e) {
			System.out.println(e);
		}


		rows = Character.getNumericValue(inputArray[0][0]);
		columns = Character.getNumericValue(inputArray[0][3]);


	}


	public int getNumRows(){
		return rows;
	}

	public int getNumColumns(){
		return columns;
	}

	public char[][] getInputArray(){
		return inputArray;
	}

	public ArrayList<block> getBlocks(){


		for(int i=1; i < numLines; i++){

			block temp = new block();
			temp.setRowSize(rows);
			temp.setColumnSize(columns);
			temp.setRow(Character.getNumericValue(inputArray[i][0]));
			temp.setColumn(Character.getNumericValue(inputArray[i][3]));
			temp.setWidth(Character.getNumericValue(inputArray[i][6]));
			temp.setHeight(Character.getNumericValue(inputArray[i][9]));
			temp.setDirection(inputArray[i][12]);
			temp.setSize();
			if(i==1){
				temp.setLetter('Z');
			}
			else{
				temp.setLetter((char)(63 + i));
			}
			System.out.println("Block:"+i);
			temp.setLocations();
			blocks.add(temp);

		  }



		return blocks;
	}


	public char[][] getArrayWithBlocks(){
		blockArray = new char[6][6];

		for(int r=0; r < 6; r++){
			for(int c=0; c < 6; c++){
				blockArray[r][c]='.';
			}
		}

		int xLoc;
		int yLoc;
		char charSet;
		for(int a=0; a < blocks.size(); a++){
			for(int b = 0; b < blocks.get(a).getLocations().size() ; b++){

				xLoc=blocks.get(a).getLocations().get(b).getX();
				yLoc=blocks.get(a).getLocations().get(b).getY();
				charSet=blocks.get(a).getLetter();


				blockArray[xLoc][yLoc]=charSet;

			}


		}



		return blockArray;
	}


}