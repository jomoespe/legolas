package com.josemorenoesteban.lab.legolas.analysis;

import static java.lang.System.getenv;
import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

import java.util.function.Function;
import java.util.Optional;

public class Environment {
    private static final Function<String, Optional<String>> env = name -> 
            ofNullable(getenv(name));
    
    private static final Function<String, Optional<String>> prop = name -> 
            ofNullable(getProperty(name));
    
    public static final Function<String, Optional<String>> value = name -> {
        Optional<String> propValue = prop.apply(name);
        return propValue.isPresent() ?  propValue : env.apply(name);
    };

    public static final Function<String, Optional<Integer>> asInteger = name -> 
        value.apply(name)
              .map(Integer::valueOf);

    public static final Function<String, Optional<Float>> asFloat = name -> 
        value.apply(name)
              .map(Float::valueOf);
}
