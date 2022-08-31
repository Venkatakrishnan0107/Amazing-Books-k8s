# order-service-k8

Building a spring boot application and deploying it in Minikube with MySQL database.

1. First start minikube
2. run minikube docker-env
3. run the last command to get into minikube docker access
4. docker images to check
5. run the db-deployment.yml file by going to the directory using -> kubectl apply -f <file-name>
6. this would run the mysql deployment and create service
7. get the pods using -> kubectl get pods
8. take the pod id and execute-> kubectl exec -it <pod_id> /bin/bash
9. connect to mysql using-> mysql -h mysql -u root -p root

10. prepare the app-deployment file.
11. Build the project and prepare the dockerfile
12. run the app-deployment.yml -> kubectl apply -f <file-name>
13. verify the pods and deployments

14. Get the url using minikube service <service-name> --url

![image](https://user-images.githubusercontent.com/73943222/187267366-c8fec2f5-ea15-4665-ba82-c9c2a5d9584a.png)

Hit from postman

![image](https://user-images.githubusercontent.com/73943222/187267492-412cb7d5-474d-4436-ace6-2d39b16d0da4.png)
