package com.josemorenoesteban.lab.legolas.analysis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ImageAnalysisServiceTest {
    @Test
    public void canLoadFirstService() {
        assertNotNull( ImageAnalysisService.first() );
    }

    @Test
    public void canLoadServiceByName() {
        assertNotNull( ImageAnalysisService.byName("mock-image-analysis-service") );
    }

    @Test
    public void canLoadAllServices() {
        ImageAnalysisService.services().forEach( service -> assertNotNull(service) );
    }
}
