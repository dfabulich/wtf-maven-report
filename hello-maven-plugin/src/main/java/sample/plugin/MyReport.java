package sample.plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import org.apache.maven.doxia.siterenderer.Renderer;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;

@Mojo(name = "myreport", defaultPhase = LifecyclePhase.SITE)
public class MyReport extends AbstractMavenReport {

	@Parameter(defaultValue="${project.reporting.outputDirectory}", required=true, readonly=true)
	private File outputDirectory;
	
	@Parameter(defaultValue="${project}", required=true, readonly=true)
	private MavenProject project;
	
	@Component
	private Renderer siteRenderer;
	
	@Override
	public String getOutputName() {
		return "my-report";
	}

	@Override
	public String getName(Locale locale) {
		return "My Report";
	}

	@Override
	public String getDescription(Locale locale) {
		return "This is my cool report";
	}

	@Override
	protected Renderer getSiteRenderer() {
		return siteRenderer;
	}

	@Override
	protected String getOutputDirectory() {
		return outputDirectory.getAbsolutePath();
	}

	@Override
	protected MavenProject getProject() {
		return project;
	}

	@Override
	protected void executeReport(Locale locale) throws MavenReportException {
		getLog().info("Hello");
		try {
			FileWriter fos = new FileWriter(outputDirectory.getAbsolutePath() + "/foo");
			fos.write("hello\n");
			fos.flush();
			fos.close();
		} catch (IOException e) {
			throw new MavenReportException("IOException", e);
		}

	}

	@Override
	public boolean isExternalReport() {
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
