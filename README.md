# amazing-books-k8s
Multiple microservices for amazing books using k8s

1. Deployed all Services in Kubernetes cluster using minikube
![image](https://user-images.githubusercontent.com/73943222/187729262-b1c3cb0f-6f11-4d74-aeb6-19807bc06600.png)

2. Added MySQL Database as a single database for all microservice
3. Added Zipkin and Sleuth for logging purpose


Configuration:

1. API Gateway - NodePort
2. Order-Service - ClusterIP
3. Issue-Service - ClusterIP
4. Book-Service - ClusterIP
5. Zipkin - NodePort
6. MySQL - ClusterIP

![image](https://user-images.githubusercontent.com/73943222/187729735-843eefb3-cf86-4a66-a229-cf52fa8dfe80.png)

Zipkin:

![image](https://user-images.githubusercontent.com/73943222/187729959-ea4ce4d1-44be-4e21-ba99-100411665faa.png)
