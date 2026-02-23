package br.com.pc.omniflow.domain.enums;

public enum StatusRegra {
    PENDENTE("P"), // Criado automaticamente pelo sistema (Aguardando ação)
    ATIVO("A"),    // Validado pelo usuário (Libera estoque)
    BLOQUEADO("B"); // Ignora o estoque permanentemente para este CFOP

    private String sigla;
    StatusRegra(String sigla) { this.sigla = sigla; }
    public String getSigla() { return sigla; }

    public static StatusRegra fromCodigo(String dbData) {
        if (dbData == null) return PENDENTE;

        for (StatusRegra status : StatusRegra.values()) {
            if (status.getSigla().equals(dbData)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status de Regra desconhecido: " + dbData);
    }
}
