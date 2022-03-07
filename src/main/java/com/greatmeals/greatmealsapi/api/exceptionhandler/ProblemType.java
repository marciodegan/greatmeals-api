package com.greatmeals.greatmealsapi.api.exceptionhandler;

public enum ProblemType {
    ARGUMENTO_INVÁLIDO("/argumento-invalido", "Argumento inválido"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incomprensível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violacao de regra de negocio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.title = title;
        this.uri = "https://greatmeals.com.br" + path;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
