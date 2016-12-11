/**
 * 
 */
package com.nmangal.rally.application;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.QueryFilter;
import com.rallydev.rest.util.Ref;

/**
 * @author nmangal
 *
 */
public class RallyFetchUserStoryDetails {

	//String workSpaceId = "294773690";
	//String projectId = "33128215990";
	//String messagingUcxnProjectId = "1021474236";
	private static String storyOwner = null;
	private static String storyName = null;
	private static String storyDescription = null;
	private static String storyScheduledState = null;
	private static String storyAssignedTeam = null;
	private static String storyRef;
	private static String storyOID;

	/**
	 * @return the storyOwner
	 */
	public static String getStoryOwner() {
		return storyOwner;
	}

	/**
	 * @param storyOwner
	 *            the storyOwner to set
	 */
	private static void setStoryOwner(String storyOwner) {
		RallyFetchUserStoryDetails.storyOwner = storyOwner;
	}

	/**
	 * @return the storyName
	 */
	public String getStoryName() {
		return storyName;
	}

	/**
	 * @param storyName
	 *            the storyName to set
	 */
	private static void setStoryName(String storyName) {
		RallyFetchUserStoryDetails.storyName = storyName;
	}

	/**
	 * @return the storyDescription
	 */
	public String getStoryDescription() {
		return storyDescription;
	}

	/**
	 * @param storyDescription
	 *            the storyDescription to set
	 */
	private static void setStoryDescription(String storyDescription) {
		RallyFetchUserStoryDetails.storyDescription = storyDescription;
	}

	/**
	 * @return the storyScheduledState
	 */
	public String getStoryScheduledState() {
		return storyScheduledState;
	}

	/**
	 * @param storyScheduledState
	 *            the storyScheduledState to set
	 */
	private static void setStoryScheduledState(String storyScheduledState) {
		RallyFetchUserStoryDetails.storyScheduledState = storyScheduledState;
	}

	public String getStoryRef() {
         return storyRef;
	}

	private static void setStoryRef(String storyRef) {
		RallyFetchUserStoryDetails.storyRef = storyRef;
	}

    private static void setStoryOID(String storyOID) {
    	RallyFetchUserStoryDetails.storyOID = storyOID;
	}
    
    public String getStoryOID() {
        return storyOID;
	}
    
	/**
	 * @return the storyAssignedTeam
	 */
	public String getStoryAssignedTeam() {
		return storyAssignedTeam;
	}

	/**
	 * @param storyAssignedTeam
	 *            the storyAssignedTeam to set
	 */
	private static void setStoryAssignedTeam(String storyAssignedTeam) {
		RallyFetchUserStoryDetails.storyAssignedTeam = storyAssignedTeam;
	}

	/**
	 * 
	 */
	public RallyFetchUserStoryDetails(String userStoryNumber) {
		JsonObject responseObject = null;
		try {
			responseObject = fetchUserStoryData(userStoryNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public RallyFetchUserStoryDetails() {
		// TODO Auto-generated constructor stub
	}

	public JsonObject fetchUserStoryData(String userStoryNumber) throws IOException {
		RallyRestApi rallyConnector = RallyConnector.getRallyConnector();
		JsonObject responseObject = null;
		try {
			QueryRequest userStoryRequest = new QueryRequest("HierarchicalRequirement");
			userStoryRequest.setQueryFilter(new QueryFilter("FormattedID", "=", userStoryNumber));
			QueryResponse userStoryResponse = rallyConnector.query(userStoryRequest);
			if (userStoryResponse.getResults().size() > 0) {
				responseObject = userStoryResponse.getResults().get(0).getAsJsonObject();
				setUserStoryDetailsFromResponse(responseObject);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} 
		return responseObject;

	}

	public String getUserStoryDetailsAll() {
		
		StringBuilder userStoryData = new StringBuilder();
		userStoryData.append("Team:").append(getStoryAssignedTeam()).append("\n");
		userStoryData.append("Name:").append(getStoryName()).append("\n");
		userStoryData.append("Description:").append(getStoryDescription()).append("\n");
		userStoryData.append("ScheduleState:").append(getStoryScheduledState()).append("\n");
		userStoryData.append("StoryRef:").append(getStoryRef()).append("\n");
		userStoryData.append("StoryOID:").append(getStoryOID()).append("\n");
		return userStoryData.toString();
		
	}
	/**
	 * @param userStoryResponse
	 */
	private static void setUserStoryDetailsFromResponse(JsonObject responseObject) {

		System.out.println(PrettyPrintJson(responseObject));
		setStoryRef(responseObject.get("_ref").toString());
		setStoryOID(responseObject.get("ObjectID").toString());
		setStoryName(responseObject.get("Name").toString());
		setStoryDescription(responseObject.get("Description").toString());
		setStoryScheduledState(responseObject.get("ScheduleState").toString());
		setStoryAssignedTeam(responseObject.get("Project").getAsJsonObject().get("_refObjectName").toString());
	}

	

	public static String PrettyPrintJson(JsonObject jsonObject) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(jsonObject);
	}

}
