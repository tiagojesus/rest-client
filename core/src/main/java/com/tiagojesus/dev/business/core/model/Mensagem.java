package com.tiagojesus.dev.business.core.model;

public class Mensagem {
    private final String codigo;
    private final String descricao;

    public Mensagem(String codigo, String descricao) {

        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
