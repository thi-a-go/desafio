package br.com.conductor.crud.helper;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class AppHelper {

	private static final String SEPARATOR = ";";
	
	public AppHelper() {
		 throw new IllegalStateException("Utility class");
	}
	
	public static List<String> convertStringToArray(String params) {
	    return Stream.of(params.split(SEPARATOR))
			      		.map (elem -> new String(elem))
			      		.collect(Collectors.toList());
	}
	
	public static String getVersionProject() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        
        return model.getVersion();
    }
}
