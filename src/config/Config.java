package config;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class Config {
	
	public static String[] getBatimentsFromConfig(String configFile) {
		
		List<String> listeBatiments = new ArrayList<String>();
		LineNumberReader lineReader;	
		
		try {

			String line;
			
			lineReader = new LineNumberReader(new FileReader(configFile));
			
			while(lineReader.ready())
				if(getTypeFromLine((line = readLine(lineReader))) == ConfigType.BATIMENT)
					listeBatiments.add(line.substring(2));
			
			lineReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] tableauBatiments = new String[listeBatiments.size()];
		
		for(int i = 0 ; i < tableauBatiments.length ; ++i)
			tableauBatiments[i] = listeBatiments.get(i);
		
		return tableauBatiments;
		
	}
	
	public static String[] getEtagesFromConfig(String configFile, int batimentId) {
		
		List<String> listeEtages = new ArrayList<String>();
		LineNumberReader lineReader;	
		
		try {

			String line;
			int currentBatimentId = 0;
			
			lineReader = new LineNumberReader(new FileReader(configFile));
			
			while(lineReader.ready() && currentBatimentId != batimentId + 1)
				if(getTypeFromLine((line = readLine(lineReader))) == ConfigType.BATIMENT)
					++currentBatimentId;
			
			while(lineReader.ready()) {
				line = readLine(lineReader);
				if(getTypeFromLine(line) == ConfigType.BATIMENT)
					break;
				else if(getTypeFromLine(line) == ConfigType.ETAGE)
					listeEtages.add(line.substring(2));
			}
			
			lineReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] tableauEtages = new String[listeEtages.size()];
		
		for(int i = 0 ; i < tableauEtages.length ; ++i)
			tableauEtages[i] = listeEtages.get(i);
		
		return tableauEtages;
		
	}
	
	public static String[] getSallesFromConfig(String configFile, int batimentId, int etageId) {
		
		List<String> listeSalles = new ArrayList<String>();
		LineNumberReader lineReader;	
		
		try {

			String line;
			int currentBatimentId = 0;
			int currentEtageId = 0;
			
			lineReader = new LineNumberReader(new FileReader(configFile));
			
			while(lineReader.ready() && currentBatimentId != batimentId + 1)
				if(getTypeFromLine((line = readLine(lineReader))) == ConfigType.BATIMENT)
					++currentBatimentId;
			
			while(lineReader.ready() && currentEtageId != etageId + 1)
				if(getTypeFromLine((line = readLine(lineReader))) == ConfigType.ETAGE)
					++currentEtageId;
			
			while(lineReader.ready()) {
				line = readLine(lineReader);
				if(getTypeFromLine(line) == ConfigType.ETAGE) {
					break;
				}
				else if(getTypeFromLine(line) == ConfigType.SALLE)
					listeSalles.add(line.substring(2));
			}
			
			lineReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] tableauSalles = new String[listeSalles.size()];
		
		for(int i = 0 ; i < tableauSalles.length ; ++i)
			tableauSalles[i] = listeSalles.get(i);
		
		return tableauSalles;
		
	}
	
	private static ConfigType getTypeFromLine(String line) {
		if(line.length() > 0) {
			if(line.charAt(0) == 'b')
				return ConfigType.BATIMENT;
			else if(line.charAt(0) == 'e')
				return ConfigType.ETAGE;
			else if(line.charAt(0) == 's')
				return ConfigType.SALLE;
			else
				return ConfigType.UNKNOWN;
		}
		else
			return ConfigType.UNKNOWN;
	}
	
	private static String readLine(LineNumberReader lineReader) {
		String line = "";
		
		try {
			line = lineReader.readLine();
			line = line.trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return line;
	}

}
