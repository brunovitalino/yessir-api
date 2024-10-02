package lol.bvlabs.yessir.module.garcom.domain.atendente;

public record DadosListagemAtendente(
		Long id,
		String nome
) {
	
	public DadosListagemAtendente(Atendente atendente) {
		this(atendente.getId(), atendente.getNome());
	}
}
