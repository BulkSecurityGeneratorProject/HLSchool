package com.hl.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.hl.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.hl.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.hl.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.hl.domain.Config.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Vocabulary.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Post.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Post.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.hl.domain.Comment.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Room.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Course.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Lesson.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Feedback.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Question.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Answer.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.UserLog.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.GiftLog.class.getName(), jcacheConfiguration);
            cm.createCache(com.hl.domain.Gift.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
