# hotrodspringboot

1. Clone the project:
~~~
git clone https://github.com/alexbarbosa1989/hotrodspringboot
~~~

2. Execute it:

~~~
mvn clean install
~~~
~~~
java -jar target/hotrodspringboot-0.0.1-SNAPSHOT.jar
~~~

3. Create the cache in Data Grid 8:

~~~
<infinispan>
    <cache-container>
        <distributed-cache owners="2" mode="SYNC" remote-timeout="30000" name="sessions" statistics="true">
            <transaction mode="NONE"/>
            <expiration lifespan="-1"/>
        </distributed-cache>
    </cache-container></infinispan>
~~~

4. Add user in Data Grid instance:

~~~
User: jdgUser
Password: jdgUs3R
~~~

5. Start the Data Grid instance:

~~~
./bin/server.sh -n node1 
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
