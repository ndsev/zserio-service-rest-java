package zserio.service.rest.java.spring;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import lombok.extern.slf4j.Slf4j;
import zserio.runtime.io.InitializeOffsetsWriter;
import zserio.runtime.io.ZserioIO;

@Slf4j
public class SpringZserioOctetStreamHttpMessageConverter extends AbstractHttpMessageConverter<InitializeOffsetsWriter> {

    public SpringZserioOctetStreamHttpMessageConverter() {
        super(MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_HTML);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return InitializeOffsetsWriter.class.isAssignableFrom(clazz);
    }

    @Override
    protected InitializeOffsetsWriter readInternal(Class<? extends InitializeOffsetsWriter> clazz,
            HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        if ((inputMessage != null) && (inputMessage.getBody() != null)) {
            try {
                InitializeOffsetsWriter value = ZserioIO.read(clazz, inputMessage.getBody().readAllBytes());
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
    protected void writeInternal(InitializeOffsetsWriter value, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        try {
            byte[] byteArray = ZserioIO.write(value);
            outputMessage.getBody().write(byteArray);
        } catch (Exception e) {
            log.error("error writing stream data");
        }
    }
}
