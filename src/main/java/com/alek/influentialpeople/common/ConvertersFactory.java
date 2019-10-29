package com.alek.influentialpeople.common;

import com.alek.influentialpeople.security.service.UserRegistrationConverter;
import com.alek.influentialpeople.user.service.UserAccountConverter;
import com.alek.influentialpeople.user.service.UserResponseConverter;


public class ConvertersFactory {

    public static <E, M> TwoWayConverter<E, M> getConverter(ConverterType converterType) {
        TwoWayConverter converter = null;
        switch (converterType) {
            case USER_ACCOUNT_TO_USER:
                converter = new <E,M>UserAccountConverter();
                break;
            case USER_TO_USER_RESPONSE:
                converter = new <E,M>UserResponseConverter();
                break;
            case USER_REGISTRATION_TO_USER:
                converter = new <E,M>UserRegistrationConverter();
                break;
        }
        return converter;
    }

   public enum ConverterType {
        USER_TO_USER_RESPONSE, USER_REGISTRATION_TO_USER, USER_ACCOUNT_TO_USER
    }
}
