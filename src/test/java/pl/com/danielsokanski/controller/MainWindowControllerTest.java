package pl.com.danielsokanski.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainWindowControllerTest {

    private MainWindowController mainWindowControllerUnderTest;
    private String myCityName = "KRAKOW";
    private String newCityName = "WARSZAWA";
    @BeforeEach
    void setUp() {
        mainWindowControllerUnderTest = new MainWindowController();
    }

    @Test
    void testInitialize() {
        //when
        clearLabels();
        // then
        assertTrue(myCityName.isEmpty());
        assertTrue(newCityName.isEmpty());
    }

    private void clearLabels() {
        myCityName = "";
        newCityName = "";
    }
}
