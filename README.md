# hotrodspringboot

1. Clone the project:
~~~
git clone https://github.com/alexbarbosa1989/hotrodspringboot
~~~

2. Execute it:

~~~
./mvnw spring-boot:run
~~~

3. Create the cache in Data Grid 8

~~~
<infinispan>
    <cache-container>
        <distributed-cache owners="2" mode="SYNC" remote-timeout="30000" name="sessions" statistics="true">
            <transaction mode="NONE"/>
            <expiration lifespan="-1"/>
        </distributed-cache>
    </cache-container></infinispan>
~~~

4. Add user in Data Grid instance.

~~~
User: jdgUser
Password: jdgUs3R
~~~

5. Start the Data Grid instance:

~~~
./bin/server.sh -n node1 
~~~

6. Execute the springboot GET operation:

~~~
 curl -X GET http://localhost:8080/redhat/update-cache/sessions/cacheKey1/cacheValue1
~~~
