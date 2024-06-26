package seedu.findvisor.model.person;

//@@author Dethada
import static seedu.findvisor.commons.util.AppUtil.checkArgument;
import static seedu.findvisor.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.findvisor.commons.util.StringUtil.isSafeString;
import static seedu.findvisor.logic.Messages.MESSAGE_SAFE_STRING_INPUT_CHARACTERS;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.findvisor.commons.util.DateTimeUtil;
import seedu.findvisor.commons.util.ToStringBuilder;

/**
 * Represents a meeting with a Person in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(LocalDateTime, LocalDateTime)}
 */
public class Meeting {
    public static final int MAX_REMARK_LENGTH = 200;
    public static final String MESSAGE_DATETIME_CONSTRAINTS = "Meetings consist of 2 datetimes, the start datetime and "
            + "end date time.\n"
            + "Each input string for datetime must be in the format dd-MM-yyyy'T'HH:mm, for example 23-02-2024T16:00.\n"
            + "The start datetime must be before the end date time.";
    public static final String MESSAGE_DATE_CONSTRAINT = "Meeting date is specified in the following format: "
            + DateTimeUtil.HUMAN_DATE_PATTERN;
    public static final String MESSAGE_REMARK_CONSTRAINTS = "Remark is at most "
            + MAX_REMARK_LENGTH + " characters long and can only contain " + MESSAGE_SAFE_STRING_INPUT_CHARACTERS;

    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String remark;

    /**
     * Constructs an {@code Meeting}.
     *
     * @param start The start datetime of the meeting.
     * @param end The end datetime of the meeting.
     */
    public Meeting(LocalDateTime start, LocalDateTime end, String remark) {
        requireAllNonNull(start, end, remark);
        checkArgument(isValidDateTime(start, end), MESSAGE_DATETIME_CONSTRAINTS);
        checkArgument(isValidRemark(remark), MESSAGE_REMARK_CONSTRAINTS);
        this.start = start;
        this.end = end;
        this.remark = remark;
    }

    /**
     * Returns true if the given start and end datetimes are valid.
     * The start and end datetime are valid if the start is before the end datetime.
     */
    public static boolean isValidDateTime(LocalDateTime start, LocalDateTime end) {
        return start.isBefore(end);
    }

    /**
     * Returns true if the given remark is valid.
     * The remark is valid if it is at most {@code MAX_REMARK_LENGTH} characters long and contains only safe characters.
     */
    public static boolean isValidRemark(String remark) {
        boolean validLength = remark.length() <= MAX_REMARK_LENGTH;
        return validLength && isSafeString(remark);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getRemark() {
        return remark;
    }

    public String getStartString() {
        return DateTimeUtil.dateTimeToString(start);
    }

    public String getEndString() {
        return DateTimeUtil.dateTimeToString(end);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Start Datetime", getStartString())
                .add("End Datetime", getEndString())
                .add("Remark", remark)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        // We only need to compare up to minutes, comparing seconds and nanos is unnecessary and can cause issues.
        return DateTimeUtil.isEqualsDateTimeMinutes(start, otherMeeting.start)
                && DateTimeUtil.isEqualsDateTimeMinutes(end, otherMeeting.end)
                && remark.equals(otherMeeting.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
//@@author

