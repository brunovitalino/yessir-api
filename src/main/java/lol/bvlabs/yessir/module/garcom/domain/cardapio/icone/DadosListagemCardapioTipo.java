package lol.bvlabs.yessir.module.garcom.domain.cardapio.icone;

public record DadosListagemCardapioTipo(
		Long id,
		String nome
) {
	
	public DadosListagemCardapioTipo(CardapioTipo cardapioTipo) {
		this(cardapioTipo.getId(), cardapioTipo.getNome());
	}
}
