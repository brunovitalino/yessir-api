package lol.bvlabs.yessir.domain.mesa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import({ MesaService.class})
@ExtendWith(SpringExtension.class)
@EnableCaching
@ImportAutoConfiguration(classes = { 
  CacheAutoConfiguration.class, 
  RedisAutoConfiguration.class 
})
@ActiveProfiles("test")
class MesaServiceCachingIntegrationTest {

    @MockBean
    private MesaRepository mockMesaRepository;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenFindMesaById_thenMesaReturnedFromCache() {
    	var id = 1L;
    	var nome = "mesa 1";
        var mesa = new Mesa(id, nome);
        var mesaOptional = new DadosListagemMesa(mesa);
        given(mockMesaRepository.findById(id))
          .willReturn(Optional.of(mesa));

        var itemCacheMiss = mesaService.getMesaForId(id);
        var itemCacheHit = mesaService.getMesaForId(id);

        assertThat(itemCacheMiss).isEqualTo(mesaOptional);
        assertThat(itemCacheHit).isEqualTo(mesaOptional);

        verify(mockMesaRepository, times(1)).findById(id);
        //assertThat(itemFromCache()).isEqualTo(anItem);
    }
}