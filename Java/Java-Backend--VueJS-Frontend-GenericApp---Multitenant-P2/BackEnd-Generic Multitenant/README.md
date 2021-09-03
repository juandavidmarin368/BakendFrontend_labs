# How to make a Spring Boot application layout with JWT, authorizing users based on roles over the end-points using JPA, and CrudRespository

The main purpose of this project is to be able to set up a Spring Boot application using a security layer with spring-boot-starter-security,JSON web token to protect any path we need, just allowing the endpoints we need according to the roles who have got with the right permissions, the roles we are going to be working with are SUPERADMIN, ADMIN AND USER.<br/>

if you want to read the whole tutorial just click on this <a href="https://springboot-vuejs-reactjs.blogspot.com/2019/06/how-to-make-spring-boot-application_28.html">https://springboot-vuejs-reactjs.blogspot.com/2019/06/how-to-make-spring-boot-application_28.html</a>

we are also going to be using on the project spring-boot-starter-actuator, spring-boot-starter-web, spring-boot-starter-data-jpa,spring-boot-devtools, and mysql-connector-java.

as developers we need to take advantage of time just focusing the most that we can on the business logic of the application rather than the standard functionality of the app, it takes a while when starting a project from scratch and even more when we are just starting in a new back-end language, in this case, Spring Boot, so this tutorial is for those ones who are starting in Spring boot and need to start a project from scratch wondering about how to deal with roles, how to make a login and signup end-points for their users according to the roles, how to deal with JWT, how to protect their APIs or end-points, in other words, how to set up the basic things to start working on the business logic of the solution they must give on the app, so I just upload the sources of a standard base project in which you just need to pay some attention about how it works and you are done after you do it,  enabling you to start working on the solution you have got ahead of you.
I am sure this can be helpful for many of you as me for instance with a solution which allows me to start a new project in just than a few minutes.


# read it with images at 

let's make a resume about the application before running it.<br/>

We have got an application which inside has got 2 main package structure<br/>

<strong>|-com</strong><br/>
<strong>|--root</strong><br/>
<strong>|---Generic</strong><br/>
<strong>|----AplicationLayer</strong><br/>
<strong>|----JwtSecurityLayer</strong><br/>


<strong>JwtSecurityLayer =</strong> it has got all the packages and classes to set up the security layer, let's now look at it roughly what each package does:<br/>

<strong>Models =</strong> in this package we are going to be working with 2 models which are User and Role, so that a user can have many roles, and a subpackage called audit which inside there is a DateAudit class which is going to be useful each time an user is created or updated allowing us to have a record with a datetime when an user was created or updated<br/>

<strong>Repositories =</strong> we have got under this package the repositories for the models which were made before allowing us to persist and get the data from the modles, in other words it's the mechanism which allow us to make a CRUD over the models.
<br/>

<strong>Config =</strong> inside this pacakge we have got almost all the security configurations that are required for our project, it has got a class called SecurityConfig.class which has got the next annotation:<br/>

<strong>@EnableWebSecurity</strong><br/>
<strong>@EnableGlobalMethodSecurity</strong><br/>

securedEnabled: It enables the @Secured annotation using which you can protect your controller/service methods like this:<br/>

<strong>@Secured("ROLE_ADMIN")</strong><br/>
public User getAllUsers() {}<br/>

<strong>@Secured({"ROLE_USER", "ROLE_ADMIN"})</strong><br/>
public User getUser(Long id) {}<br/>

<strong>@Secured("IS_AUTHENTICATED_ANONYMOUSLY")</strong><br/>
public boolean isUsernameAvailable() {}<br/>
jsr250Enabled: It enables the @RolesAllowed annotation that can be used like this:<br/>

<strong>@RolesAllowed("ROLE_ADMIN")</strong><br/>
public Poll createPoll() {}  <br/>
prePostEnabled: It enables more complex expression based access control syntax with @PreAuthorize and @PostAuthorize annotations like this:<br/>

<strong>@PreAuthorize("isAnonymous()")</strong><br/>
public boolean isUsernameAvailable() {}<br/>

<strong>@PreAuthorize("hasRole('USER')")</strong><br/>
public Poll createPoll() {}<br/>
  
we have also got a <strong>WebSecurityConfigurerAdapter.class</strong> which implements Spring Security’s WebSecurityConfigurer interface. providing the default security configurations and allows other classes to extend it and customize the security configurations by overriding its methods.<br/>

We’ll be accessing the APIs from ReactJs, VueJS or Angular client that will run on its own development server. To allow cross origin requests from the react client, create the following <strong>WebMvcConfig.class</strong>  inside this package 

<strong>Security = </strong> we start with * AuthenticationEntryPoint.class implements AuthenticationEntryPoint interface and provides the implementation for its commence() method. This method is called whenever an exception is thrown due to an unauthenticated user trying to access a resource that requires authentication.<br/>

In this case, we’ll simply respond with a 401 error containing the exception message.<br/>

there is a <strong>* UserPrincipal.class</strong> whose instances will be returned from our custom UserDetailsService. Spring Security will use the information stored in the UserPrincipal object to perform authentication and authorization. in other words with UserPrincipal we can get the information from the user login any where in the application.<br/>

<strong> * CustomUserDetailsService.class</strong> allow us to load an user’s data given its username<br/>
<strong> * JwtTokenProvider.class</strong>  will be used for generating a JWT after a user logs in successfully, and validating the JWT sent in the Authorization header of the requests<br/>

<strong> * JWTAuthenticationFilter.class </strong> allow us to get the JWT token from the request, validate it, load the user associated with the token, and pass it to Spring Security</br>

<strong> * CurrentUser.class </strong> is wrapper around @AuthenticationPrincipal annotation.<br/>
Spring security provides an annotation called @AuthenticationPrincipal to access the currently authenticated user in the controllers.<br/>

<strong>Security = </strong> all the security configurations that were required were set up. It’s time to finally write the login and signup APIs, so all the request and response payloads will go inside this package.<br/>

<strong>Exceptions = </strong>The APIs will throw exceptions if the request is not valid or some unexpected situation occurs.<br/>
We would also want to respond with different HTTP status codes for different types of exceptions.<br/>

Let’s define these exceptions along with the corresponding @ResponseStatus (All the exception classes will go inside this package<br/>

<strong>Controller = </strong>Here is the complete code for AuthController that contains APIs for login and signup (All the controllers in our project will go inside this package.</br></br>


<strong>AplicationLayer = </strong> it is where the business logic of our solution is going to be to avoid mixing it with the <strong>JwtSecurityLayer</strong> package files, and it has got 2 more packages<br/>

<strong>|----AplicationLayer</strong><br/>
<strong>|-----JDBC = </strong>this package is for those ones who use JDBC with DAO<br/>
<strong>|-----JPA  = </strong>this package is for setting all the subpackages for those ones who are working with the Hibernate ORM using JPA<br/>

before run the application by typing the following command from the root directory of your project <strong>mvn spring-boot:run
</strong> open the file <strong>application.properties</strong> and change the database name, port, username and password of your database, the experation token is set to be alive during 2 hours in 7200000 miliseconds, increase it or decrease it according to your needs.

