# hotrodspringboot

1. Create the "sessions" cache in Data Grid 8 instance:

~~~
create cache --template=org.infinispan.DIST_SYNC sessions
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

2. Clone the project openshift branch:
~~~
git clone -b openshift https://github.com/alexbarbosa1989/hotrodspringboot
~~~

3. Create a new project in the openshift cluster:
~~~
oc new-project springboot-test
~~~

4. Deploy the springboot project using the fabric8 plugin
~~~
mvn clean fabric8:deploy -Popenshift
~~~

5. Verify the created pod once the deployment process finish:
~~~
oc get pods
NAME                           READY     STATUS      RESTARTS   AGE
hotrodspringboot-1-deploy      0/1       Completed   0          42s
hotrodspringboot-1-plkf7       1/1       Running     0          39s
hotrodspringboot-s2i-1-build   0/1       Completed   0          92s
~~~

6. Check the created route
~~~
 oc get routes
NAME               HOST/PORT                                           PATH      SERVICES           PORT      TERMINATION   WILDCARD
hotrodspringboot   hotrodspringboot-springboot-test.apps-crc.testing             hotrodspringboot   8080                    None
~~~

7. Execute the web GET operation to store values in Data Grid Cache:

~~~
 curl -X GET http://hotrodspringboot-springboot-test.apps-crc.testing/redhat/update-cache/sessions/cacheKey1/cacheValue1
~~~

~~~
 curl -X GET http://hotrodspringboot-springboot-test.apps-crc.testing/redhat/update-cache/sessions/cacheKey2/cacheValue2
~~~

8. Execute the web GET operation to retrieve cache value from Data Grid Cache using the Key:

~~~
curl -X GET http://hotrodspringboot-springboot-test.apps-crc.testing/redhat/get-cache-value/sessions/cacheKey1
~~~

