package lol.bvlabs.yessir.module.garcom.domain.cardapio;

public record DadosListagemCardapio(
		Long id,
		String nome,
		Boolean ativo
) {
	
	public DadosListagemCardapio(Cardapio cardapio) {
		this(cardapio.getId(), cardapio.getNome(), cardapio.getAtivo());
	}
}
