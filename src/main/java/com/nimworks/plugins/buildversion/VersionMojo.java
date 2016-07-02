package com.nimworks.plugins.buildversion;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import nw.commons.props.KeyProperties;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.INITIALIZE, requiresProject = true, threadSafe = true)
public class VersionMojo extends AbstractMojo {

	 @Parameter( defaultValue = "${basedir}/.versions" )
	 private File versionsFile;

	 @Parameter(property = "build.type", defaultValue = "PATCH", required = true)
	 private BuildType buildType;

	 @Parameter( defaultValue = "${project}", required = true, readonly = true )
	 protected MavenProject project;

	public void execute() throws MojoExecutionException, MojoFailureException {
		String revision = "";
		KeyProperties kp = new KeyProperties(versionsFile.getName());
		Integer major = kp.getInt("major-version", 1);
		Integer minor = kp.getInt("minor-version", 1);
		Integer patch = kp.getInt("patch-version", 1);
		if(buildType == BuildType.MAJOR){
			major += 1;
			minor = 0;
			patch = 0;
		}else if(buildType == BuildType.MINOR){
			minor += 1;
			patch = 0;
		}else if(buildType == BuildType.PATCH){
			patch += 1;
		}

		kp.setProperty("major-version", major + "", "application major version");
		kp.setProperty("minor-version", minor + "", "application minor version");
		kp.setProperty("patch-version", patch + "", "application minor version");

		revision = major + "." + minor + "." + patch;

		project.getProperties().put("buildVersion", revision);

	}

}
