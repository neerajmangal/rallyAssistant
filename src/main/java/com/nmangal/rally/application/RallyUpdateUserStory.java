/**
 * 
 */
package com.nmangal.rally.application;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.UpdateRequest;
import com.rallydev.rest.response.UpdateResponse;

/**
 * @author nmangal
 *
 */
public class RallyUpdateUserStory {

	/**
	 * 
	 */
	public RallyUpdateUserStory() {
		// TODO Auto-generated constructor stub
	}

	public boolean RallyUpdateStoryState(String userStory, String state) {
		RallyFetchUserStoryDetails userStoryDetails = new RallyFetchUserStoryDetails(userStory);
		RallyRestApi rallyConnector = RallyConnector.getRallyConnector();
		JsonObject updateObject = new JsonObject();
		updateObject.addProperty("ScheduleState", state);
		String updateRef = "/hierarchicalrequirement/" + userStoryDetails.getStoryOID();
		System.out.println(updateRef);
		UpdateRequest updateRequest = new UpdateRequest(updateRef, updateObject);
		try {
			UpdateResponse updateResponse = rallyConnector.update(updateRequest);
			if (!updateResponse.wasSuccessful()) {
				System.out.println("not able to update state of " + userStory + "Error" + Arrays.toString(updateResponse.getErrors()));
				return false;
			}
			
		} catch (IOException e) {
			System.out.println("Exception Caught while updating UserStory" + state);
			e.printStackTrace();
		}
		
		return true;
	}
}
