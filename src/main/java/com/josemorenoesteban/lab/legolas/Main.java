package com.josemorenoesteban.lab.legolas;

import static java.nio.file.Files.readAllBytes;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.Image;
import com.google.api.services.vision.v1.model.SafeSearchAnnotation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final String    GOOGLE_ACCESS_TOKEN_ENV = "GOOGLE_ACCESS_TOKEN";
    
    private final GoogleCredential credential;
    private final Vision           vision;
    
    private Main() {
        credential = new GoogleCredential().setAccessToken( System.getenv(GOOGLE_ACCESS_TOKEN_ENV) );
        vision     = new Vision(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential);
    }
    
    public static void main(String...args) throws FileNotFoundException, IOException {
        checkEnvironment();
        new Main().processImages(args);
    }

    public static void checkEnvironment() {
        if (System.getenv(GOOGLE_ACCESS_TOKEN_ENV) == null) {
            System.err.printf("No %s environment variable.\n", GOOGLE_ACCESS_TOKEN_ENV);
            System.exit(1);
        }
    }
    
    private void processImages(final String...filenames) throws IOException {
        vision.images()
              .annotate( createRequest(filenames) )
              .execute()
              .getResponses()
              .forEach( this::showResponse );
    }
    
    private BatchAnnotateImagesRequest createRequest(final String...imageNames) throws IOException {
        List<AnnotateImageRequest> imagesToAnnotated = stream(imageNames)
              .map( this::toAnnotatedImageRequest )
              .filter( Optional::isPresent )
              .map( Optional::get )  
              .collect( toList() );
        return new BatchAnnotateImagesRequest()
                .setRequests(imagesToAnnotated);
    }

    private void showResponse(final AnnotateImageResponse response) {
        SafeSearchAnnotation safeSearch = response.getSafeSearchAnnotation();
        System.out.printf(" __ Amos que nos vamos!!!! ________________\n");
        System.out.printf("\tAdult: %s\n",    safeSearch.getAdult());
        System.out.printf("\tMedical: %s\n",  safeSearch.getMedical());
        System.out.printf("\tSpoof: %s\n",    safeSearch.getSpoof());
        System.out.printf("\tViolence: %s\n", safeSearch.getViolence());
        System.out.printf(" __________________________________________\n");
    }
    
    private Optional<AnnotateImageRequest> toAnnotatedImageRequest(final String imageName) {
        try {
            Image image = new Image().encodeContent( readAllBytes( Paths.get(imageName)));
            return Optional.of(new AnnotateImageRequest().setImage( image ));
        } catch(IOException e) {
            e.printStackTrace(System.err);
            return Optional.empty();
        }
    }
}
