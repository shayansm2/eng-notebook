# Docker
main resources: 
1. [youtube of nana](https://www.youtube.com/watch?v=3c-iBn73dDE&t=1756s)
2. [youtube of jadi](https://www.youtube.com/watch?v=_jKNnHROiC0&t=806s)
3. [digikala docs]()

articles:
1. 
## terminology
**what is Container**: a portable package of applications with all their dependencies and configurations which makes development easier.

container is a stack of images on top of each other. The OS (linux) image is at the button and the application image is at the top.

![Untitled](static/container.png)

container repositories like [docker hub](https://hub.docker.com/) stores a lot of container images.

---
* get the image from docker hub
```commandline
docker pull imageName:version
```
after running this, all layers of the container will be downloaded
![Untitled](static/layersOfImages.png)
If layer exists locally, it will not get downloaded again (can occur in update image which only different layers will be downloaded) 
---
* creates a container from image, starts the container, if not found locally get the image from docker hub
```commandline
docker run imageName:version
docker run -d imageName
docker run --name containerName imageName:version
```
the `-d` runs the container in the detached mode

---
* see all running containers
```commandline
docker ps
docker ps -a
```
the `-a` will show all the running and stopped containers

### docker vs VMs
OS is made up of 2 layers, kernel and application.
The **main difference between docker and VMs** is that **docker virtualizes application layer and uses host's kernel** while **VMs virtualize both kernel and application layer**.

![Untitled](static/dockerVsVMs.png)

therefore:
1. size of docker is smaller than VM
2. docker containers are faster
3. linux based docker images are not compatible with the Windows kernel (should use docker toolbox for mac and Windows)

### container vs image
container is a running env for image

container consists of application image + file system + env configs

---
* see all existing images
```commandline
docker images
```
---
* start and stop the container
```commandline
docker start containerId
docker stop containerId
```
`docker run` creates a container while `docker start` restarts a stopped container

### container port vs host port
![Untitled](static/containerAndHostPorts.png)
in order to connect to a container, you should call the host port which is mapped to the container port
* run a container with binding host port to container port
```commandline
docker run -p<hostPort>:<containerPort> imageName
```
* see the logs of a container
```commandline
docker logs containerId
docker logs containerName
```
* **go to the terminal of the running container**
```commandline
docker exec -it containerId /bin/bash
docker exec -it containerName /bin/bash
```
which `-it` means iterative