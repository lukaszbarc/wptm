package pl.ptm.data.parser;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jbogacz on 25.03.2016.
 */
public class ZtmFormatUtilsTest {

    private static final String GROUP_LINE = "   1001   KIJOWSKA,                        --  WARSZAWA";
    private static final String STOP_LINE = "100101   2      Ul./Pl.: TARGOWA,                          Kier.: AL.ZIELENIECKA,                   Y= 52.248678     X= 21.044226";
    private static final String VEHICLES_LIST_LINE = "            L 11  - sta³y:                3^    6^    7     9^   132    18^   22^   23^   26^   27^   28  ";

    private static final Set<Long> tramsNumbers = new HashSet<>();

    @Test
    public void testIsStopsGroupSection() throws Exception {
        assertThat(ZtmFormatUtils.isStopsGroupSection(GROUP_LINE), is(true));
    }

    @Test
    public void testIsStop() throws Exception {
        assertThat(ZtmFormatUtils.isStop(STOP_LINE), is(true));
    }

    @Test
    public void testIsVehiclesList() throws Exception {
        assertThat(ZtmFormatUtils.isVehiclesList(VEHICLES_LIST_LINE), is(true));
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

    @Test
    public void testParseTramsNumbers() throws Exception {
        assertThat(ZtmFormatUtils.parseTramsNumbers(40, VEHICLES_LIST_LINE).size(), is(10));
    }
}