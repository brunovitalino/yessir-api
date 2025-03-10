package lol.bvlabs.yessir.domain.mesa;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;
    
    public MesaService(MesaRepository mesaRepository) {
    	this.mesaRepository = mesaRepository;
    }

    @Cacheable(value = "mesas", key = "'mesa:' + #id")
    public Optional<MesaPOJO> getOneById(Long id) {
        return mesaRepository.findById(id).map(MesaPOJO::new);
    }
}
