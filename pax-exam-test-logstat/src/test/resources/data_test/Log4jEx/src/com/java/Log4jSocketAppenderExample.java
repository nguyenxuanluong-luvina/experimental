import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jruby.RubyProcess.Sys;

public class Log4jSocketAppenderExample {

	public static void main(String[] args) throws InterruptedException {
		try {
			Log4jSocketAppenderExample l = new Log4jSocketAppenderExample();
			l.generateLogs();
		} catch (Exception ex) {
			SysoSystem.out.println(ex);
		}
	}

	public void generateLogs() {
		Logger logger = Logger.getLogger(Log4jSocketAppenderExample.class);
		int i = 0;
		while (true) {
			// PropertiesConfigurator is used to configure logger from
			// properties file
			String wd = System.getProperty("user.dir");
			PropertyConfigurator.configure(wd + "/src/com/java/log4j.properties");
			// These logs will be sent to socket server as configured in
			logger.error("Log4j error message!!");			
			//logger.debug("Log4j debug message!!");
			System.out.println(i++);
			Thread.sleep(3000);
		}
	}
}