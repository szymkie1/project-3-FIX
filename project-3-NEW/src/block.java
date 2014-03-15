import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class block{

	int row=0;
	int column=0;
	int width;
	int height;
	char direction;
	int size;
	int[] arrayRowPositions;
	int[] arrayColumnPositions;
	int rowSize;
	int columnSize;
	char letter;
	ArrayList<locations> locs;
	int rowIter=0;
	int columnIter=0;


	public void setRowSize(int y){
		rowSize=y;
	}

	public void setColumnSize(int z){
		columnSize=z;
	}

	public void setRow(int r){
		row=r;
	}

	public int getRow(){
		return row;
	}

	public void setColumn(int c){
		column=c;
	}

	public int getColumn(){
		return column;
	}

	public void setWidth(int w){
		width=w;
	}

	public int getWidth(){
		return width;
	}

	public void setHeight(int h){
		height=h;
	}

	public int getHeight(){
		return height;
	}

	public void setDirection(char d){
		direction=d;
	}

	public char getDirection(){
		return direction;
	}

	public void setSize(){
		size=width*height;
	}

	public int getSize(){
		return size;
	}

	public void setLetter(char le){
		letter=le;
	}

	public char getLetter(){
		return letter;
	}


	public void setLocations(){
		locs = new ArrayList<locations>();




		  for(int s=0; s<size; s++){





			locations temp = new locations();



			if(s==0){

				temp.setX(row);
				temp.setY(column);
				rowIter++;
				columnIter++;

			}
			else{
				if(rowIter < height){
					temp.setX(row + rowIter);
						}
				else{
					temp.setX(row);
				}
				if(columnIter < width){
					temp.setY(column + columnIter);
				}
				else{
					temp.setY(column);
				}


			}

			locs.add(temp);


		  }

	}

	public ArrayList<locations> getLocations(){
		return locs;
	}




}
