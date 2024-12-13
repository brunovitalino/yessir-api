package lol.bvlabs.yessir.domain.estabelecimento;

public record DadosListagemEstabelecimento(
		Long id,
		String nome,
		Boolean ativo
) {
	
	public DadosListagemEstabelecimento(Estabelecimento estabelecimento) {
		this(estabelecimento.getId(), estabelecimento.getNome(), estabelecimento.getAtivo());
	}
}
