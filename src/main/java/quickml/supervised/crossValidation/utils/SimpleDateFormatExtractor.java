package quickml.supervised.crossValidation.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickml.data.AttributesMap;
import quickml.data.instances.InstanceWithAttributesMap;

import java.text.*;
import java.util.Date;

/**
 * Created by alexanderhawk on 6/22/14.
 */
public class SimpleDateFormatExtractor<T extends InstanceWithAttributesMap<?>> implements DateTimeExtractor<T> {
    private static final Logger logger = LoggerFactory.getLogger(SimpleDateFormatExtractor.class);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateAttribute = "created_at";

    public void setDateFormat(String dateFormatString) {
        dateFormat = new SimpleDateFormat(dateFormatString);
    }

    public void setDateAttribute(String dateAttribute) {
        this.dateAttribute = dateAttribute;
    }

    @Override
    public DateTime extractDateTime(T instance) {
        AttributesMap attributes = instance.getAttributes();
        try {
            Date currentTimeMillis = dateFormat.parse((String) attributes.get(dateAttribute));
            return new DateTime(currentTimeMillis, DateTimeZone.UTC);
        } catch (ParseException e) {
            logger.error("Error parsing date", e);
        }
        return new DateTime();
    }
}

