import javax.inject.Inject;
import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.*;
 
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.junit.runner.JUnitCore;
import org.wiperdog.sample.managedservice.ServiceIntf;
import org.osgi.service.cm.ManagedService;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class SampleTest {
    public SampleTest() {}

	@Inject
	private org.osgi.framework.BundleContext context;
 
    @Configuration
    public Option[] config() {
		String wd = System.getProperty("user.dir");
        return options(
			// felix log level
			systemProperty("felix.log.level").value("4"), // 4 = DEBUG
			// setup properties for fileinstall bundle.
			systemProperty("felix.home").value(wd),
			systemProperty("felix.fileinstall.dir").value(wd + "/etc"),
			systemProperty("felix.fileinstall.filter").value(".*\\.cfg"),
			systemProperty("felix.fileinstall.log.level").value("4"),
			systemProperty("felix.fileinstall.noInitialDelay").value("true"),
			systemProperty("felix.fileinstall.poll").value("2000"),
			systemProperty("felix.fileinstall.tmpdir").value(wd + "/tmp"),
			//
			// Pax-exam make this test code into OSGi bundle at runtime, so 
			// we need "groovy-all" bundle to use this groovy test code.
			mavenBundle("org.apache.felix", "org.apache.felix.configadmin", "1.8.0"),
			mavenBundle("org.apache.felix", "org.apache.felix.fileinstall", "3.2.8"),
            mavenBundle("org.codehaus.groovy", "groovy-all", "2.2.1"),
            mavenBundle("org.apache.felix", "org.apache.felix.ipojo", "1.8.6"),
			mavenBundle("org.wiperdog.sample", "org.wiperdog.sample.managedservice", "0.0.1-SNAPSHOT"),
            junitBundles()
            );
    }

	private String cfgFile = null;
	ServiceIntf svc = null;
	
	@Before
	public void prepare() {
		try {
			svc = context.getService(context.getServiceReference(ServiceIntf.class.getName()));
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

	@After
	public void finish() {
		if (cfgFile != null) {
			new File(cfgFile).delete();
		}
	}
	
    @Test
    public void test0() {
		assertNull(svc.getProperty("quantity"));
		String wd = System.getProperty("user.dir");
		cfgFile = wd + "/etc/" + svc.getPid() + ".cfg";
		FileWriter fw = new FileWriter(cfgFile);
		fw.write('''quantity=111''');
		fw.close();
		Thread.sleep(5000); 
		assertEquals("111", svc.getProperty("quantity"));
    }
}

