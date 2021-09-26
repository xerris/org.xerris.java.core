package org.xerris.core.utils.xml;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Xml {
    private static XmlMapper xmlMapper;

    public static <T> String toXml(T subject) {
        try {
            return instance().writeValueAsString(subject);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> T fromXml(String fromXml, Class<T> ofType) {
        try {
            return instance().readValue(fromXml, ofType);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static XmlMapper instance() {
        if (xmlMapper == null) {
            xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return xmlMapper;
    }
}
