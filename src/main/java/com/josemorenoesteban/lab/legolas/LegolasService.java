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

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

class LegolasService {
    public static final String     GOOGLE_ACCESS_TOKEN_ENV = "GOOGLE_ACCESS_TOKEN";

    private final GoogleCredential credential;
    private final Vision           vision;

    LegolasService() {
        credential = new GoogleCredential().setAccessToken( System.getenv(GOOGLE_ACCESS_TOKEN_ENV) );
        vision     = new Vision(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential);
    }

    List<AnnotateImageResponse> processImages(final String...filenames) throws IOException {
        return vision.images()
              .annotate( createRequest(filenames) )
              .execute()
              .getResponses();
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