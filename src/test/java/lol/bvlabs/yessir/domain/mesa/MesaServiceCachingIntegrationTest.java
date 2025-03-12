package lol.bvlabs.yessir.domain.mesa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lol.bvlabs.yessir.infra.cache.EmbededRedis;
import lol.bvlabs.yessir.infra.cache.RedisConfig;

@Import({ RedisConfig.class, MesaService.class, EmbededRedis.class })	// Importa as classes que vou testar
@ExtendWith(SpringExtension.class)
@EnableCaching										// Habilita o cache para o teste
@ImportAutoConfiguration(classes = {				// Importa as configurações automáticas do Redis e Cache
	CacheAutoConfiguration.class,
	RedisAutoConfiguration.class
})
@ActiveProfiles("test")
class MesaServiceCachingIntegrationTest {

    @MockBean
    private MesaRepository mockMesaRepository;

    private final MesaService mesaService;

    private final CacheManager cacheManager;

    @Autowired
    public MesaServiceCachingIntegrationTest(MesaService mesaService, CacheManager cacheManager) {
        this.mesaService = mesaService;
        this.cacheManager = cacheManager;
    }

    @Test
	@DisplayName("Deve retornar uma Mesa à partir do cache")
    void givenRedisCaching_whenFindMesaById_thenMesaReturnedFromCache() {
		// GIVEN
    	var mesaBuilder = new MesaBuilder()
        		.withId(1L)
        		.withNome("mesa 1");
    	Long idSearched = 1L;

    	var mesaNamespace = "mesas";
        var mesaKey = "mesa:" + idSearched;
        cacheManager.getCache(mesaNamespace).evict(mesaKey);

		var mesa = mesaBuilder.build();
        given(mockMesaRepository.findById(idSearched))
          .willReturn(mesa);

		// WHEN
        var mesaPOJOCacheMiss = mesaService.getOneById(idSearched);
        var mesaPOJOCacheHit = mesaService.getOneById(idSearched);

		// THEN
        var mesaPOJO = mesaBuilder.buildMesaPOJO();
        assertThat(mesaPOJOCacheMiss).isEqualTo(mesaPOJO);
        assertThat(mesaPOJOCacheHit).isEqualTo(mesaPOJO);

        verify(mockMesaRepository, times(1)).findById(idSearched);

        cacheManager.getCache(mesaNamespace).evict(mesaKey);
    }
}