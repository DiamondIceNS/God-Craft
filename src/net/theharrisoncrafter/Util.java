package net.theharrisoncrafter;

public class Util {
	public static boolean isParsableToInt(String i){
		try
		{
			Integer.parseInt(i);
			return true;
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
	}
}
