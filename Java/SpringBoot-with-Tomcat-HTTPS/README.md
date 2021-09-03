# SpringBoot-with-Tomcat-HTTPS


This an easy example to run a SpringBoot application with tomcat using HTTPS<br><br>


activating SSL on SpringBoot is an easy step indeed, you just to copy and paste this code at application.properties<br><br>

server.port=8443<br>
server.ssl.enabled=true<br>
security.require-ssl=true<br>
server.ssl.key-store=YOURPKCSFILE.p12<br>
server.ssl.key-store-password=YOURPASSWORD<br>
server.ssl.key-alias=YOURDOMAIN.COM<br>
server.ssl.key-password=YOURPASSWORD<br>

<br><br>
this is jus quite easy from the code side, the steps about getting the SSl with Namecheap in this case or any other SSL provider are on this link <a href="https://springboot-vuejs-reactjs.blogspot.com/2019/07/how-to-secure-springboot-with-ssl-and.html">https://springboot-vuejs-reactjs.blogspot.com/2019/07/how-to-secure-springboot-with-ssl-and.html</a>



<strong>NOTE: "</strong> It is a good idea to have the application.properties close to the final .jar because if you need to change the server port or you want to change another .p12 or any other setting, you are not going to recompile everything you, just change the values you need on that file.
<br><br>

if you want to have that .p12 file hardcoded then you need to set this command line in the application.properties 
<br><br>
server.ssl.key-store=classpath:YOURPKCSFILE.p12
<br><br>


it is because you are going to have the .p12 file inside the resources folder and compile it and let it inside the same .jar
<br><br>


Do not try to run the application in a localhost environment because it just won't work unless you are compiling the code at the server the application is going to be running, what I just do is compiling the code on the terminal I am working with and after that, I upload the final .jar to the remote server  


<strong>"</strong>
