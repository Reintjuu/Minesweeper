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

		for (int i = 0; i < field.length; i++)
		{
			for (int j = 0; j < field[i].length; j++)
			{
				string += field[i][j] + "  ";
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

		for (int i = 0; i < field.length; i++)
		{
			for (int j = 0; j < field[i].length; j++)
			{
				if (currentMine < mines.length && currentPosition == mines[currentMine])
				{
					field[i][j] = 9;

					// Find neighbors and increase their number if they're not a mine.
					for (int k = -1; k < 2; k++)
					{
						for (int l = -1; l < 2; l++)
						{
							if (k == 0 && l == 0)
							{
								continue;
							}

							if (k + i >= 0 && k + i < field.length && l + j >= 0 && l + j < field[i].length)
							{
								if (field[i + k][j + l] != 9)
								{
									field[i + k][j + l]++;
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