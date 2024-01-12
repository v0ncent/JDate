package JDateTests;

import JDate.JDate;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    @Test
    public void testJDate() {
        final String windowName = "TestWindow";
        final JDate test = new JDate(windowName);
        assertNotNull(test);

        // ensure that default constructor gets users primary screen height
        assertEquals(Toolkit.getDefaultToolkit().getScreenSize().height, test.getGetUserScreenHeight());
        // ensure that default constructor gets users primary screen width
        assertEquals(Toolkit.getDefaultToolkit().getScreenSize().width, test.getUserScreenWidth());
    }

}
