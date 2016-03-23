package pl.ptm.data.service.impl;


import org.junit.Before;
import org.junit.Test;
import pl.ptm.data.service.SpeedCalculationService;

import static org.fest.assertions.Assertions.assertThat;

public class SpeedCalculationServiceImplTest {

    private SpeedCalculationService objectUnderTest;

    @Before
    public void init() {
        objectUnderTest = new SpeedCalculationServiceImpl();
    }

    @Test
    public void testCalculations() {
        assertThat(objectUnderTest.calculateSpeedInKph(0, 0)).isEqualTo(0);
        assertThat(objectUnderTest.calculateSpeedInKph(0, 60)).isEqualTo(0);
        assertThat(objectUnderTest.calculateSpeedInKph(1000, 60)).isEqualTo(60);
        assertThat(objectUnderTest.calculateSpeedInKph(1000, 30)).isEqualTo(120);
        assertThat(objectUnderTest.calculateSpeedInKph(1500, 60)).isEqualTo(90);
        assertThat(objectUnderTest.calculateSpeedInKph(1500, 30)).isEqualTo(180);
        assertThat(objectUnderTest.calculateSpeedInKph(1000, 0)).isEqualTo(0);
    }
}