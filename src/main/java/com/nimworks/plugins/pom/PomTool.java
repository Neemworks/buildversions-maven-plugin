package com.nimworks.plugins.pom;

import java.io.File;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.WriterFactory;
import org.codehaus.plexus.util.xml.XmlStreamReader;
import org.codehaus.plexus.util.xml.XmlStreamWriter;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

public class PomTool {
	
	private MavenXpp3Reader mr = new MavenXpp3Reader();
	private MavenXpp3Writer mw = new MavenXpp3Writer();
	
	public Model read() throws IOException, XmlPullParserException{
		XmlStreamReader xsr = ReaderFactory.newXmlReader(new File("pom.xml"));
		Model pm = mr.read(xsr);
		return pm;
		
	}
	
	public void write(Model model) throws IOException{
		write(model, "pom.xml");
	}
	
	public void write(Model model, String pomName) throws IOException{
		XmlStreamWriter xw = WriterFactory.newXmlWriter(new File(pomName));
		mw.write(xw, model);
	}

}
