package zserio.service.rest.java.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Getter;

@Configuration
public class SpringZserioConverterRegistrar implements WebMvcConfigurer {
    @Getter
    static private List<HttpMessageConverter<?>> additionalMessageConverters = initAdditionalMessageConverters();

    static private synchronized List<HttpMessageConverter<?>> initAdditionalMessageConverters() {
        if (additionalMessageConverters == null) {
            additionalMessageConverters = new ArrayList<HttpMessageConverter<?>>();
            additionalMessageConverters.add(new SpringZserioOctetStreamHttpMessageConverter());
        }
        return additionalMessageConverters;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.addAll(additionalMessageConverters);
    }
}
