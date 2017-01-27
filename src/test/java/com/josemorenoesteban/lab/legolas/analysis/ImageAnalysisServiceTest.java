package com.josemorenoesteban.lab.legolas.analysis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ImageAnalysisServiceTest {
    @Test
    public void canLoadAService() {
        assertNotNull( ImageAnalysisService.first() );
    }

    @Test
    public void canLoadAllServices() {
        ImageAnalysisService.stream().forEach( service -> assertNotNull(service) );
    }
}
