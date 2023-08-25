package spring.springboot.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperSkipNullValue {
    public ModelMapper skip() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true).setMatchingStrategy(MatchingStrategies.LOOSE)
                .setSkipNullEnabled(true);
        return mapper;
    }
}
