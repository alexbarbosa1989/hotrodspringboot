package com.redhat.hotrod.hotrodspringboot.controller;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryCreated;
import org.infinispan.client.hotrod.annotation.ClientCacheEntryModified;
import org.infinispan.client.hotrod.annotation.ClientListener;
import org.infinispan.client.hotrod.event.ClientCacheEntryCreatedEvent;
import org.infinispan.client.hotrod.event.ClientCacheEntryModifiedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	private final RemoteCacheManager cacheMgrRemote;
	
	@Autowired
	public Controller(RemoteCacheManager cacheManager) {
	    this.cacheMgrRemote = cacheManager;
	}
	
	@GetMapping(value = "/update-cache/{cacheName}/{cacheKey}/{cacheValue}", produces = {
			"application/json; charset=UTF-8" })
	public String updateCache(@PathVariable String cacheName, @PathVariable String cacheKey,
			@PathVariable String cacheValue) {
		//Get the cache
		RemoteCache<String, String> cache = cacheMgrRemote.getCache(cacheName);

		// Register a listener
		MyListener listener = new MyListener();
		cache.addClientListener(listener);

		//Put data and return value
		cache.put(cacheKey, cacheValue);
		String returnedValue = cache.get(cacheKey).toString();
		return "SUCCESS "+returnedValue;
	}
	
	@GetMapping(value = "/get-cache-value/{cacheName}/{cacheKey}", produces = {
			"application/json; charset=UTF-8" })
	public String getValueCache(@PathVariable String cacheName, @PathVariable String cacheKey) {
		RemoteCache<String, String> cache = cacheMgrRemote.getCache(cacheName);
		String returnedValue = cache.get(cacheKey).toString();
		return "The value for key: " + cacheKey + " is: " + returnedValue;
	}

	@ClientListener
   public static class MyListener {

      @ClientCacheEntryCreated
      public void entryCreated(ClientCacheEntryCreatedEvent<String> event) {
         System.out.printf("Created %s%n", event.getKey());
      }

      @ClientCacheEntryModified
      public void entryModified(ClientCacheEntryModifiedEvent<String> event) {
         System.out.printf("About to modify %s%n", event.getKey());
      }

   }
}
