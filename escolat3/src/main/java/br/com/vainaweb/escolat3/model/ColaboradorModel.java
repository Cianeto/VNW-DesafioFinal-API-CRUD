package br.com.vainaweb.escolat3.model;

import org.springframework.validation.annotation.Validated;

import br.com.vainaweb.escola.t3.dto.EnderecoDTO;
import br.com.vainaweb.escolat3.dto.DadosAtualizados;
import br.com.vainaweb.escolat3.enums.Cargo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_colaboradores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// Chave primária com auto_increment
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String cpf;
	
	@Column(unique = true)
	private String email;
	
	private Cargo cargo;
	
	@Embedded
	private Endereco endereco;
	
// ------------------------------------------CONSTRUTOR--------------------------------
	
	public ColaboradorModel(String nome, String cpf, String email, Cargo cargo, @Valid EnderecoDTO endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.cargo = cargo;
		this.endereco = new Endereco(endereco.cep(), endereco.logradouro(), endereco.bairro(), endereco.cidade(), endereco.uf(), endereco.complemento(), endereco.numero());
	}

	public void atualizarInfo(@Validated DadosAtualizados dados) {
		this.nome = dados.nome() != null ? dados.nome(): this.nome;
		this.email = dados.email() != null ? dados.email(): this.email;
	}
	
}
