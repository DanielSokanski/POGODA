package pl.com.danielsokanski.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.com.danielsokanski.model.openweathermap.daily.Forecast;
import pl.com.danielsokanski.model.openweathermap.weather.CurrentWeather;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.junit.jupiter.api.Assertions.assertTrue;


class WeatherServiceTest {

    private WeatherService weatherServiceUnderTest;

    @BeforeEach
    void setUp() {
        weatherServiceUnderTest = new WeatherService("KRAKOW");
    }

    @Test
    void testLoadData() {
        // given
        weatherServiceUnderTest.loadData();
        // when
        final CurrentWeather currentWeather = weatherServiceUnderTest.getCurrentWeather();
        // then
        assertThat(currentWeather.getName(),equalTo("Kraków"));
    }

    @Test
    void testGetOneCall() {
        // given
        weatherServiceUnderTest.loadData();
        // when
        final Forecast result = weatherServiceUnderTest.getOneCall();
        // then
        assertTrue(result.getAdditionalProperties().isEmpty());
    }
}
