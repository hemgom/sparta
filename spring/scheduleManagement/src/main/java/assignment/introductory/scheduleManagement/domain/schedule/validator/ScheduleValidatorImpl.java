package assignment.introductory.scheduleManagement.domain.schedule.validator;

import assignment.introductory.scheduleManagement.exception.customException.NotValidRequestDataException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static assignment.introductory.scheduleManagement.domain.schedule.enums.ValidDataTimeFormat.UPDATE_AT;
import static assignment.introductory.scheduleManagement.domain.schedule.enums.ValidFieldLength.*;
import static assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode.BAD_REQUEST_ADD_SCHEDULE;
import static assignment.introductory.scheduleManagement.exception.exceptionCode.ExceptionCode.BAD_REQUEST_FIND_ALL_SCHEDULE;

@Component
public class ScheduleValidatorImpl implements ScheduleValidator {

    @Override
    public void checkRequestAddSchedule(String body, String author, String password) {
        List<String> notValidField = new ArrayList<>();

        if (!StringUtils.hasText(body) || !isValidLengthOfBody(body))
            notValidField.add(BODY.getField());

        if (!StringUtils.hasText(author) || !isValidLengthOfAuthor(author))
            notValidField.add(AUTHOR.getField());

        if (!StringUtils.hasText(password) || !isValidLengthOfPassword(password))
            notValidField.add(PASSWORD.getField());

        if (notValidField.isEmpty()) return;
        throw new NotValidRequestDataException(BAD_REQUEST_ADD_SCHEDULE, notValidField.toString());
    }

    @Override
    public void checkRequestFindAllSchedule(String updateAt, String author) {
        List<String> notValidField = new ArrayList<>();

        if (StringUtils.hasText(updateAt) &&!isValidFormatOfUpdateAt(updateAt))
            notValidField.add(UPDATE_AT.getField());

        if (!StringUtils.hasText(author)) author = "";

        if (!isValidLengthOfAuthor(author))
            notValidField.add(AUTHOR.getField());

        if (notValidField.isEmpty()) return;
        throw new NotValidRequestDataException(BAD_REQUEST_FIND_ALL_SCHEDULE, notValidField.toString());
    }

    @Override
    public void checkRequestUpdateSchedule(String body, String author) {
        List<String> notValidField = new ArrayList<>();

        if (!StringUtils.hasText(body) || !isValidLengthOfBody(body))
            notValidField.add(BODY.getField());

        if (!StringUtils.hasText(author) || !isValidLengthOfAuthor(author))
            notValidField.add(AUTHOR.getField());

        if (notValidField.isEmpty()) return;
        throw new NotValidRequestDataException(BAD_REQUEST_ADD_SCHEDULE, notValidField.toString());
    }

    private boolean isValidLengthOfBody(String body) {
        return BODY.getLength() >= body.length();
    }

    private boolean isValidLengthOfAuthor(String author) {
        return AUTHOR.getLength() >= author.length();
    }

    private boolean isValidLengthOfPassword(String password) {
        return PASSWORD.getLength() >= password.length();
    }

    private boolean isValidFormatOfUpdateAt(String updateAt) {
        return Pattern.matches(UPDATE_AT.getRegex(), updateAt);
    }
}
