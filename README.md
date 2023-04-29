# hotrodspringboot

# Deployment steps (For Openshift Deployment):
For current branch, most of the steps remain as-is in the Red Hat Developer blog: https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid

**In order to deploy current branch project, there should be make below described Steps Chages**
1. In step [How to deploy the Spring Boot project](https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid#how_to_deploy_the_spring_boot_project):
Instead of clone the **openshift** brach, must clone **RHDG_8.4** branch:

**Old step:**
~~~
git clone -b openshift https://github.com/alexbarbosa1989/hotrodspringboot
~~~
**New step:**
~~~
git clone -b RHDG_8.4_POST https://github.com/alexbarbosa1989/hotrodspringboot
~~~

2. In step [Deploy the Spring Boot application
](https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid#deploy_the_spring_boot_application):
Instead of using **fabric8** maven comand, should use **JKube** maven commands for aplication deployment on the Openshift environment:

**Old step:**
~~~
mvn clean fabric8:deploy -Popenshift
~~~
**New step:**
~~~
mvn clean package oc:build -Popenshift
mvn oc:apply -Popenshift
~~~

3. Execute the web POST operation to store Person Object in Data Grid Cache:

~~~
curl -X POST --header 'Content-Type: application/json' -d '{ "firstName": "Luis", "lastName": "Diaz", "bornYear": "1997", "bornIn": "Colombia" }' http://localhost:8080/redhat/update-cache/sessions/key1
~~~

~~~
curl -X POST --header 'Content-Type: application/json' -d '{ "firstName": "Sadio", "lastName": "Mané", "bornYear": "1992", "bornIn": "Senegal" }' http://localhost:8080/redhat/update-cache/sessions/key2
~~~

4. Execute the web GET operation to retrieve cache value from Data Grid Cache using the Key:

~~~
curl -X GET http://localhost:8080/redhat/get-cache-value/sessions/key1
~~~


The other steps remains exactly the same as in the [Red Hat Developer blog](https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid)

######
# Deployment Steps (For On-Premise Execution)
1. Clone the project:
~~~
git clone -b RHDG_8.4_POST https://github.com/alexbarbosa1989/hotrodspringboot
~~~

2. Execute it:

~~~
mvn clean install
~~~
~~~
java -jar target/hotrodspringboot-0.0.1-SNAPSHOT.jar
~~~

3. Add user in Data Grid instance:

~~~
User: jdgUser
Password: jdgUs3R

#or via CLI
${RHDG_HOME}/bin/cli.sh user create jdgUser -p 'jdgUs3R'
~~~

**NOTE**: You can create your own custom user and set it in the __application.properties__ file located in the resources directory (/hotrodspringboot/src/main/resources/).


4. Start the Data Grid instance:

~~~
./bin/server.sh -n dg1 -s server 
~~~

5. Create the "sessions" cache in Data Grid 8 instance:

~~~
[dg1@cluster//containers/default]> create cache --template=org.infinispan.DIST_SYNC sessions
~~~

Created "sessions" cache looks like below (it could be created manually or using the Web console too):
~~~
<?xml version="1.0"?>
<infinispan xmlns="urn:infinispan:config:12.1">
    <cache-container>
        <distributed-cache mode="SYNC" remote-timeout="17500" name="sessions" statistics="true">
            <locking concurrency-level="1000" acquire-timeout="15000" striping="false"/>
            <state-transfer timeout="60000"/>
        </distributed-cache>
    </cache-container>
</infinispan>
~~~

6. Execute the web POST operation to store Person Object in Data Grid Cache:

~~~
curl -X POST --header 'Content-Type: application/json' -d '{ "firstName": "Luis", "lastName": "Diaz", "bornYear": "1997", "bornIn": "Colombia" }' http://localhost:8080/redhat/update-cache/sessions/key1
~~~

~~~
curl -X POST --header 'Content-Type: application/json' -d '{ "firstName": "Sadio", "lastName": "Mané", "bornYear": "1992", "bornIn": "Senegal" }' http://localhost:8080/redhat/update-cache/sessions/key2
~~~

7. Execute the web GET operation to retrieve cache value from Data Grid Cache using the Key:

~~~
curl -X GET http://localhost:8080/redhat/get-cache-value/sessions/key1
~~~

# Changes summary:
- Upgrade to RHDG 8.4 hotrod connector. (Infinispan 14.0.2.Final-redhat-00001)
- Upgrade to Spring Boot starter 2.7.0
- Removed fabric8 maven plugin (Deprecated)
- Adding JKube maven plugin for Openshift deployment.
