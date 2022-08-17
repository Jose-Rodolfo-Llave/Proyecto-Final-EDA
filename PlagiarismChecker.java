import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;
public class PlagiarismChecker {
	
	private ArrayList<BPlusTree> documents = new ArrayList<BPlusTree>();
	
	public boolean loadFiles(String [] paths) throws FileNotFoundException, IOException {
		this.documents = new ArrayList<BPlusTree>();		
		if(paths == null) {
			return false;
		}
		else {
			for (int i = 0; i < paths.length; i++) {
			BPlusTree<String, Integer> document = insertarArbol(paths[i]);
			documents.add(document	);
			}
		}
		
		return true;
	}
	
	public ResultChecker verifyPlagiarism(String path) throws FileNotFoundException, IOException {
		ResultChecker result = null;
		result.resultados = new Boolean [documents.size()];
		int cont = 0;
		int total = 0;
		BPlusTree<String, Integer> text = insertarArbol(path);
		for(int i=0; i < documents.size(); i++) {
			BPlusTree<String, Integer> aux = documents.get(i);
			cont = cont + comparacion(text, aux);
			if(cont == 0) {
				result.resultados[i] = false;
			} else {
				result.resultados[i] = true;
			}
		}
		return result;
	}
	
	public static BPlusTree<String, Integer> insertarArbol(String doc) throws FileNotFoundException, IOException {
		
		int cont = 0;
		BPlusTree<String, Integer> text = new BPlusTree<>(5);
		try {
			BufferedReader bf = new BufferedReader(new FileReader(doc));
			String bfRead;
			while ((bfRead = bf.readLine()) != null) {
				text.insert(bfRead, cont);
				cont++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public static int comparacion(BPlusTree<String, Integer> doc1, BPlusTree<String, Integer> doc2) {
		int contIgual=0;
		for(int i=0; i<doc1.getMaxNumber(); i++) {
			for(int j=0; j<doc2.getMaxNumber(); j++) {
				if(doc1.find(i).compareTo(doc2.find(j)) == 0) {
					contIgual++;
				}
			}
		}
		if(contIgual == doc1.getMaxNumber()) {
			return 0;
		}
		return contIgual;
	}
	
}
