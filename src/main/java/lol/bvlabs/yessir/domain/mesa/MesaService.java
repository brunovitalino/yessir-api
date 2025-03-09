package lol.bvlabs.yessir.domain.mesa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MesaService {

	@Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Cacheable(value = "itemCache")
    public DadosListagemMesa getMesaForId(Long id) {
        var mesa = mesaRepository.findById(id).orElseThrow(RuntimeException::new);
        return new DadosListagemMesa(mesa);
    }

    @Cacheable(value = "mesas", key = "#id")
    public Optional<DadosListagemMesa> getOneById(Long id) {
        System.err.println("Buscando mesa no banco de dados...");

        Optional<Mesa> mesa = mesaRepository.findById(id);
        if (mesa.isPresent()) {
            DadosListagemMesa dados = new DadosListagemMesa(mesa.get());
            //try {
                // Convertendo o objeto para JSON antes de armazenar no Redis
                //String json = objectMapper.writeValueAsString(dados);
                //redisTemplate.opsForValue().set("mesa:" + id, json); // Armazenando no Redis como String
            //} catch (JsonProcessingException e) {
              //  e.printStackTrace();
            //}
            return Optional.of(dados);
        }
        return Optional.empty();
        //return mesa.map(MesaOne::new);
    }
}
