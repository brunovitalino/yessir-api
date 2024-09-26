package lol.bvlabs.yessir.module.garcom.domain.atendente;

public record DadosListagemAtendente(
		Long id,
		String nome,
		Boolean ativo
) {
	
	public DadosListagemAtendente(Atendente atendente) {
		this(atendente.getId(), atendente.getNome(), atendente.getAtivo());
	}
}
