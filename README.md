## demoapp
Springboot "Hello World" project with kubernetes and ingress using Jenkins

Shared URL: http://myweb-40-68-140-105.nip.io:32223/hello

* First of all, an Ubuntu-focal installed Azure VM is used as the environment(Size: Standard D4ads v5).

### Preparing spring-boot project

* Java, Maven installed to the server. Simple spring-boot hello world project is created.

### Preparing Jenkins Environment for the CI/CD pipeline

* Jenkins installed to the server separately. The port 8081 is used as public access: http://40.68.140.105:8081/ uname:admin pwd:admin
* The required plugins are installed: Kubernetes, Git, etc.
* New pipeline created and the project's GitHub repo hooked with the git push actions for triggering pipeline automatically.

### Preparing Kubernetes Cluster

* Kubernetes installed via kubespray to the same server(cluster has only 1 node) to deploy spring-boot sample app as a service.
* nginx ingress controller is added to the kubernetes cluster.


### Preparing CI/CD processes

* A Dockerfile is prepared for building image.
* DockerHub is used as the image registry: https://hub.docker.com/r/fcomak/myrepo
* A Jenkinsfile is prepared for all of the CI/CD stages.
* A myweb.yaml named manifest file is prepared for deploying docker image on K8S.
* Login credentials are added to Jenkins for GitHub and DockerHub.
* Kubernetes cluster config is added to "Configure Clouds" settings of Jenkins and rest of the settings are set(k8s cloud details, pod templates, etc.).


## CI/CD Stages

* Since our Jenkinsfile is selected as the Script Path of Jenkins pipeline, all of the stages are prepared in Jenkinsfile.
* The stages are:

1. Checkout GitHub Source
2. Build Project (maven compile)
3. Run Tests (maven test)
4. Build Docker Image (docker build from Dockerfile)
5. Push Docker Image (docker push to the registry)
6. Deploy App on K8S (deploy service to kubernetes cluster using manifest)


## Addirional Info

* nip.io is used for the simple wildcard DNS auto-generation from the IP address.
* Since the port of the service is 32223 in myweb.yaml file, http://myweb-40-68-140-105.nip.io:32223/ URL can be used to access Index endpoint.
* hello-world endpoint is http://myweb-40-68-140-105.nip.io:32223/hello.
* nginx ingress controller is used. Because it is managing/controlling and welcoming request traffic from outside, and then, transmits requests to the services in cluster.
* myweb service has 3 replicas for experiencing the load balancing feature.



## Some Screenshots:

* Jenkins Pipeline:
<img src="https://i.ibb.co/K9TQLzw/Screenshot-2.png">


* GitHub Pepo Webhook:
<img src="https://i.ibb.co/pnYDrrN/github-repo-webhook.png">


* Open-ports on Azure VM(Public IP:40.68.140.105) networking for the public access:
<img src="https://i.ibb.co/XstN16x/azure-open-ports.png">


* Pipeline Dashboard Auto-triggering View after Git Push action:
<img src="https://i.ibb.co/pftNcFs/pipeline-triggering.png">


* ```kubectl get nodes -A```, ```kubectl get svc -A```, ```kubectl get pods -A```
<img src="https://i.ibb.co/m5sbMVW/kubectl-commands.png">

* The spring boot app (http://myweb-40-68-140-105.nip.io:32223/hello):
<img src="https://i.ibb.co/gWmDh14/hello.png">