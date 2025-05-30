package org.example.api.Entities.dtos;

public class DistribucionNotasDTO {
    private double notaMedia;
    private int[] conteos; // índice 0: [0-1), índice 1: [1-2), ..., índice 9: [9-10]

    public DistribucionNotasDTO(double notaMedia, int[] conteos) {
        this.notaMedia = notaMedia;
        this.conteos = conteos;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public int[] getConteos() {
        return conteos;
    }

    public void setConteos(int[] conteos) {
        this.conteos = conteos;
    }
}
