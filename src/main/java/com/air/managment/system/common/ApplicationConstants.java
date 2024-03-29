package com.air.managment.system.common;

import lombok.experimental.UtilityClass;

/**
 * Contains various constants used in the air management application.
 */
@UtilityClass
public class ApplicationConstants {
    /**
     * Inner utility class for web-related constants.
     */
    @UtilityClass
    public class Web {
        /**
         * Inner utility class for validation.
         */
        @UtilityClass
        public class DataValidation {
            public static final int MIN_SIZE_OF_AIR_COMPANY_NAME = 2;
            public static final int MAX_SIZE_OF_AIR_COMPANY_NAME = 240;
            public static final int MIN_SIZE_OF_AIR_COMPANY_TYPE = 3;
            public static final int MAX_SIZE_OF_AIR_COMPANY_TYPE = 24;
            public static final int MIN_SIZE_OF_AIRPLANE_NAME = 2;
            public static final int MAX_SIZE_OF_AIRPLANE_NAME = 255;
            public static final int MIN_SIZE_OF_FACTORY_SERIAL_NUMBER = 2;
            public static final int MAX_SIZE_OF_FACTORY_SERIAL_NUMBER = 100;
            public static final int MIN_SIZE_OF_DEPARTURE_COUNTRY = 2;
            public static final int MAX_SIZE_OF_DEPARTURE_COUNTRY = 100;
            public static final int MIN_SIZE_OF_DESTINATION_COUNTRY = 2;
            public static final int MAX_SIZE_OF_DESTINATION_COUNTRY = 50;
        }

    }
}
