# Artist Album Service

It is a RESTful webservice that takes in a String that is a recording artist's name and return back in JSON format, the top 10 albums by that artist listing the individual tracks for each album. It uses the the following two LastFM APIs (http://www.last.fm/api)

1. artist.getTopAlbums: It gets the top albums for an artist on Last.fm, ordered by popularity
2. album.getInfo: It gets the metadata and tracklist for an album on Last.fm using the album name or a musicbrainz id

__Architecture__

This application is written using Spring Boot. Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can just run. It takes an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration.

Spring Boot has following features:

 - Create stand-alone Spring applications
 - Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
 - Provide opinionated 'starter' POMs to simplify your Maven configuration
 - Automatically configure Spring whenever possible
 - Provide production-ready features such as metrics, health checks and externalized configuration
 - Absolutely no code generation and no requirement for XML configuration
 
__Create a simple RESTful webservice application__

TopAlbumController class is flagged as a @RestController, meaning it is ready for use by Spring MVC to handle web requests. @RequestMapping maps "/topAlbums/{artist}" to the topAlbums method. @ParamVariable maps the parameter variable "artistName" for "{artist}" in the URL path. When invoked from a browser or using curl on the command line, the method returns JSON for com.gstv.domain.TopAlbum object. That is because @RestController combines @Controller and @ResponseBody, two annotations that results in web requests returning data rather than a view.

TopAlbumController autowires TopAlbumServiceImp which implements TopAlbumService interface. TopAlbumServiceImpl is flagged as @Service. So, it can be autowired.

__Create an Application class__

@SpringBootApplication is a convenience annotation that adds all of the following:

 - @Configuration tags the class as a source of bean definitions for the application context.
 - @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 - Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
 - @ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the HelloController.
 
The main() method uses Spring Boot¯s SpringApplication.run() method to launch an application. It doesn't need a single line of XML. It doesn't need web.xml file either. This web application is 100% pure Java and you didn¯t have to deal with configuring any plumbing or infrastructure.

The run() method returns an ApplicationContext and this application then retrieves all the beans that were created either by this app or were automatically added thanks to Spring Boot.

__Create TopAlbumService interface and its implementation class__

TopAlbumServiceImpl class implements TopAlbumService interface. getTopAlbums method TopAlbumServiceImpl class takes in an artist¯s name and sends a RESTful webservice call to LastFM artist.getTopAlbums API using Spring RestTemplate. LastFM artist.getTopAlbums API returns the artist's top 10 albums. Then it sends RESTful webservice calls to LastFM's album.getInfo API for each album to take those albums' tracks.

LastFM's domain object classes have been created based on two LastFM APIs' XML responses. After getting LastFM's domain objects from the APIs, it copies all the objects' properties to GSTV domain objects using Spring BeanUtils.

TopAlbumServiceImpl class gets properties for LastFM APIs' URLs and API key. These properties are set in application.properties in src/main/resources folder. This application.properties file will be included in artist-album-service.jar's root folder. Spring Boot loads applications.properties and TopAlbumServiceImpl class gets these properties using @Value annotation. These properties can be set with environment specific such as dev, sit, uat, pre-prod and prod.

__Run the application__

Once artist-album-service-0.1.0.jar is built using Gradle, to run the application:

from the directory where is artist-album-service-0.1.0.jar, execute: 

    java -jar artist-album-service-0.1.0.jar

Check out the service using a browser:

http://localhost:8080/topAlbums/Taylor%20Swift

You will see JSON response payload of Taylor Swift's top 10 albums and tracks.

