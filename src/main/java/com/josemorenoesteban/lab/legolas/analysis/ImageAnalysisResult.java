package com.josemorenoesteban.lab.legolas.analysis;

import static java.util.Collections.unmodifiableMap;
import static java.util.Collections.emptyMap;
import static java.util.Objects.nonNull;

import java.util.Map;

public final class ImageAnalysisResult { 
    public static final Float DEFAULT_ADULT_CONTENT_SCORE = 0F;
    
    private final Map<String, Float> labels;
    private final Float              adultContentScore;
    
    public ImageAnalysisResult(final Map<String, Float> labels, 
                                final Float adultContentScore) {
        this.labels            = unmodifiableMap( nonNull(labels) ?  labels : emptyMap() );
        this.adultContentScore = nonNull(adultContentScore) ?  adultContentScore : DEFAULT_ADULT_CONTENT_SCORE;
    }
    
    public Map<String, Float> labels() { return labels; }

    public Float adultContentScore() { return adultContentScore; }
}
