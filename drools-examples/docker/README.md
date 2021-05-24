# drools docker

+ <https://hub.docker.com/u/jboss>

<font color="RED">VFIXME 2021-05-24 >>>>  </font>
1. 貌似 business-workbench 与 kie-server 并没有 连通！尝试过别名`kie-web` OR `kie_web`。

**x-image VS x-image-showcase**

|                 | x  | x-showcase                                               |
|:----------------|:---|:---------------------------------------------------------|
| Users and roles | -  | default(user: `kieserver`, pwd: `kieserver1!`, role: `kie-server`) |

## port & http

|                  | host:port  |                                                         |
|:-----------------|:-----------|:--------------------------------------------------------|
| kie-server       | 18080:8080 | http://localhost:18080/kie-server/services/rest/server/ |
|                  |            | (swagger) http://127.0.0.1:18080/kie-server/docs/       |
|                  |            |                                                         |
| business-central | 18081:8080 | http://localhost:18081/business-central                 |
|                  |            | (swagger) http://127.0.0.1:18081/business-central/docs/ |
|                  | 18001:8001 | the GIT repository is SSH at port 8001                  |



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
