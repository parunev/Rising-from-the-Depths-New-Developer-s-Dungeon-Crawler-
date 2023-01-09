package Main;

import java.awt.*;

// Extended version of Rectangle, still has all the functions of rectangle, but we can add more
public class EventRect extends Rectangle {

    int eventRectDefaultX, eventRectDefaultY;
    boolean eventDone = false; // we can check if the event already happened, gives us an option for one time only event
}
