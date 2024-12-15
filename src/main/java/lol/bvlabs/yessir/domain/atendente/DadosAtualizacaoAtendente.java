package lol.bvlabs.yessir.domain.atendente;

public record DadosAtualizacaoAtendente(
	Long id,
	String nome
) {
	
	public DadosAtualizacaoAtendente(Atendente atendente) {
		this(atendente.getId(), atendente.getNome());
	}

}
