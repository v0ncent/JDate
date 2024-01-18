package JDate.JDateFramework.TimelinePlayer;

import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.Scene;

import java.util.ArrayList;

public abstract class TimelinePlayer {
    protected abstract void runPlayer(ArrayList<Scene> script) throws NoScenesException;
}
