package br.com.fiap.dragons.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dragon {

    private Integer id;
    private String nome;
    private String cor;
    private Integer poderDeFogo;
    private Long peso;
    private Long altura;
    private Boolean possuiMontador;

    public Dragon(String nome, String cor, Integer poderDeFogo, Long peso, Long altura, Boolean possuiMontador) {
        this.nome = nome;
        this.cor = cor;
        this.poderDeFogo = poderDeFogo;
        this.peso = peso;
        this.altura = altura;
        this.possuiMontador = possuiMontador;
    }
}
