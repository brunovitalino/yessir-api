package lol.bvlabs.yessir.domain.mesa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MesaService {

	@Autowired
    private MesaRepository mesaRepository;

    @Cacheable(value = "mesas", key = "'mesa:' + #id")
    public Optional<DadosListagemMesa> getOneById(Long id) {
        System.err.println("Buscando mesa no banco de dados...");
        Optional<Mesa> mesa = mesaRepository.findById(id);
        return mesa.map(DadosListagemMesa::new);
    }
}
