package br.com.joao.cm.modelo;

@FunctionalInterface
public interface CampoObservador {

    public void eventoOcorreu(Campo c, CampoEvento evento);
}
