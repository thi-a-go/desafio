package br.com.conductor.crud.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.jupiter.api.Test;

public class AppHelperTest {

	@Test
	void verifyConstructor() {
		
		Throwable exception = assertThrows(
				IllegalStateException.class, () ->
				new AppHelper());
	    
		final String PARAM_EXPECTED = "Utility class";
		
		assertEquals(PARAM_EXPECTED, exception.getMessage());
	}	
	
	@Test
	void verifyGetVersionProject() throws IOException, XmlPullParserException {
		final String PARAM_EXPECTED = "0.0.1-SNAPSHOT";
		assertEquals(PARAM_EXPECTED, AppHelper.getVersionProject());
	}
}
