package lol.bvlabs.yessir.module.garcom.domain.cardapio.tipo;

public record DadosListagemCardapioIcone(
		Long id,
		String nome
) {
	
	public DadosListagemCardapioIcone(CardapioIcone cardapioIcone) {
		this(cardapioIcone.getId(), cardapioIcone.getNome());
	}
}
