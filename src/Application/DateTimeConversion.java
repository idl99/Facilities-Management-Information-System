package Application;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeConversion {

    private DateTimeConversion(){

    }

    public static Date zonedDateTimeToDate(ZonedDateTime zonedDateTime){
        return Date.from(zonedDateTime.toInstant());
    }

    public static ZonedDateTime dateToZonedDateTime(Date date){
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Colombo"));
    }

}
