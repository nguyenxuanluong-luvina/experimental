// see the document at:
//    http://docs.seleniumhq.org/docs/03_webdriver.jsp#introducing-the-selenium-webdriver-api-by-example
//
@Grab(group='org.seleniumhq.selenium', module='selenium-java', version='2.39.0')
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.By
import org.openqa.selenium.TakesScreenshot
import static org.openqa.selenium.OutputType.*
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebDriver

import org.junit.Test
import org.junit.Before
import org.junit.After
import static org.junit.Assert.*

class JUnitTest {
	def appdir = ''
	def webserver = 'www.google.com'
	def port = null
	def driver = null

	@Before
	public void prepare() {
		driver = new FirefoxDriver()
	}

	@After
	public void postproc() {
		driver.kill()
	}

	@Test
	public void test1() {
		def starturl = 'http://' + webserver + (port?':'+port:'') + appdir

		println "Opening the address: '" + starturl + "'"

		driver.get(starturl)

		def element = driver.findElement(By.name("q"))

		assertNotNull(element)

		element.sendKeys("selenium")
		element.submit()

	    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("selenium");
            }
        });


		File dest = new File('screen.png')
		dest.exists() ? dest.delete() : null
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(FILE)
		dest << screenshotFile.readBytes()

	}

}

