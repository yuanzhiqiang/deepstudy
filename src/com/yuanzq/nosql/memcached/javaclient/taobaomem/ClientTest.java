package com.yuanzq.nosql.memcached.javaclient.taobaomem;


import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class ClientTest {   
       
    @SuppressWarnings("unchecked")   
    public static void main(String[] args) {   
        ICacheManager<IMemcachedCache> manager;   
        manager = CacheUtil.getCacheManager(IMemcachedCache.class,   
                MemcachedCacheManager.class.getName());   
        manager.setConfigFile("memcached.xml");   
        manager.start();   
        try {   
            IMemcachedCache cache = manager.getCache("mclient_0");   
            cache.put("key", "value");   
            System.out.println(cache.get("key")+"....");   
        } finally {   
            manager.stop();   
        }   
    }   
  
}  
