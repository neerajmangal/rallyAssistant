package com.nmangal.rally.application;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.JsonObject;

public class UserStory {

	private String storyOwner = null;
	private String storyName = null;
	private String storyDescription = null;
	private String storyScheduledState = null;
	private String storyAssignedTeam = null;
	private String storyRef = null;
	private String storyOID = null;
	private String storyNumber = null;

	@JsonIgnore
	private static RallyFetchUserStoryDetails userStoryDetails = null;

	public UserStory(String story) {
		if (userStoryDetails == null) {
			userStoryDetails = new RallyFetchUserStoryDetails();
		}
		try {
			JsonObject response = userStoryDetails.fetchUserStoryData(story);
			if (response != null) {
				setUserStoryDetailsFromResponse(response);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public ResponseEntity<String> getStoryDetails(String story) { try {
	 * JsonObject response = userStoryDetails.fetchUserStoryData(story); if
	 * (response != null) { setUserStoryDetailsFromResponse(response); }
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	private void setUserStoryDetailsFromResponse(JsonObject responseObject) {
		setStoryRef(responseObject.get("_ref").toString());
		setStoryOID(responseObject.get("ObjectID").toString());
		setStoryName(responseObject.get("Name").toString());
		setStoryNumber(responseObject.get("FormattedID").toString());
		setStoryDescription(responseObject.get("Description").toString());
		setStoryScheduledState(responseObject.get("ScheduleState").toString());
		setStoryAssignedTeam(responseObject.get("Project").getAsJsonObject().get("_refObjectName").toString());
		setStoryOwner(responseObject.get("Owner").getAsJsonObject().get("_refObjectName").toString());
	}

	public String getUserStory() {
		return getStoryNumber();
	}

	/**
	 * @return the storyOwner
	 */
	public String getStoryOwner() {
		return storyOwner;
	}

	/**
	 * @param storyOwner
	 *            the storyOwner to set
	 */
	public void setStoryOwner(String storyOwner) {
		this.storyOwner = storyOwner;
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
	public void setStoryName(String storyName) {
		this.storyName = storyName;
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
	public void setStoryDescription(String storyDescription) {
		this.storyDescription = storyDescription;
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
	public void setStoryScheduledState(String storyScheduledState) {
		this.storyScheduledState = storyScheduledState;
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
	public void setStoryAssignedTeam(String storyAssignedTeam) {
		this.storyAssignedTeam = storyAssignedTeam;
	}

	/**
	 * @return the storyRef
	 */
	public String getStoryRef() {
		return storyRef;
	}

	/**
	 * @param storyRef
	 *            the storyRef to set
	 */
	public void setStoryRef(String storyRef) {
		this.storyRef = storyRef;
	}

	/**
	 * @return the storyOID
	 */
	public String getStoryOID() {
		return storyOID;
	}

	/**
	 * @param storyOID
	 *            the storyOID to set
	 */
	public void setStoryOID(String storyOID) {
		this.storyOID = storyOID;
	}

	public String getStoryNumber() {
		return storyNumber;
	}

	public void setStoryNumber(String storyNumber) {
		this.storyNumber = storyNumber;
	}

}
