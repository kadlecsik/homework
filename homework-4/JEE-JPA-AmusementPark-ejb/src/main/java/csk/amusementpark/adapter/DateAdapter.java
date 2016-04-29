
package csk.amusementpark.adapter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date>{

    @Override
    public Date unmarshal(String dateInput) throws Exception {
        return Date.from(LocalDate.parse(dateInput,DateTimeFormatter.ISO_DATE).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String marshal(Date date) throws Exception {
        return DateTimeFormatter.ISO_DATE.format(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    
}
