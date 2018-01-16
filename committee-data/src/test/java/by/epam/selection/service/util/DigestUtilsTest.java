package by.epam.selection.service.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lex on 1/5/2018.
 */
public class DigestUtilsTest {

    private static final String PASSWORD_VALUE = "test";
    private static final String HASHED_PASSWORD_VALUE = "098f6bcd4621d373cade4e832627b4f6";

    @Test
    public void shouldEqualStringValueWhenMd5Hex() throws Exception {
        String actual = DigestUtils.md5Hex(PASSWORD_VALUE);
        String expected = HASHED_PASSWORD_VALUE.toUpperCase();
        Assert.assertEquals(expected, actual);
    }

}