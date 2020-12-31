package com.redhat.hotrod.hotrodspringboot.config;

import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.spring.starter.remote.InfinispanRemoteConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class InfinispanConfigurer {

//	@Bean
//	public InfinispanRemoteConfigurer infinispanRemoteConfigurer() {
//		return () -> new ConfigurationBuilder().addServers("127.0.0.1:11222").security().authentication()
//				.saslMechanism("SCRAM-SHA-512").username("jdgUser").password("jdgUs3R")
//				.marshaller("org.infinispan.commons.marshall.JavaSerializationMarshaller")
//				.addJavaSerialWhiteList("org.", "com.", "java.").build();
//
//	}
	
	

}
