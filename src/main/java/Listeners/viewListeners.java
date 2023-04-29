package Listeners;

import VIEW.SlotChoice;


public interface viewListeners {
    void addviewEventListener(viewListeners listener);
    void notifySelectedCoordinates(SlotChoice[] SC);
    void selecteCoordinates();

}
