// package com.example.myproject.test;

// WE CANNOT USE Grab with pax-exam because Ivy has some bug.

// @GrabResolver(name='snapshot', root='file:///home/kurohara/.m2/repositoryY')
// package test;
 
//@Grab(group='com.google.guava', module='guava', version='13.0.1')
//@Grab(group='javax.inject', module='javax.inject', version='1')
//@Grab(group='junit', module='junit', version='4.11')
//@Grab(group='org.slf4j', module='slf4j-api', version='1.7.5')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam', version='3.4.0')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-junit4', version='3.4.0')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-spi', version='3.4.0')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-container-native', version='3.4.0')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-link-mvn', version='3.4.0')
//@Grab(group='org.ops4j.pax.url', module='pax-url-aether', version='1.6.0')
//@Grab(group='org.apache.felix', module='org.apache.felix.framework', version='3.2.2')
//@Grab(group='org.ops4j.pax.runner', module='pax-runner', version='1.8.5')
// @Grab(group='org.codehaus.plexus', module='plexus-utils', version='2.0.6')

//@Grab(group='org.codehaus.plexus', module='plexus-utils', version='3.0')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-container-native', version='3.5.0-SNAPSHOT')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-junit4', version='3.5.0-SNAPSHOT')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam-link-mvn', version='3.5.0-SNAPSHOT')
//@Grab(group='org.ops4j.pax.url', module='pax-url-aether', version='1.6.0')
//@Grab(group='org.apache.felix', module='org.apache.felix.framework', version='3.0.9')
//@Grab(group='ch.qos.logback', module='logback-core', version='1.0.13')
//@Grab(group='ch.qos.logback', module='logback-classic', version='1.0.13')
//@Grab(group='junit', module='junit', version='4.11')
//@Grab(group='javax.inject', module='javax.inject', version='1')
//@Grab(group='org.ops4j.pax.exam', module='pax-exam', version='3.5.0-SNAPSHOT')
////@Grab(group='org.slf4j', module='slf4j-api', version='1.7.5')
//@Grab(group='org.slf4j', module='slf4j-api', version='1.5.11')
//// @GrabExclude('com.google.guava:guava')
//@Grab(group='com.google.guava', module='guava', version='14.0')
//
import javax.inject.Inject;
import static org.junit.Assert.*;
import static org.ops4j.pax.exam.CoreOptions.*;
 
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerMethod;
import org.ops4j.pax.exam.spi.reactors.PerClass;
//import com.insight_tect.hello.service.HelloService;
import org.junit.runner.JUnitCore;

// @RunWith(JUnit4TestRunner.class)
// @ExamReactorStrategy(PerMethod.class)
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerMethod.class)
public class SampleTest {
    public SampleTest() {}
//		JUnitCore.main(SampleTest.class.getName());
//	}

//    @Inject
//    private com.insight_tect.hello.service.HelloService helloService;
	@Inject
	private org.osgi.framework.BundleContext context;
 
    @Configuration
    public Option[] config() {
        return options(
			// Pax-exam make this test code into OSGi bundle at runtime, so 
			// we need "groovy-all" bundle to use this groovy test code.
            mavenBundle("org.codehaus.groovy", "groovy-all", "2.2.1"),
            mavenBundle("org.apache.felix", "org.apache.felix.ipojo", "1.8.6"),
			mavenBundle("com.insight_tec", "ipojo.manage.service", "1.0"),
			mavenBundle("com.insight_tec", "ipojo.service.listener", "1.0"),
            junitBundles()
            );
    }
 
    @Test
    public void getHelloService() {
// println ("STARTING TEST")
//        assertNotNull(context);
//        assertEquals("Hello Pax!", helloService.getMessage());
    }
}

