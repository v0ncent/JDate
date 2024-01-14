package JDateTests;

import JDate.JDate;
import org.junit.jupiter.api.Test;
import JDate.Constants;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
//    /**
//     * Test to see the initialization of JDate window.
//     * @implNote  IF THIS TEST IS MAKING IT THROUGH TO YOUR COMMITS IT WILL CAUSE DEV OPS TO FAIL SO COMMENT IT OUT !!!!!!!!!!
//     * @throws InterruptedException if thread is interrupted while sleeping.
//     */
//    // THIS SHOULD BE UNCOMMENTED WHEN PUSHING TO REPOSITORY OTHERWISE DEVOPS WILL FAIL.
//    @Test
//    @SuppressWarnings("unused")
//    public void testWindowCreation() throws InterruptedException {
//        final JDate jDateFrame = JDate.getInstance();
//        // sleeps for 5 seconds, so you can check frame but not hold up other tests for long.
//        Thread.sleep(5000);
//        assertNotNull(jDateFrame);
//
//        // ensure that singleton class is working right.
//        final JDate secondObj = JDate.getInstance();
//        assertEquals(secondObj, jDateFrame);
//    }

    /**
     * Test for ensuring the constants are what they need to be.
     */
    @Test
    public void testConstants() {
        assertEquals(Constants.DEFAULT_ICON_PATH, "Images/sailor.png"); // default icon
    }
}
