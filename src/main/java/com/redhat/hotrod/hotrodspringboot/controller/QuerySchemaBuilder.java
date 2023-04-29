package com.redhat.hotrod.hotrodspringboot.controller;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
	      includeClasses = {
	            Person.class
	      },
	      schemaPackageName = "person")
public interface QuerySchemaBuilder extends GeneratedSchema {    
}
