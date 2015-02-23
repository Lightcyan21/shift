
package mvc.event;

import java.util.EventListener;


public interface LocalUIEventListener extends EventListener {
	
	void handleEvent(LocalUIEvent event);
}
