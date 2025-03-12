package lol.bvlabs.yessir.domain.mesa;

import java.util.Optional;

public class MesaBuilder {
	Long id;
	String nome;

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

	public MesaBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public MesaBuilder withNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public Optional<Mesa> build() {
		return Optional.of(new Mesa(this.id, this.nome));
	}
	
	public Optional<MesaPOJO> buildMesaPOJO() {
		return Optional.of(new MesaPOJO(new Mesa(this.id, this.nome)));
	}
}
