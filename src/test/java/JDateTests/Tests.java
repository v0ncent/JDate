package JDateTests;

import JDate.Constants;
import JDate.Exceptions.JDateException;
import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.JDateFramework;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
//    /**
//     * Test to see the initialization of JDate window.
//     * @implNote  IF THIS TEST IS MAKING IT THROUGH TO YOUR COMMITS IT WILL CAUSE DEV OPS TO FAIL SO COMMENT IT OUT !!!!!!!!!!
//     * @throws InterruptedException if thread is interrupted while sleeping.
//     */
//    // THIS SHOULD BE UNCOMMENTED WHEN PUSHING TO REPOSITORY OTHERWISE DEVOPS WILL FAIL.
//    @Test
//    public void testWindowCreation() throws InterruptedException {
//        final JDate jDateFrame = JDate.getInstance();
//        // sleeps for 5 seconds, so you can check frame but not hold up other tests for long.
//        Thread.sleep(5000);
//        assertNotNull(jDateFrame);
//
//        // ensure that singleton class is working right.
//        final JDate secondObj = JDate.getInstance();
//
//        assertEquals(secondObj, jDateFrame); // Each object is equal to itself
//        // Each object is unequal to null
//        assertNotEquals(jDateFrame, null);
//        // The hashCode of each object in a group is the same as the hash code of each other member of the group
//        assertEquals(jDateFrame.hashCode(), secondObj.hashCode());
//    }

    /**
     * Test for ensuring the constants are what they need to be.
     */
    @Test
    public void testConstants() {
        assertEquals(Constants.DEFAULT_ICON_PATH, "Images/sailor.png"); // default icon
    }

    @Test
    public void testJDateFramework() {
        // ensure that we get the wanted error when no scenes have been added to list.
        assertThrows(NoScenesException.class, JDateFramework::run);
        // ensure that when no scenes have been added by user that the only thing in list is the null intro scene.
        assertEquals(1, JDateFramework.getScript().size());
        assertNull(JDateFramework.getScript().get(0));
    }
}
