 package com.josemorenoesteban.lab.legolas.analysis;

import static java.util.ServiceLoader.load;
import static java.util.stream.StreamSupport.stream;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface ImageAnalysisService {
    static ImageAnalysisService first() {
        final ServiceLoader<ImageAnalysisService> loader = load(ImageAnalysisService.class);
        return stream(loader.spliterator(), true)
               .findFirst()
               .orElseThrow( () -> new RuntimeException("Cannot load service any image analysis service") );
    }

    static ImageAnalysisService byName(final String name) {
        final ServiceLoader<ImageAnalysisService> loader = load(ImageAnalysisService.class);
        return stream(loader.spliterator(), true)
               .filter( service -> Objects.equals(service.name(), name))
               .findFirst()
               .orElseThrow( () -> new RuntimeException("Cannot load service image analysis service with name " + name) );
    }

    static Stream<ImageAnalysisService> services() {
        final ServiceLoader<ImageAnalysisService> loader = load(ImageAnalysisService.class);
        return stream(loader.spliterator(), true);
    }

    String name();

    ImageAnalysisResult analyse(final Supplier<ByteBuffer> image);
}
