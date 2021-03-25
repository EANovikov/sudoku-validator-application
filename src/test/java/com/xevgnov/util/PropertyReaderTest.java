package com.xevgnov.util;

import org.junit.jupiter.api.Test;

import static com.xevgnov.util.PropertyReader.FILE_SEPARATOR_PROPERTY;
import static org.assertj.core.api.Assertions.assertThat;

public class PropertyReaderTest {

    @Test
    public void getInstance_existingPropertyRead_correctValue() {
        //When
        String property = PropertyReader.getInstance()
                .getProperty(FILE_SEPARATOR_PROPERTY);

        //Then
        assertThat(property).isEqualTo(",");
    }

    @Test
    public void getInstance_nonExistingPropertyReadDefault_defaultValue() {
        //Given
        String nonExistentProperty = "not.existing";

        //When
        String property = PropertyReader.getInstance()
                .getProperty(nonExistentProperty, ",");

        //Then
        assertThat(property).isEqualTo(",");
    }

    @Test
    public void getInstance_nonExistingPropertyReadNoDefault_null() {
        //Given
        String nonExistentProperty = "not.existing";

        //When
        String property = PropertyReader.getInstance()
                .getProperty(nonExistentProperty);

        //Then
        assertThat(property).isNull();
    }
}