package com.josemorenoesteban.lab.legolas;


import com.google.api.services.vision.v1.model.AnnotateImageResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Cli {
    private final LegolasService legolas;
    
    private Cli() {
        legolas = new LegolasService();
    }
    
    public static void main(String...args) throws FileNotFoundException, IOException {
        checkEnvironment();
        new Cli().perform();
    }

    private void perform(final String...args) throws IOException  {
        legolas.processImages(args)
                .forEach( this::showResponse );
    }
    
    public static void checkEnvironment() {
        if (System.getenv(LegolasService.GOOGLE_ACCESS_TOKEN_ENV) == null) {
            System.err.printf("No %s environment variable.\n", LegolasService.GOOGLE_ACCESS_TOKEN_ENV);
            System.exit(1);
        }
    }

    private void showResponse(final AnnotateImageResponse response) {
        try {
            System.out.println( response.toPrettyString() );
        } catch (IOException e) {
            System.err.printf("io exception getting response pretty print. error message: %s\n", e.getMessage());
            System.out.printf("response=%s\n", response );
        }
    }
}
