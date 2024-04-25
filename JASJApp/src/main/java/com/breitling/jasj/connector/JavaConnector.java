package com.breitling.jasj.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import netscape.javascript.JSObject;

/**
 *
 * @author Robert
 */

public class JavaConnector
{
    private static final Logger LOG = LoggerFactory.getLogger(JavaConnector.class);
    
    @SuppressWarnings("unused")
	private JSObject javascriptConnector;
    
    public void setJavascriptConnector(JSObject conn) {
        this.javascriptConnector = conn;
    }
    
    public void exit(int value) 
    {
        LOG.debug("Exiting - exit code = {}", value);
        System.exit(value);
    }
    
    public void logIt(String json)
    {
        LOG.debug("LOG: {}", json);
    }
}
