# KUBERNETES
## WHY
## Architecture
- worker node
  - also called node
  - has containers of different apps on it
  - **kubelet** process also is running on them which makes communication and running tasks on the cluster possible
  - high workload
  - need more resource
- master node
  - also called control plane node
  - at least one master node
  - runs kubernetes processes such as
    - API server: entry point to k8 cluster (UI, API, CLI)
    - controller manager: what happens in the cluster
    - scheduler: decides on which node the new pod should be scheduled
    - etcd: key value store
  - less resource is required
  - much more important that worker node -> have backup master node
- virtual network: makes communication between nodes possible
## Core Objects
pod: smallest unit in k8. abstraction of a container
- 1 app per pod
- each pod has an ip address
- if a pod crasher, the new recreated pod will have a new ip
svc: a permanent ip address attached to a pod
- external service: open to access from out of the node
- internal service: cannot access it from outside the node
ingress: out requests of the node come to ingress, and it will forward the request to services 
config map: external configs of your app -> plain text
- like urls of other services
- not good for passwords
secrets: config map for secret data and credentials -> base 64 encoding format
