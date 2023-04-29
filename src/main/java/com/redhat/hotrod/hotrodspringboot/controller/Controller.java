package com.redhat.hotrod.hotrodspringboot.controller;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.protostream.GeneratedSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.infinispan.query.remote.client.ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME;

@RestController
public class Controller {

	private final RemoteCacheManager cacheMgrRemote;

	@Autowired
	public Controller(RemoteCacheManager cacheManager) {
		this.cacheMgrRemote = cacheManager;

		// Retrieve metadata cache
		RemoteCache<String, String> metadataCache = cacheManager.getCache(PROTOBUF_METADATA_CACHE_NAME);
		// Define the new schema on the server too
		GeneratedSchema schema = new QuerySchemaBuilderImpl();
		metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());
	}

	@PostMapping(value = "/update-cache/{cacheName}/{cacheKey}", produces = {
			"application/json; charset=UTF-8" })
	public String updateCache(@PathVariable String cacheName, @PathVariable String cacheKey,
			@RequestBody Person personJSON) {
		cacheMgrRemote.getCache(cacheName).put(cacheKey, personJSON);
		RemoteCache<String, Person> cache = cacheMgrRemote.getCache(cacheName);
		String returnedValue = cache.get(cacheKey).toString();
		return "SUCCESS " + returnedValue;
	}

	@GetMapping(value = "/get-cache-value/{cacheName}/{cacheKey}", produces = {
			"application/json; charset=UTF-8" })
	public String getValueCache(@PathVariable String cacheName, @PathVariable String cacheKey) {
		RemoteCache<String, Person> cache = cacheMgrRemote.getCache(cacheName);
		Person returnedValue = cache.get(cacheKey);
		return "The value for key: " + cacheKey + " is: " + returnedValue;
	}

}
