package service;

import com.leading.demo1.configuration.Configuration;
import com.leading.demo1.service.TokenFilterService;
import com.leading.demo1.token.AccessToken;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TokenFilterServiceTest {
    private TokenFilterService filterService;
    @Before
    public void setUp() {
        Configuration configuration = Mockito.mock(Configuration.class);
        Mockito.when(configuration.getAccessThreshold()).thenReturn(2);
        Mockito.when(configuration.getIntervalThreshold()).thenReturn(10000L);
        filterService = new TokenFilterService(configuration);
    }
    @Test
    public void testIsLimited() {
        assertFalse(filterService.isLimited(new AccessToken().setToken("token").setAccessTimeStampUTC(1541033789574L)));
        assertFalse(filterService.isLimited(new AccessToken().setToken("token").setAccessTimeStampUTC(1541033789579L)));
        assertTrue(filterService.isLimited(new AccessToken().setToken("token").setAccessTimeStampUTC(1541033789589L)));
    }
}
