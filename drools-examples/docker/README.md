# drools docker

+ <https://hub.docker.com/u/jboss>

** x-image VS x-image-showcase**

|                 | x  | x-showcase                                               |
|:----------------|:---|:---------------------------------------------------------|
| Users and roles | -  | default(user: `kieserver`, pwd: `kieserver1!`, role: `kie-server`) |



## Kie-server\[-showcase\]
+ kie-server: <https://hub.docker.com/r/jboss/kie-server>
+ kie-server-showcase: <https://hub.docker.com/r/jboss/kie-server-showcase>

```CMD
$cmd> docker pull jboss/kie-server-showcase:7.54.0.Final
```

**No support for clustering!**
（应该指的是 docker-image 不支持集群吧，如果要部署kie-server集群需要自己弄？）

1. **kie-server** Image
> **have no users or roles configured**, so you cannot not access it by default！

2. **kie-server-showcase** Image
> **This image provides a default user `kieserver` using password `kieserver1!`
> and with the role `kie-server`.**




## business-central-workbench\[-showcase\]
```cmd
$cmd> docker pull jboss/business-central-workbench-showcase:7.54.0.Final
```

+ business-central-workbench-showcase: <https://hub.docker.com/r/jboss/business-central-workbench-showcase>

**Users and roles:**
```
# This showcase image contains default users and roles:

USER        PASSWORD    ROLE
*************************************************
admin       admin       admin,analyst,kiemgmt
krisv       krisv       admin,analyst
john        john        analyst,Accounting,PM
sales-rep   sales-rep   analyst,sales
katy        katy        analyst,HR
jack        jack        analyst,IT
```
