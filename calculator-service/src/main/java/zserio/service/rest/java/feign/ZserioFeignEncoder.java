package zserio.service.rest.java.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import zserio.service.rest.java.spring.SpringZserioConverterRegistrar;

public class ZserioFeignEncoder extends FormEncoder {

    static final private ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(
            SpringZserioConverterRegistrar.getAdditionalMessageConverters());

    public ZserioFeignEncoder() {
        super(new FormEncoder(new SpringEncoder(objectFactory)));
    }

    public ZserioFeignEncoder(Encoder encoder) {
        super(encoder);
    }
}
