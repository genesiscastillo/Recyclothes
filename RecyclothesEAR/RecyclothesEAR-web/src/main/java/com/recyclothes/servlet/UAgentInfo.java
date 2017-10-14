package com.recyclothes.servlet;

import org.apache.log4j.Logger;

/**
 -->>>  userAgent = Mozilla/5.0 (Linux; Android 5.1; XT1032 Build/LPB23.13-56) AppleWebKit/537.36 (KHTM L, like Gecko) Chrome/49.0.2623.105 Mobile Safari/537.36
 -->>>  userAgent = Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36

 */
public class UAgentInfo {

    private final static Logger LOGGER = Logger.getLogger(UAgentInfo.class);

    //Stores some info about the browser and device.
    private String userAgent;

    //Stores info about what content formats the browser can display.
    private String httpAccept;

    // A long list of strings which provide clues
    //   about devices and capabilities.
    public static final String mobile = "Mobile";

    // [ SNIP! Other variables snipped out ]


    //**************************
    //The constructor. Initializes several default variables.
    public UAgentInfo(String userAgent, String httpAccept) {

        if (userAgent != null) {
            LOGGER.info("userAgent = "+userAgent);
            this.userAgent = userAgent.toLowerCase();
        }
        if (httpAccept != null) {
            LOGGER.info("httpAccept = "+httpAccept );
            this.httpAccept = httpAccept.toLowerCase();
        }
    }

    //**************************
    //Returns the contents of the User Agent value, in lower case.
    public String getUserAgent()
    {
        return userAgent;
    }

    //**************************
    // Detects if the current device is an iPhone.
    public boolean detectIphone()
    {
        //The iPod touch says it's an iPhone! So let's disambiguate.
        if (userAgent.indexOf(mobile) != -1 ){//&& !detectIpod()) {
            return true;
        }
        return false;
    }

    // [ SNIP! Other functions snipped out ]
}
