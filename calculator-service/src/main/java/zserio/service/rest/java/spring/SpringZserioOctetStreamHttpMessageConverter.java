package zserio.service.rest.java.spring;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;
import zserio.runtime.io.Writer;
import zserio.runtime.io.SerializeUtil;

@Slf4j
public class SpringZserioOctetStreamHttpMessageConverter extends AbstractHttpMessageConverter<Writer> {

    public SpringZserioOctetStreamHttpMessageConverter() {
        super(MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_HTML);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return Writer.class.isAssignableFrom(clazz);
    }

    @Override
    protected Writer readInternal(Class<? extends Writer> clazz,
            HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if ((inputMessage != null) && (inputMessage.getBody() != null)) {
            try {
                // java.io.InputStream has readAllBytes() only from Java 9, use Spring alternative instead
                Writer value = SerializeUtil.deserializeFromBytes(clazz,
                        StreamUtils.copyToByteArray(inputMessage.getBody()));
                return value;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("error reading stream data");
                return null;
            }
        }
        log.error("no input data found");
        return null;
    }

    @Override
    protected void writeInternal(Writer value, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        try {
            byte[] byteArray = SerializeUtil.serializeToBytes(value);
            outputMessage.getBody().write(byteArray);
        } catch (Exception e) {
            log.error("error writing stream data");
        }
    }
}
