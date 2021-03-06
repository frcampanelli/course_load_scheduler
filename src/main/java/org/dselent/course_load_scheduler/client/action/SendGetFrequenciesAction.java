package org.dselent.course_load_scheduler.client.action;

import org.dselent.course_load_scheduler.client.model.User;

public class SendGetFrequenciesAction extends Action{
	
	private User model;
	
	public SendGetFrequenciesAction() {
		model = null;
	}
	
	public SendGetFrequenciesAction(User model)
	{
		this.model = model;
	}

	public User getModel()
	{
		return model;
	}

	public void setModel(User model)
	{
		this.model = model;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("ReceiveFrequenciesAction [model=");
		builder.append(model);
		builder.append("]");
		return builder.toString();
	}
}
