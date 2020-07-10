package com.example.sharekernel.domain.time;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.util.Objects;
import java.util.TimeZone;

@Embeddable
@Getter
public class Time {

    private final LocalTime timeTo;
    private final LocalTime timeFrom;
    private final TimeZone timeZone;

    public Time(@NotNull LocalTime timeTo,@NotNull LocalTime timeFrom,@NotNull TimeZone timeZone) {
        if(!timeTo.isBefore(timeFrom) && (timeTo.getHour() - timeFrom.getHour()) >= 1 ) {
            throw new IllegalArgumentException("time To cant be after time from");
        }
        this.timeTo = timeTo;
        this.timeFrom = timeFrom;
        this.timeZone = timeZone;
    }

    private Time(){
        timeTo = null;
        timeFrom = null;
        timeZone = null;
    }
    public Time changeTimeTo(@NotNull LocalTime newTimeTo,@NotNull TimeZone timeZone){
       if(!timeZone.equals(this.timeZone)){
           throw new IllegalArgumentException("Cant change time with other time zone");
       }
        return new Time(newTimeTo,timeFrom,timeZone);
    }
    public Time changeTimeFrom(@NotNull LocalTime newTimeFrom,@NotNull TimeZone timeZone){
       if(!timeZone.equals(this.timeZone)){
           throw new IllegalArgumentException("Cant change time with other time zone");
       }
        return new Time(timeTo,newTimeFrom,timeZone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(timeTo, time.timeTo) &&
                Objects.equals(timeFrom, time.timeFrom) &&
                Objects.equals(timeZone, time.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeTo, timeFrom, timeZone);
    }
}
