package lol.bvlabs.yessir.infra.cache;

import java.time.Duration;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisConfig {

	@Bean
	public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(RedisConnectionFactory redisConnectionFactory) {
        return (builder) -> builder
    		.cacheDefaults(getDefaultRedisCacheConfiguration())
			.withCacheConfiguration("mesas", getMesasRedisCacheConfiguration());
	}

	private RedisCacheConfiguration getDefaultRedisCacheConfiguration() {
	    return RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(60))																	// Configuração global de TTL
			.disableCachingNullValues()
			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));	// Serialização global
	}

	private RedisCacheConfiguration getMesasRedisCacheConfiguration() {
	    return RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(10)) 																	// Configuração personalizado de TTL para o cache "mesas"
			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));	// Serialização personalizada para "mesas"
	}
}
