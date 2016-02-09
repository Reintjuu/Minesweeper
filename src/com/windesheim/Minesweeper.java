package com.windesheim;

import java.util.Random;

/**
 * Created by Reinier on 5-2-2016.
 */

public class Minesweeper
{
	private int[][] field;
	private int width;
	private int height;
	private int minesAmount;

	public Minesweeper(int width, int height, int minesAmount)
	{
		this.width = width;
		this.height = height;
		this.minesAmount = minesAmount;
		field = new int[height][width];
	}

	public String toString()
	{
		String string = "";

		for (int y = 0; y < field.length; y++)
		{
			for (int x = 0; x < field[y].length; x++)
			{
				string += field[y][x] + "  ";
			}
			string += "\n";
		}

		return string;
	}

	public void generateField()
	{
		int[] mines = new Random().ints(0, width * height).distinct().limit(minesAmount).sorted().toArray();
		int currentPosition = 0;
		int currentMine = 0;

		for (int y = 0; y < field.length; y++)
		{
			for (int x = 0; x < field[y].length; x++)
			{
				if (currentMine < mines.length && currentPosition == mines[currentMine])
				{
					field[y][x] = 9;

					// Find neighbors and increase their number if they're not a mine.
					for (int yOffset = -1; yOffset < 2; yOffset++)
					{
						for (int xOffset = -1; xOffset < 2; xOffset++)
						{
							if (yOffset == 0 && xOffset == 0)
							{
								continue;
							}

							if (y + yOffset >= 0 && y + yOffset < field.length && x + xOffset >= 0 && x + xOffset < field[y].length)
							{
								if (field[y + yOffset][x + xOffset] != 9)
								{
									field[y + yOffset][x + xOffset]++;
								}
							}
						}
					}

					currentMine++;
				}

				currentPosition++;
			}
		}
	}
}