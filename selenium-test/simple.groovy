@Grab(group='org.seleniumhq.selenium', module='selenium-java', version='2.39.0')
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.By

def appdir = ''
def webserver = 'www.google.com'
def port = null

def driver = new FirefoxDriver()

def starturl = 'http://' + webserver + (port?':'+port:'') + appdir

println "Opening the address: '" + starturl + "'"

driver.get(starturl)

def element = driver.findElement(By.name("q"))

element.sendKeys("selenium")
element.submit()

