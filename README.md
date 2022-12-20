# hotrodspringboot

# Deployment steps:

Most of the steps remain as-is in the Red Hat Developer blog: https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid

**In order to deploy current branch project. There should be make below described Steps Chages**
1. In step [How to deploy the Spring Boot project](https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid#how_to_deploy_the_spring_boot_project):
Instead of clone the **openshift** brach, must clone **RHDG_8.4** branch:

**Old step:**
~~~
git clone -b openshift https://github.com/alexbarbosa1989/hotrodspringboot
~~~
**New step:**
~~~
git clone -b RHDG_8.4 https://github.com/alexbarbosa1989/hotrodspringboot
~~~

2. In step [Deploy the Spring Boot application
](https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid#deploy_the_spring_boot_application):
Instead of using **fabric8** maven comand, should use **JKube** maven commands:

**Old step:**
~~~
mvn clean fabric8:deploy -Popenshift
~~~
**New step:**
~~~
mvn clean package oc:build -Popenshift
mvn oc:apply -Popenshift
~~~

The other steps remains exactly the same as in the [Red Hat Developer blog](https://developers.redhat.com/articles/2022/05/31/integrate-spring-boot-application-red-hat-data-grid)

# Changes summary:
- Upgrade to RHDG 8.4 hotrod connector. (Infinispan 14.0.2.Final-redhat-00001)
- Upgrade to Spring Boot starter 2.7.0
- Removed fabric8 maven plugin (Deprecated)
- Adding JKube maven plugin for Openshift deployment.
