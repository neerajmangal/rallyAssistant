/**
 * 
 */
package com.nmangal.rally.application;

import java.net.URI;
import java.net.URISyntaxException;

import com.rallydev.rest.RallyRestApi;

/**
 * @author nmangal
 *
 */
public class RallyConnector {

	private final static String RallyUrl = "https://rally1.rallydev.com";
	private static RallyRestApi rallyRestApiConnector = null;
	private static final String RALLY_ACCESS_API_KEY="_hDA2pOoEQek316UXNyrWsk2upx9D0WgnNAiPpEwxf4";

	/**
	 * 
	 */
	private RallyConnector() {
		
	}

	public static RallyRestApi getRallyConnector(){
		if (rallyRestApiConnector != null) {
			return rallyRestApiConnector;
		}
		
        try {
			rallyRestApiConnector = new RallyRestApi(new URI(RallyUrl),
					RALLY_ACCESS_API_KEY);
			rallyRestApiConnector.setApplicationName("Rally To Spark Integration");
			rallyRestApiConnector.setApplicationVersion("1.0");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rallyRestApiConnector;
	}
}
