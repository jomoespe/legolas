package com.josemorenoesteban.lab.legolas.analysis;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class MockImageAnalysisServiceIT {
    private static final String SERVICE_NAME = "mock-image-analysis-service";
    
    @Test
    public void canLoadServiceByName() {
        assertNotNull( ImageAnalysisService.byName(SERVICE_NAME) );
    }
    
    @Test
    public void canAnalyseChucho() {
        Optional<ImageAnalysisResult> result = ImageAnalysisService.byName(SERVICE_NAME).analyse(() -> null);
        assertTrue(result.isPresent());
    }
}
