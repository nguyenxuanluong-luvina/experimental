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
import org.osgi.service.cm.ManagedService;
import org.wiperdog.LogStat;

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
			cleanCaches(true),
			frameworkStartLevel(6),
			// felix log level
			systemProperty("felix.log.level").value("4"), // 4 = DEBUG
			// setup properties for fileinstall bundle.
			systemProperty("felix.home").value(wd),
			//
			// Pax-exam make this test code into OSGi bundle at runtime, so 
			// we need "groovy-all" bundle to use this groovy test code.
            mavenBundle("org.codehaus.groovy", "groovy-all", "2.2.1").startLevel(2),
			// wrappedBundle(mavenBundle("org.jruby", "jruby-complete", "1.7.10")),
			mavenBundle("org.jruby", "jruby-complete", "1.7.10").startLevel(2),
			mavenBundle("org.wiperdog", "logstat", "1.0").startLevel(3),
            junitBundles()
            );
    }

	private String cfgFile = null;
	private LogStat svc;
	
	@Before
	public void prepare() {
	}

	@After
	public void finish() {
	}
 
    @Test
    public void test0() {

		svc = context.getService(context.getServiceReference(LogStat.class.getName()));
		try{
			HashMap<String , Object> input_conf = new HashMap<String, Object>();
			HashMap<String , Object> output_conf = new HashMap<String, Object>();
			HashMap<String , Object> filter_conf = new HashMap<String, Object>();
			HashMap<String , Object> conf = new HashMap<String, Object>();
	
			output_conf.put("type", "file");
			output_conf.put("destination", "E:\\output.log");
	
			filter_conf.put("date", "^[0-9]{4}-[0-9]{2}-[0-9]{2}");
			filter_conf.put("time", "\\d{2}:\\d{2}:\\d{2}");
			filter_conf.put("message", "");
	
			input_conf.put("input_type", "file");
			input_conf.put("input_source", "C:\\filelog.log");
			input_conf.put("start_pos", 5);
			input_conf.put("num_of_lines", null);
	
			conf.put("input",input_conf);
			conf.put("filter",filter_conf);
			conf.put("output",output_conf);
	
			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
    }
}

