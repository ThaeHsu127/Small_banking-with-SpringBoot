package com.mytel.smallBanking.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;

@Service
public class MytelUtil {
    private static final Logger logger = LoggerFactory.getLogger(MytelUtil.class);

    public static final ObjectMapper mapper = new ObjectMapper();

    private static final double DELTA = 0.0001d;

    private static final NumberFormat nf = NumberFormat.getInstance();

    private static final NumberFormat readableNF = NumberFormat.getInstance(Locale.ENGLISH);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");

    private static final DateTimeFormatter onlyDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        nf.setMaximumFractionDigits(1);
    }

    public static String toReadableKyats(Double number) {
        return readableNF.format(number) + " Kyats";
    }

    public static void logProcessTime(String function, LocalDateTime begin, LocalDateTime end) {
        long runtime = ChronoUnit.MILLIS.between(begin, end);
        logger.info("{} [{} ms]", function, runtime);
    }

    public static boolean isNotNull(Object o) {
        return Optional.ofNullable(o).isPresent();
    }

    public static boolean checkVisible(boolean deleted, boolean testing, boolean inWhitelist) {
        boolean visible = !deleted;
        if (testing) visible &= inWhitelist;
        return visible;
    }

    public static String getURL(HttpServletRequest request) {
        return String.format("%s %s", request.getMethod(), request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
    }

    public static <T> T getOrDefault(Object o, Object defaultValue, Class T) {
        return (T) Optional.ofNullable(o).orElse(defaultValue);
    }

    public static <T> T convertToObject(String raw, Class T) {
        return (T) mapper.convertValue(raw, T);
    }

    public static String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error("[{}] Can write object as string: {}", e.getClass().getSimpleName(), e.getMessage());
            return o.toString();
        }
    }

    public static boolean notEqual(double a, double b) {
        return !(Math.abs(a - b) < DELTA);
    }

    public static Double getDoubleUpToFirstFractionDigit(double a) {
        if (Double.isInfinite(a) || Double.isNaN(a)) {
            return Double.parseDouble(nf.format(a));
        }
        return a;
    }

    public static String normalizePhoneNo(String phoneNo) {
        if (phoneNo == null) {
            return null;
        }
        if (phoneNo.startsWith("959")) {
            return phoneNo;
        }
        if (phoneNo.startsWith("9")) {
            return "95" + phoneNo;
        }
        if (phoneNo.startsWith("09")) {
            return "95" + phoneNo.substring(1);
        }
        if (phoneNo.startsWith("+959")) {
            return phoneNo.substring(1);
        }
        return phoneNo;
    }
}

