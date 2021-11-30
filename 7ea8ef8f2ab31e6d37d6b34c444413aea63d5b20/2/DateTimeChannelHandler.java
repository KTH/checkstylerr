package org.sputnikdev.esh.binding.bluetooth.handler;

import org.eclipse.smarthome.core.library.types.DateTimeType;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Vlad Kolotov
 */
class DateTimeChannelHandler extends SingleChannelHandler<Date, DateTimeType> {

    DateTimeChannelHandler(BluetoothHandler handler, String channelID) {
        super(handler, channelID);
    }

    @Override Date convert(DateTimeType value) {
        if (value == null) {
            return null;
        }
        return value.getCalendar().getTime();
    }

    @Override DateTimeType convert(Date value) {
        if (value == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(value);
        return new DateTimeType(calendar);
    }

    @Override Date load(Object stored) {
        //TODO figure out how it is stored (what type)
        return null;
    }
}
