package br.com.joao.cm.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Campo {

    private final int LINHA;
    private final int COLUNA;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();
    private List<CampoObservador> observadores = new ArrayList<>();

    public Campo(int linha, int coluna){
        this.LINHA = linha;
        this.COLUNA = coluna;
    }

    public void registrarObservador(CampoObservador observador){
        observadores.add(observador);
    }

    private void notificarObservadores(CampoEvento evento) {
        observadores.stream()
                .forEach(o -> o.eventoOcorreu(this, evento));
    }

     public boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = LINHA != vizinho.LINHA;
        boolean colunaDiferente = COLUNA != vizinho.COLUNA;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
        int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
        int deltaGeral = deltaColuna + deltaLinha;

        if (deltaGeral == 1 && !diagonal){
            vizinhos.add(vizinho);
            return true;
        } else if (deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }else {
            return false;
        }
    }

    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;

            if (marcado) {
                notificarObservadores(CampoEvento.MARCAR);
            } else {
                notificarObservadores(CampoEvento.DESMACAR);
            }
        }
    }

    public boolean abrir(){

        if (!aberto && !marcado){
            aberto = true;

            if (minado){
                notificarObservadores(CampoEvento.EXPLODIR);
                return true;
            }

            setAberto(true);


            if (vizinhacaSegura()){
                vizinhos.forEach(Campo::abrir);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean vizinhacaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar(){
        minado = true;
    }
    public boolean isMinado(){
        return minado;
    }
    public boolean isMarcado() {
        return marcado;
    }

    void setAberto(boolean aberto) {
        this.aberto = aberto;

        if (aberto) {
            notificarObservadores(CampoEvento.ABRIR);
        }
    }

    public boolean isAberto(){
        return aberto;
    }

    public boolean isFechado(){
        return !isAberto();
    }

    public int getLINHA() {
        return LINHA;
    }

    public int getCOLUNA() {
        return COLUNA;
    }

    boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    public int minasNaVizinhanca(){
        return (int) vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
        notificarObservadores(CampoEvento.REINICIAR);
    }
}

