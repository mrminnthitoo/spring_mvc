package com.minnthitoo.spring_mvc.common;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Mapper {

    ModelMapper modelMapper = new ModelMapper();

    public Mapper() {
        log.info("Mapper inalized");
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> clazz) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, clazz))
                .collect(Collectors.toList());
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }


}
