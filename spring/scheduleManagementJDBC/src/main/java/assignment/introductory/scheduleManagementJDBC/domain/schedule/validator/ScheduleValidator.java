package assignment.introductory.scheduleManagementJDBC.domain.schedule.validator;

public interface ScheduleValidator {
    void checkRequestAddSchedule(String body, String author, String password);

    void checkRequestFindAllSchedule(String updateAt, String author);

    void checkRequestUpdateSchedule(String body, String author);
}
