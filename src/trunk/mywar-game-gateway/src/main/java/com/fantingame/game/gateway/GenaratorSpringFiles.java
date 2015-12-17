package com.fantingame.game.gateway;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;



public class GenaratorSpringFiles {

	public static void main(String[] args) throws IOException {
		//IOUtils and FileUtils come from Apache Commons IO
		for(String s : new String[] {"spring.schemas", "spring.handlers", "spring.tooling"}) {
		Enumeration<?> e = GenaratorSpringFiles.class.getClassLoader().getResources("META-INF/"+s);
		StringBuilder out = new StringBuilder();
		while(e.hasMoreElements()) { 
			URL u = (URL) e.nextElement();
			out.append(IOUtils.toString(u.openStream())).append("\n"); }
		File outf = new File(s);
		FileUtils.writeStringToFile(outf, out.toString(), "UTF-8");
		}
	}
}
