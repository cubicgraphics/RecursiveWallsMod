package net.cubicmods.recursivecustomstart.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.util.Map;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.BufferedReader;

public class ReadEndingsTextProcedure {
	public static ArrayList executeProcedure(Map<String, Object> dependencies) {
		File Endings = new File("");
		ArrayList ListOfEndingsAchieved = new ArrayList();
		Endings = new File(FMLPaths.GAMEDIR.get().toString(), File.separator + "AchievedEndings.txt");
		if (!Endings.exists()) {
			try {
				Endings.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			BufferedReader EndingsReader = new BufferedReader(new FileReader(Endings));
			String stringiterator = "";
			while ((stringiterator = EndingsReader.readLine()) != null) {
				ListOfEndingsAchieved.add(stringiterator);
			}
			EndingsReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ListOfEndingsAchieved;
	}
}
