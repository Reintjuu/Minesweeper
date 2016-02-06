package com.windesheim;

public class Main
{
	public static void main(String[] args)
	{
		Minesweeper m = new Minesweeper(9, 9, 10);
		m.generateField();
		System.out.println(m);
	}
}