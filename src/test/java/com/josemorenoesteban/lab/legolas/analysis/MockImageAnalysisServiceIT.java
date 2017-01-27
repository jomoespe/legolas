package com.josemorenoesteban.lab.legolas.analysis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MockImageAnalysisServiceIT {
    private static final String SERVICE_NAME = "mock-image-analysis-service";
    @Test
    public void canLoadServiceByName() {
        assertNotNull( ImageAnalysisService.byName(SERVICE_NAME) );
    }
    
    @Test
    public void canAnalyseChucho() {
        ImageAnalysisResult result = ImageAnalysisService.byName(SERVICE_NAME).analyse(() -> null);
        assertNotNull(result);
        //System.out.printf("is adult content score? %s\n", result.adultContentScore());
        //result.labels().forEach( (label, score) -> System.out.printf("%s=%s\n", label, score) );
    }
}
