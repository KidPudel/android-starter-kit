> We have a server. we can install on it virtual mashines and run it. or..  
> We have a server. we install one operating system. and install a docker and create docker containers with different OS's.

Virtual mashines virtualizes **hardware**, while _docker virtualizes **software**_


![Docker](https://user-images.githubusercontent.com/63263301/227731841-7464eb9d-8de2-4159-8e98-0eebe5ba202c.png)


> Docker is a tool for creating and deploying isolated environments (read: virtual machines) for running applications with their dependencies.

A few terms you should be familiar with (including a baking analogy for ease of understanding):

`Docker Container` – A single instance of the application, that is live and running. In our analogy, this is a cookie.

`Docker Image` – A blueprint for creating containers. Images are immutable and all containers created from the same image are exactly alike. In our analogy this is the cookie cutter mould.

`Dockerfile` – A text file containing a list of commands to call when creating a Docker Image. In our analogy this is the instructions to create the cookie cutter mould.

# Why (as a data scientist) should I care?
Broadly, there are two use cases for Docker in ML:

`Run Only` (run): A run-only container means you edit your code on a local IDE and run it with the container so that your code runs inside the container. Here is one good example.  

`End-to-End Platform` (run and interact): An end-to-end platform container means you have an IDE or Jupyter Notebook / Lab, and your entire working environment, running in the container, and also run the code inside it (with the exception of the working file system which can be mounted).
We will focus on the second use case.

Reasons to use Docker in data science projects
Using docker containers means you don't have to deal with "works on my machine" problems.
Generally, the main advantage Docker provides is standardization. This means you can define the parameters of your container once, and run it wherever Docker is installed. This in turn provides two major advantages:

Reproducibility: Everyone has the same OS, the same versions of tools etc. This means you don't need to deal with "works on my machine" problems. If it works on your machine, it works on everyone's machine.
Portability: This means that moving from local development to a super-computing cluster is easy. Also, if you're working on open source data science projects, like we do at DAGsHub, you can provide collaborators with an easy way to bypass setup hassle.
Another huge advantage – learning to use Docker will make you a better engineer, or turn you into a data scientist with super powers. Many systems rely on Docker, and it will help you turn your ML projects into applications and deploy models into production.


docker is reproducing envioronments

by crrating a dockerfile tgat defines a blueprint for images
images are a tamplets for running a container
container is a running process like some application with defined environment 