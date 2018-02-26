package org.dselent.course_load_scheduler.client.translator.impl;

import org.dselent.course_load_scheduler.client.action.ReceiveLoginAction;
import org.dselent.course_load_scheduler.client.action.SendLoginAction;
import org.dselent.course_load_scheduler.client.model.User;
import org.dselent.course_load_scheduler.client.receive.jsonkeys.ReceiveLoginKeys;
import org.dselent.course_load_scheduler.client.send.jsonkeys.SendLoginKeys;
import org.dselent.course_load_scheduler.client.translator.ActionTranslator;
import org.dselent.course_load_scheduler.client.utils.JSONHelper;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;

public class LoginActionTranslatorImpl implements ActionTranslator<SendLoginAction, ReceiveLoginAction>
{
	@Override
	public JSONObject translateToJson(SendLoginAction action)
	{
		JSONObject jsonObject = new JSONObject();

		Window.alert("in translator to make action JSON.");
		
		JSONHelper.putStringValue(jsonObject, JSONHelper.convertKeyName(SendLoginKeys.USER_NAME), action.getUserName());
		JSONHelper.putStringValue(jsonObject, JSONHelper.convertKeyName(SendLoginKeys.PASSWORD), action.getPassword());

		Window.alert("josnObject: " + jsonObject.toString());
		
		return jsonObject;
	}

	@Override
	public ReceiveLoginAction translateToAction(JSONObject json)
	{		
		// null values will not have their keys sent back from the sever
		// this will throw an exception here
		// you may choose to handle the exception as you wish

		// sent timestamps as epoch seconds (long)

		
		
		JSONValue jsonObject = json.get("success");
		JSONObject userObject = jsonObject.isArray().get(0).isObject();
		
		Window.alert("in translator to make JSON action? JSON = " + userObject.toString());

		Integer id = JSONHelper.getIntValue(userObject, JSONHelper.convertKeyName(ReceiveLoginKeys.ID));
		Integer accountTypeId = JSONHelper.getIntValue(userObject, JSONHelper.convertKeyName(ReceiveLoginKeys.ACCOUNT_TYPE_ID));
		String password = JSONHelper.getStringValue(userObject, JSONHelper.convertKeyName(ReceiveLoginKeys.ENCRYPTED_PASSWORD));

		// TODO look into time conversion more
		// put into JSONHelper?

		User user = new User();
		user.setId(id);
		user.setAccountTypeId(accountTypeId);
		user.setEncryptedPassword(password);
		
		Window.alert("in translator to make JSON action? action = " + user.toString());

		// possibly use builder pattern if it is a lot of data
		ReceiveLoginAction action = new ReceiveLoginAction(user);	

		return action;
	}


}