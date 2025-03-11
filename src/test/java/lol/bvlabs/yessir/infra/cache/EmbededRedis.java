package lol.bvlabs.yessir.infra.cache;

import java.io.IOException;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.TestConfiguration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import redis.embedded.RedisServer;

@TestConfiguration
public class EmbededRedis {

    private RedisServer redisServer;

    public EmbededRedis(RedisProperties redisProperties) throws IOException {
        this.redisServer = new RedisServer(redisProperties.getPort());
    }

    @PostConstruct
    public void startRedis() throws IOException {
        this.redisServer.start();
    }

    @PreDestroy
    public void stopRedis() throws IOException {
        this.redisServer.stop();
    }
}
