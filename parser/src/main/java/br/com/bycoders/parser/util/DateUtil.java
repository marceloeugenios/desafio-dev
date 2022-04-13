package br.com.bycoders.parser.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtil {

    public LocalDateTime fromStringToLocalDateTime(String dataHorario) {
        var formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        return LocalDateTime.parse(dataHorario, formatter);
    }
}
