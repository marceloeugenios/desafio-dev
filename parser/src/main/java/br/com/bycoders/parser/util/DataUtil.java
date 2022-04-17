package br.com.bycoders.parser.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;

@Slf4j
@UtilityClass
public class DataUtil {

    public static final String YYYYMMDD_HHMMSS = "yyyyMMdd HHmmss";

    public LocalDateTime fromStringToLocalDateTime(String dataHorario) {
        try {
            var formatter = DateTimeFormatter.ofPattern(YYYYMMDD_HHMMSS);
            return LocalDateTime.parse(dataHorario, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException(format("Data e/ou Horário %s informado não é válido!", dataHorario));
        }
    }
}
