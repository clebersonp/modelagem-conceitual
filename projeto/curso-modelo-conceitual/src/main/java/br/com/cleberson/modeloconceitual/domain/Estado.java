package br.com.cleberson.modeloconceitual.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cleberson.modeloconceitual.domain.enumarations.EnumEstadoSigla;

@Entity
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(value = EnumType.STRING)
	private EnumEstadoSigla sigla;
	
	@JsonIgnore
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();
	
	public Estado() {
	}

	public Estado(Long id, String nome, EnumEstadoSigla sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumEstadoSigla getSigla() {
		return sigla;
	}
	public void setSigla(EnumEstadoSigla sigla) {
		this.sigla = sigla;
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public void add(Cidade cidade) {
		this.cidades.add(cidade);
	}

	@Override
	public String toString() {
		return "Estado [getId()=" + getId() + ", getNome()=" + getNome() + ", getSigla()=" + getSigla()
				+ ", getCidades()=" + getCidades() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}