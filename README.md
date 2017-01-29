LEGOLAS - Image analysis services usage Poc
===========================================
[![Build Status](https://travis-ci.org/jomoespe/legolas.svg?branch=master)](https://travis-ci.org/jomoespe/legolas)

*Legolas project* is a proof of concept of a middleware to use visual analysis services and APIs, like *Amazon Rekognition* o *Google Vision API*.


Requirements
------------

To build the project the requirements are:

  - Java 8 SDK


Build
-----

    $ ./mvnw clean install


Generate source and JavaDoc bundles

    $ ./mvnw source:jar
    $ ./mvnw javadoc:jar


So, to prepare all artifacts for a for a `deploy`

    $ ./mvnw clean install  source:jar javadoc:jar
