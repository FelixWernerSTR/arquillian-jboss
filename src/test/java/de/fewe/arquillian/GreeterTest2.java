package de.fewe.arquillian;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.fewe.arquillian.GreeterEjb;
import de.fewe.arquillian.IGreeter;

@RunWith(Arquillian.class)
public class GreeterTest2 {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class,"greeter2.jar")
        	.addClass(IGreeter.class)
        	.addClass(GreeterEjb.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB(lookup = "java:global/greeter2/GreeterEjb!de.fewe.arquillian.wildfly.arquillian_wildfly.IGreeter")
    IGreeter greeter;

    @Test
    @Ignore
    public void should_create_greeting() {
        Assert.assertEquals("Hello, Earthling!",
            greeter.createGreeting("Earthling"));
        greeter.greet(System.out, "Earthling");
    }
}