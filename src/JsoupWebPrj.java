
import java.io.*;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupWebPrj {
	public static void main (String [] args) throws IOException {
		String content = "\0";
		for (int i = 1; i <= 30; i++) {
		    String url = "https://www.marocannonces.com/categorie/306/Multimédia/" + i + ".html";
		    Document doc = Jsoup.connect(url).timeout(6000).get();
		    Elements body = doc.select("ul.cars-list");
		    for (Element e : body.select("li")) {
		    	String article= e.select("a div.holder h3").text();
		    	String ville=e.select("a div.holder span.location").text();
		    	String prix=e.select("a div.holder strong.price").text();
		    	 content += "( "+article+" , "+ville+" , "+prix+" )\n";
		    }
		}
		System.out.println("Data scrapped successfully");
		
		File file = new File("Articles.txt"); 
		if (!file.exists()) { 
		    file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw); 
		bw.write(content); 
		bw.close(); 

		
		
	}
}
