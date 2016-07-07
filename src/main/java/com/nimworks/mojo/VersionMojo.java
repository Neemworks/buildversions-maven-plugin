package com.nimworks.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.scm.manager.ScmManager;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.embed.Embedder;

import com.nimworks.commons.props.KeyValueProperties;
import com.nimworks.plugins.pom.PomTool;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.INITIALIZE, requiresProject = true, threadSafe = true)
public class VersionMojo extends AbstractMojo {

	@Parameter(defaultValue = "${basedir}/.build/semantics.properties")
	private File versionsFile;

	@Parameter(property = "build.type", defaultValue = "PATCH", required = true)
	private BuildType buildType;

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	protected MavenProject project;

	@Parameter(property = "project.version", required = true, defaultValue = "${project.version}")
	protected String projectVersion;
	
	private PomTool tool = new PomTool();

	public void execute() throws MojoExecutionException, MojoFailureException {

		System.out.println("----------------------------------------------------------------------------------------------Current Project version: "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         + projectVersion);
		projectVersion = "1.1.12-SNAPSHOT";
		project.setVersion(projectVersion);
		Model omodel = project.getOriginalModel();
//		project.
		String revision = "";
		KeyValueProperties kp = new KeyValueProperties(versionsFile);
		Integer major = kp.getInt("major-version", 1);
		Integer minor = kp.getInt("minor-version", 1);
		Integer patch = kp.getInt("patch-version", 1);
		if (buildType == BuildType.MAJOR) {
			major += 1;
			minor = 0;
			patch = 0;
		} else if (buildType == BuildType.MINOR) {
			minor += 1;
			patch = 0;
		} else if (buildType == BuildType.PATCH) {
			patch += 1;
		}

		kp.setProperty("major-version", major + "", "application major version");
		kp.setProperty("minor-version", minor + "", "application minor version");
		kp.setProperty("patch-version", patch + "", "application minor version");

		revision = major + "." + minor + "." + patch;

		project.getProperties().put("buildVersion", revision);
		omodel.setVersion(revision);
		try {
			tool.write(omodel, "pomb.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void scm() throws ComponentLookupException {
//		ScmManager scm = (ScmManager) new Embedder().lookup(ScmManager.ROLE);

	}

}
