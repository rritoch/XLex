import java.io.File;
import java.net.URI;

import com.vnetpublishing.xlex.Parser;


public class XLexTest {

    public static void main(String[] args)
    {
    	
    	URI xlexdocuri,datauri;
    	
    	xlexdocuri = (new File("spec/csv_example.xml")).toURI();
    	datauri = (new File("spec/test.csv")).toURI();
    	
    	try {
    		Parser parser = Parser.create(xlexdocuri);
			parser.parse(datauri);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
}
