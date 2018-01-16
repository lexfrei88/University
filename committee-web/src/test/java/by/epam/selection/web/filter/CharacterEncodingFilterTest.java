package by.epam.selection.web.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by lex on 1/6/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterEncodingFilterTest {

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @Mock
    private FilterChain chain;


    @Test
    public void doFilter() throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.doFilter(request, response, chain);

        verify(request).setCharacterEncoding(any());
        verify(response).setContentType(any());
        verify(response).setCharacterEncoding(any());
        verify(chain).doFilter(request, response);
    }

}