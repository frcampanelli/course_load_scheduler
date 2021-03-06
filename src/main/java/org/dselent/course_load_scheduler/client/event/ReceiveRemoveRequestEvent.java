package org.dselent.course_load_scheduler.client.event;

import org.dselent.course_load_scheduler.client.action.ReceiveRemoveRequestAction;
import org.dselent.course_load_scheduler.client.event_handler.ReceiveRemoveRequestEventHandler;

import com.google.gwt.event.shared.GwtEvent;

public class ReceiveRemoveRequestEvent extends GwtEvent<ReceiveRemoveRequestEventHandler>{
	public static Type<ReceiveRemoveRequestEventHandler> TYPE = new Type<ReceiveRemoveRequestEventHandler>();
	
	private ReceiveRemoveRequestAction action;
	
	public ReceiveRemoveRequestEvent(ReceiveRemoveRequestAction action)
	{
		this.action = action;
	}
	
	public ReceiveRemoveRequestAction getAction()
	{
		return action;
	}
	
	@Override
	public Type<ReceiveRemoveRequestEventHandler> getAssociatedType()
	{
		return TYPE;
	}


	@Override
	protected void dispatch(ReceiveRemoveRequestEventHandler handler)
	{
		handler.onReceiveRemoveRequest(this);
	}

}
