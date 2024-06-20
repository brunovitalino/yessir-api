package lol.bvlabs.yessir.module.garcom.domain.estabelecimento;

public record DadosListagemEstabelecimento(
		String nome
) {
	
	public DadosListagemEstabelecimento(Estabelecimento estabelecimento) {
		this(estabelecimento.getNome());
	}
}
