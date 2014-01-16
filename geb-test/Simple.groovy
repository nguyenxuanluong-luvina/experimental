// see the web site:
//    http://www.gebish.org/
@Grapes([
    @Grab("org.gebish:geb-core:0.9.2"),
    @Grab("org.seleniumhq.selenium:selenium-firefox-driver:2.26.0"),
    @Grab("org.seleniumhq.selenium:selenium-support:2.26.0")
])

import geb.Browser

Browser.drive {
	go "http://www.google.com"

	assert title == "Google"

}

