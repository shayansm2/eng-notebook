minikube commands
```
minikube start
minikube dashboard
```

resource Types: `po`, `svc`, `rc`, `rs`, `nodes`, `ns`, `deployment`
### get command: 
```
kubectl get <resourceType>
kubectl get all
```

| option               | meaning                        |
| -------------------- | ------------------------------ |
| `-o wide`            | more columns to show           |
| `-l key=value`       | filter by label                |
| `--show-labels`      | show labels                    |
| `-n <namespaceName>` | within the namespace           |
| --all-namespaces     | not the current namespace, all |
### describe command:
```
kubectl describe <resourceType> <resourceName>
```
### create and update commands:
```
kubectl create -f <manifest>.yml
kubectl create <resourceType> <resourceName>

kubectl apply -f <manifest>.yml
```

| option               | meaning                          |
| -------------------- | -------------------------------- |
| `--image=nginx`      | define the base image            |
| `-n <namespaceName>` | create/update in which namespace |
### delete command:
```
kubectl delete <resourceType> <resourceName>
kubectl delete -f <manifest>.yml
```
### explain command:
```
kubectl explain <resourceType>.<option>
kubectl explain pod.spec
```
### run pod command:
```
kubectl run <podName> --image <podImage>
```

| option             | meaning                                          |
| ------------------ | ------------------------------------------------ |
| `--dry-run=client` | not running the command and just showing changes |
| `-o yaml`          | show yaml output                                 |
| `--port 80`        | expose port                                      |
| `-n <namespace>`   | create the pod in this namespace                 |
### add label
```
kubectl label <resourceType> <resourceName> <label>=<value>
```
### scale command:
```
kubectl scale --replicas=6 -f <manifest>.yml
kubectl scale --replicas=6 <resourceType> <resourceName>
```
### deployment rollout commands:
```
kubectl rollour history deployment <name>
kubectl rollout undo deployment <name>
kubectl rollout pause deployment <name>
kubectl rollout resume deployment <name>
```

| option            | meaning                           |
| ----------------- | --------------------------------- |
| `--to-revision=1` | back to which deployment revision |

### other commands
```
kubectl version
kubectl cluster-info
kubectl api-resources
kubectl api-versions
```

---

todos:

```
keubectl get po -L kay1,key2
keubectl get po -l key=value
keubectl get po -l key
keubectl get po -l `!key`
keubectl get po -l key in (val1,val2)
keubectl get po -l key notin (val1,val2)
keubectl get po -l key1=val1,key2=val2

kubectl get nodes -L gpu
kubectl get nodes -l gpu=true
```

```
kubectl describe
```


create replication controller
```
kubectl run kubia --image=imageName --port=80 --generator=run/v1
```

create service
```
kubectl expose rc kubia --type=loadBalancer --name servceName 
```

resources:
- kubernetes in action book
- kubenetes slide