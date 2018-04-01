import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestFilter {

    @Test
    public void testAuthorized() {
        String s1 = ".*/site/mail";
        Assert.assertEquals(true, "site.com/package/site/mail".matches(s1));
        Assert.assertEquals(false, "site.com/package/siаte/mail".matches(s1));
    }

}
