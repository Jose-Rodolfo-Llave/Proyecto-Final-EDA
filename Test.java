import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException{
    	
		File carpeta = new File("D:\\test");
		File[] archivos = carpeta.listFiles(verficarSoloArchivos());
		String [] rutas = new String [archivos.length];
		String rutaArchivo = "D:\\test\\";
		
		for(int i = 0; i < archivos.length; i++) {
			rutas[i] = archivos[i].getAbsolutePath();
		}

    	PlagiarismChecker Test = new PlagiarismChecker();
    	
    	Test.loadFiles(rutas);
    	Test.verifyPlagiarism(rutaArchivo);
    }
    
	public static FileFilter verficarSoloArchivos() {
		FileFilter filtro = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
			
		};
		return filtro;
	}
}
