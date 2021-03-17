package zserio.service.rest.java.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import feign.codec.Decoder;
import zserio.service.rest.java.spring.SpringZserioConverterRegistrar;

public class ZserioFeignDecoder extends ResponseEntityDecoder {

    static final private ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(
            SpringZserioConverterRegistrar.getAdditionalMessageConverters());

    public ZserioFeignDecoder() {
        super(new SpringDecoder(objectFactory));
    }

    public ZserioFeignDecoder(Decoder decoder) {
        super(decoder);
    }
}
