package com.josemorenoesteban.lab.legolas.analysis.mock;

import static java.util.Collections.unmodifiableMap;
import static java.util.AbstractMap.SimpleEntry;
import static java.util.stream.Collectors.toMap;

import com.josemorenoesteban.lab.legolas.analysis.ImageAnalysisResult;
import com.josemorenoesteban.lab.legolas.analysis.ImageAnalysisService;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MockImageAnalysisService implements ImageAnalysisService {

    @Override
    public String name() { return "mock-image-analysis-service"; }

    @Override
    public ImageAnalysisResult analyse(final Supplier<ByteBuffer> image) {
        final Map<String, Float> labels = unmodifiableMap(Stream.of(
                new SimpleEntry<>("Dog",    0.9f),
                new SimpleEntry<>("Animal", 0.92f) )
            .collect(toMap( e -> e.getKey(), e -> e.getValue())));
        return new ImageAnalysisResult(labels, 0.5f);
    }
}
