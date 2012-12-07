package com.example.woordenvervoegen;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataExtracter {
	

	ArrayList<String> ikLijst = new ArrayList<String>(); //De lijst waar alle ik vervoegingen in komt te staan.
	ArrayList<String> jijLijst = new ArrayList<String>(); //De lijst waar alle jij vervoegingen in komt te staan.
	ArrayList<String> hijLijst = new ArrayList<String>(); //De lijst waar alle hij vervoegingen in komt te staan.
	ArrayList<String> wijLijst = new ArrayList<String>(); //De lijst waar alle wij vervoegingen in komt te staan.
	ArrayList<String> jullieLijst = new ArrayList<String>(); //De lijst waar alle jullie vervoegingen in komt te staan.
	ArrayList<String> zijLijst = new ArrayList<String>(); //De lijst waar alle zij vervoegingen in komt te staan.
	
	static ArrayList<String> ottLijst = new ArrayList<String>();
	static ArrayList<String> vttLijst = new ArrayList<String>();
	static ArrayList<String> ovtLijst = new ArrayList<String>();
	static ArrayList<String> vvtLijst = new ArrayList<String>();
	static ArrayList<String> otttLijst = new ArrayList<String>();
	static ArrayList<String> vtttLijst = new ArrayList<String>();
	static ArrayList<String> ovttLijst = new ArrayList<String>();
	static ArrayList<String> vvttLijst = new ArrayList<String>();
	
	public void extractData(String str)
	{   
		ikLijst.clear();
		jijLijst.clear();
		hijLijst.clear();
		wijLijst.clear();
		jullieLijst.clear();
		zijLijst.clear();
		
		//Definieer de regex om de woorden te vinden.
		String regexIk = "ik(\\s+([a-zA-Z-οφλόδ]+))+"; //ik(\\s+([a-zA-Z-οφλόδ]+)) geeft ik + woord terug. ik(\\s+([a-zA-Z-οφλόδ]+))+ geeft ik + alle vervolg woorden tot er een teken komt wat niet a-Z-οφλόδ is.
		String regexJij = "jij(\\s+([a-zA-Z-οφλόδ]+))+"; 
		String regexHij = "hij(\\s+([a-zA-Z-οφλόδ]+))+";
		String regexWij = "wij(\\s+([a-zA-Z-οφλόδ]+))+";
		String regexJullie = "jullie(\\s+([a-zA-Z-οφλόδ]+))+";
		String regexZij = "zij(\\s+([a-zA-Z-οφλόδ]+))+";

		// Compiles the pattern and obtains the matcher object.
		Pattern patternIk = Pattern.compile(regexIk);
		Pattern patternJij = Pattern.compile(regexJij);
		Pattern patternHij = Pattern.compile(regexHij);
		Pattern patternWij = Pattern.compile(regexWij);
		Pattern patternJullie = Pattern.compile(regexJullie);
		Pattern patternZij = Pattern.compile(regexZij);

		//Match de patterns met de string
		Matcher matcherIk = patternIk.matcher(str);		
		Matcher matcherJij = patternJij.matcher(str);	
		Matcher matcherHij = patternHij.matcher(str);		
		Matcher matcherWij = patternWij.matcher(str);
		Matcher matcherJullie = patternJullie.matcher(str);		
		Matcher matcherZij = patternZij.matcher(str);

		// Find every match and print it
		while (matcherIk.find() && matcherJij.find() && matcherHij.find() && matcherWij.find() && matcherJullie.find() && matcherZij.find()) 
		{

			System.out.println(matcherIk.group());
			System.out.println(matcherJij.group());
			System.out.println(matcherHij.group());
			System.out.println(matcherWij.group());
			System.out.println(matcherJullie.group());
			System.out.println(matcherZij.group());

			ikLijst.add(matcherIk.group());
			jijLijst.add(matcherJij.group());
			hijLijst.add(matcherHij.group());
			wijLijst.add(matcherWij.group());
			jullieLijst.add(matcherJullie.group());
			zijLijst.add(matcherZij.group());
		}

		
		ottLijst.clear();
		ottLijst.add(0, ikLijst.get(0));
		ottLijst.add(1, jijLijst.get(0));
		ottLijst.add(2, hijLijst.get(0));
		ottLijst.add(3, wijLijst.get(0));
		ottLijst.add(4, jullieLijst.get(0));
		ottLijst.add(5, zijLijst.get(0));
		
		vttLijst.clear();
		vttLijst.add(0, ikLijst.get(1));
		vttLijst.add(1, jijLijst.get(1));
		vttLijst.add(2, hijLijst.get(1));
		vttLijst.add(3, wijLijst.get(1));
		vttLijst.add(4, jullieLijst.get(1));
		vttLijst.add(5, zijLijst.get(1));
		
		ovtLijst.clear();
		ovtLijst.add(0, ikLijst.get(2));
		ovtLijst.add(1, jijLijst.get(2));
		ovtLijst.add(2, hijLijst.get(2));
		ovtLijst.add(3, wijLijst.get(2));
		ovtLijst.add(4, jullieLijst.get(2));
		ovtLijst.add(5, zijLijst.get(2));
		
		vvtLijst.clear();
		vvtLijst.add(0, ikLijst.get(3));
		vvtLijst.add(1, jijLijst.get(3));
		vvtLijst.add(2, hijLijst.get(3));
		vvtLijst.add(3, wijLijst.get(3));
		vvtLijst.add(4, jullieLijst.get(3));
		vvtLijst.add(5, zijLijst.get(3));
		
		otttLijst.clear();
		otttLijst.add(0, ikLijst.get(4));
		otttLijst.add(1, jijLijst.get(4));
		otttLijst.add(2, hijLijst.get(4));
		otttLijst.add(3, wijLijst.get(4));
		otttLijst.add(4, jullieLijst.get(4));
		otttLijst.add(5, zijLijst.get(4));
		
		vtttLijst.clear();
		vtttLijst.add(0, ikLijst.get(5));
		vtttLijst.add(1, jijLijst.get(5));
		vtttLijst.add(2, hijLijst.get(5));
		vtttLijst.add(3, wijLijst.get(5));
		vtttLijst.add(4, jullieLijst.get(5));
		vtttLijst.add(5, zijLijst.get(5));
		
		ovttLijst.clear();
		ovttLijst.add(0, ikLijst.get(6));
		ovttLijst.add(1, jijLijst.get(6));
		ovttLijst.add(2, hijLijst.get(6));
		ovttLijst.add(3, wijLijst.get(6));
		ovttLijst.add(4, jullieLijst.get(6));
		ovttLijst.add(5, zijLijst.get(6));
		
		vvttLijst.clear();
		vvttLijst.add(0, ikLijst.get(7));
		vvttLijst.add(1, jijLijst.get(7));
		vvttLijst.add(2, hijLijst.get(7));
		vvttLijst.add(3, wijLijst.get(7));
		vvttLijst.add(4, jullieLijst.get(7));
		vvttLijst.add(5, zijLijst.get(7));
		
		System.out.println("iklijst "+ ikLijst.toString());
	}

}
