package br.com.vainaweb.escola.t3.dto;

import br.com.vainaweb.escolat3.enums.Cargo;

public record DadosColaborador(String nome, String cpf, String email, Cargo cargo, EnderecoDTO endereco) {

}
