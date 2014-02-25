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
import service.LogStat;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class SampleTest {
	public SampleTest() {
	}

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
	//------------------------TEST Plain text FORMAT------------------------------------------
	//Test function get logs from plain text by line - full parameter
	public void testPlainTextByLine0() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";
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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			conf.put("input",input_conf);
			conf.put("filter",filter_conf);
			conf.put("output",output_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	//Test function get logs from plain textby line - missing some parameter
	public void testPlainTextByLine1() {

		String currentDir = System.getProperty("user.dir");
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
			//input_conf.put("path", currentDir + "/src/test/data_test/testPlainText/");
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by line- missing some parameter
	public void testPlainTextByLine2() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			//input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);
			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by line- missing some parameter
	public void testPlainTextByLine3() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "line");
			//input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by line- missing some parameter
	public void testPlainTextByLine4() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			//input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by line- missing some parameter
	public void testPlainTextByLine5() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			//input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by line- start_pos is string
	public void testPlainTextByLine6() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", "3");
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by line- missing some parameter
	public void testPlainTextByLine7() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			//			input_conf.put("file_format", "plain_text");
			//			input_conf.put("monitor_type", "line");
			//			input_conf.put("start_file_name", "logfile2.log");
			//			input_conf.put("start_pos", 3);
			//			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by date - full parameter
	public void testPlainTextByDate0() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-02-29");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by date - full parameter
	public void testPlainTextByDate1() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			//input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	//Test function get logs from plain text by date - missing some parameter
	public void testPlainTextByDate2() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			//input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}


	//Test function get logs from plain text by date - missing some parameter
	public void testPlainTextByDate3() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			//input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-2");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by date - missing some parameter
	public void testPlainTextByDate4() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			//input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}


	//Test function get logs from plain text by date - missing some parameter
	public void testPlainTextByDate5() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			//input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by date - incorrect from_date parameter
	public void testPlainTextByDate6() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-02-30");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from plain text by date - incorrect from_date parameter
	public void testPlainTextByDate7() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testPlainText/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "plain_text");
			input_conf.put("monitor_type", "date");
			//			input_conf.put("start_file_name", "logfile2.log");
			//			input_conf.put("start_pos", 3);
			//			input_conf.put("asc_by_fname", true);
			//			input_conf.put("from_date", "2014-02-30");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//------------------------TEST CSV FORMAT------------------------------------------
	//Test function get logs from csv by line - full parameter

	public void testCSVByLine0() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";
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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csvby line - missing some parameter
	public void testCSVByLine1() {

		String currentDir = System.getProperty("user.dir");
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
			//input_conf.put("path", currentDir + "/src/test/data_test/testCSV/");
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by line- missing some parameter
	public void testCSVByLine2() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			//input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}


	//Test function get logs from csv by line- missing some parameter
	public void testCSVByLine3() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "line");
			//input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}


	//Test function get logs from csv by line- missing some parameter
	public void testCSVByLine4() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.csv");
			//input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by line- missing some parameter
	public void testCSVByLine6() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "line");
			input_conf.put("start_file_name", "logfile2.csv");
			input_conf.put("start_pos", 3);
			//input_conf.put("asc_by_fname", false);

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by line- missing some parameter
	public void testCSVByLine7() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			//			input_conf.put("monitor_type", "line");
			//			input_conf.put("start_file_name", "logfile2.log");
			//			input_conf.put("start_pos", 3);
			//			input_conf.put("asc_by_fname", false);
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by date - full parameter
	public void testCSVByDate0() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.csv");
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by date - missing some parameter
	public void testCSVByDate1() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			//input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by date - missing some parameter
	public void testCSVByDate2() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			//input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	//Test function get logs from csv by date - missing some parameter
	public void testCSVByDate3() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			//input_conf.put("asc_by_fname", true);
			input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by date - missing some parameter
	public void testCSVByDate4() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", true);
			//input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	//Test function get logs from csv by date - missing some parameter
	public void testCSVByDate5() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			//			input_conf.put("start_file_name", "logfile2.log");
			//			input_conf.put("start_pos", 3);
			//			input_conf.put("asc_by_fname", true);
			//			input_conf.put("from_date", "2014-01-15");
			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	//Test function get logs from csv by date - missing some parameter
	public void testCSVByDate7() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			input_conf.put("from_date", "2014-01-15");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//Test function get logs from csv by date - Incorrect from_date
	public void testCSVByDate6() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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
			input_conf.put("path", logs_test_dir);
			input_conf.put("file_format", "csv");
			input_conf.put("monitor_type", "date");
			input_conf.put("start_file_name", "logfile2.log");
			input_conf.put("start_pos", 3);
			input_conf.put("asc_by_fname", false);
			input_conf.put("from_date", "2014-01-35");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}

	//-------------------------------TEST EVENT_LOG----------------------------
	//Test function get event log - Incorrect from_date

	public void testEventLog0() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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

			input_conf.put("input_type", "eventlog");
			input_conf.put("event_log_type", "System");
			input_conf.put("from_time_generated", "2014-02-23");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}


	public void testEventLog1() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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

			input_conf.put("input_type", "eventlog");
			//input_conf.put("event_log_type", "Application");
			input_conf.put("from_time_generated", "2014-02-23");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}


	public void testEventLog2() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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

			input_conf.put("input_type", "eventlog");
			input_conf.put("event_log_type", "Kaspersky Event Log");
			//input_conf.put("from_time_generated", "2014-02-23");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	
	public void testEventLog3() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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

			input_conf.put("input_type", "eventlog");
//			input_conf.put("event_log_type", "Kaspersky Event Log");
			//input_conf.put("from_time_generated", "2014-02-23");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
	
	@Test
	public void testEventLog4() {
		String currentDir = System.getProperty("user.dir");
		String logs_test_dir = currentDir + "/src/test/data_test/testCSV/";

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

			input_conf.put("input_type", "eventlog");
			input_conf.put("event_log_type", "Application");
			input_conf.put("from_time_generated", "2014-02-23");

			conf.put("input",input_conf);
			conf.put("output",output_conf);
			conf.put("filter",filter_conf);

			svc.runLogStat(conf)
		} catch(Exception ex){
			println ex
		}
	}
}

