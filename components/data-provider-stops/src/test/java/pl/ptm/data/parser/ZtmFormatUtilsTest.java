package pl.ptm.data.parser;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jbogacz on 25.03.2016.
 */
public class ZtmFormatUtilsTest {

    private static final String GROUP_LINE = "   1001   KIJOWSKA,                        --  WARSZAWA";
    private static final String STOP_LINE = "100101   2      Ul./Pl.: TARGOWA,                          Kier.: AL.ZIELENIECKA,                   Y= 52.248678     X= 21.044226";

    @Test
    public void testIsStopsGroupSection() throws Exception {
        assertThat(ZtmFormatUtils.isStopsGroupSection(GROUP_LINE), is(true));
    }

    @Test
    public void testIsStop() throws Exception {
        assertThat(ZtmFormatUtils.isStop(STOP_LINE), is(true));
    }

    @Test
    public void testIsSpaceAt() throws Exception {
        assertThat(ZtmFormatUtils.isSpaceAt(2, GROUP_LINE), is(true));
    }

    @Test
    public void testIsNotSpaceAt() throws Exception {
        assertThat(ZtmFormatUtils.isNotSpaceAt(3, GROUP_LINE), is(true));
    }

    @Test
    public void testParseWordAt() throws Exception {
        assertThat(ZtmFormatUtils.parseStringAt(10, GROUP_LINE), is("KIJOWSKA"));
    }
}