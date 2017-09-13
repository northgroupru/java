package ru.proitr.example.configuration.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Gainutdinov on 12.09.17.
 */
@EnableCaching
@Configuration
public class CacheConfiguration
{
	@Bean
	public CacheManager cacheManager()
	{
		return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean()
	{
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setCacheManagerName("cacheManager");
		cmfb.setShared(true);

		return cmfb;
	}
}