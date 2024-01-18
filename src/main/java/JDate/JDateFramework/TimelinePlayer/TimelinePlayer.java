package JDate.JDateFramework.TimelinePlayer;

import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.Scene;

import java.util.ArrayList;

public interface TimelinePlayer {
    void runPlayer(ArrayList<Scene> script) throws NoScenesException;
}
