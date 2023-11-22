# hotrodspringboot

1. Clone the project:
~~~
git clone -b main https://github.com/alexbarbosa1989/hotrodspringboot
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

6. Execute the web GET operation to store values in Data Grid Cache:

~~~
 curl -X GET http://localhost:8080/redhat/update-cache/sessions/cacheKey1/cacheValue1
~~~

~~~
 curl -X GET http://localhost:8080/redhat/update-cache/sessions/cacheKey2/cacheValue2
~~~

7. Execute the web GET operation to retrieve cache value from Data Grid Cache using the Key:

~~~
curl -X GET http://localhost:8080/redhat/get-cache-value/sessions/cacheKey1

~~~
