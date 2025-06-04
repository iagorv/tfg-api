package org.example.api.Entities.dtos;

public class JuegoUsuarioEstadoDTO {
    private Long usuarioId;
    private Long juegoId;
    private String estado;

    public JuegoUsuarioEstadoDTO(Long usuarioId, Long juegoId, String estado) {
        this.usuarioId = usuarioId;
        this.juegoId = juegoId;
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Long juegoId) {
        this.juegoId = juegoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

