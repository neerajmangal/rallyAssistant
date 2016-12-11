package com.nmangal.rally.application;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RallyController {

	@RequestMapping(value = "/userstory/{storyNumber}", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.GET)
	public @ResponseBody UserStory getUserstory(@PathVariable(value = "storyNumber") final String storyNumber) {
		UserStory userStory = new UserStory(storyNumber);
		return userStory;
	}

	@RequestMapping(value = "/userstory/{storyNumber}", consumes = { "application/json" }, produces = {
	"application/json" }, method = RequestMethod.PATCH)
	public @ResponseBody ResponseEntity<UserStory> updateStoryState(@PathVariable(value = "storyNumber") final String storyNumber,
			@Valid @RequestBody final Map<String, String> request) {
		RallyUpdateUserStory updateStoryState = new RallyUpdateUserStory();
		
		if (updateStoryState.RallyUpdateStoryState(storyNumber, request.get("state"))){
			UserStory userStory = new UserStory(storyNumber);
			return new ResponseEntity<UserStory>(userStory, HttpStatus.OK);
		}
		return new ResponseEntity<UserStory>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
