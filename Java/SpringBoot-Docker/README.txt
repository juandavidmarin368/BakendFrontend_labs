this is a small spring boot project which allow us to store items and get items, and get a simple hello world wth the next enpoints

EndPoints for

Get
Hello
http://localhost:7075/docker/hello


get items = 

GET
http://localhost:7075/docker/items


store items = 
POST
http://localhost:7075/docker/createitems/itemanem
{}


root
TjH=yrt/2-fg


after you've got the jar done, create a folder and inside that folder do the next steps


mkdir springboot-docker
cd springboot-docker

copy the PersisterDataDocker-0.0.1-SNAPSHOT.jar and the application.properties into that folder

vim Dockerfile

and paste what this

//**************************Dockerfile

FROM openjdk:8
COPY PersisterDataDocker-0.0.1-SNAPSHOT.jar /opt
COPY application.properties /opt
WORKDIR /opt
expose 7075
CMD ["java", "-jar", "PersisterDataDocker-0.0.1-SNAPSHOT.jar"]


//***********************

after that let's build the image 

docker build -t your-image-name .

after that to run it in background

docker run --name containerspring -d -p 7075:7075 spring-persister-one

or to run it not backgroung

docker run --name containerspring  -p 7075:7075 spring-persister-one


and that's your first spring boot dockerized application



//*********************************

LIST 

docker image ls

docker container ls

FORCE REMOVE IMAGES

docker rmi $(docker images -aq) -f



///*****************Runnig your docker with a new user for running the jar



# with this, we are pulling a ready image which has got OpenJDK version 8 ready to use
FROM openjdk:8

#if you want to install something else before executing your jar, in this case I wanted to add htop, so I must do:
#RUN apt-get install -y htop

#we are creating the user and with the flag -m we are creating its own folder under that username
RUN useradd -m springuser

#with this, we are copying what is inside the folder springboot-docker to the /opt directory on the docker image
COPY PersisterDataDocker-0.0.1-SNAPSHOT.jar /home/springuser
COPY application.properties /home/springuser

#we are changing the permissions to the .jar file and the application.properties to the new user
RUN chown -R springuser:springuser /home/springuser

#now we are switching to the new user
USER springuser

# we are setting up the working directory 
WORKDIR /home/springuser

#with this, we are opening the port 7075, replace it by yours
expose 7075
CMD "echo $(ls -l)"
#and with this, we are giving the command and the needed parameters to run the jar 
CMD ["java", "-jar", "PersisterDataDocker-0.0.1-SNAPSHOT.jar"]



//***************

