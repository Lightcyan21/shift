
package mvc.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class LocalUIEvent extends EventObject {

	private Object data;
	private int eventId; 

	public LocalUIEvent(Object source, int eventId) {
		super(source);
		this.eventId = eventId;
	}

	public LocalUIEvent(Object source, int eventId, Object data) {
		super(source);
		this.eventId = eventId;
		this.data = data;
	}

	
	public Object getData() {
		return data;
	}


	public int getEventId() {
		return eventId;
	}
}
