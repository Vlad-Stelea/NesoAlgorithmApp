package Utils;

public class DateAndTimeUtils {
    public static java.sql.Timestamp convertUtilDateToSqlTimeStamp(java.util.Date toConvert) {
        return new java.sql.Timestamp(toConvert.getTime());
    }
}
