package br.com.joao.cm.modelo;

import br.com.joao.cm.excecao.ExplosaoException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private final int LINHA;
    private final int COLUNA;

    private boolean aberto = false;
    private boolean minado = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna){
        this.LINHA = linha;
        this.COLUNA = coluna;
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
        }
    }

    public boolean abrir(){

        if (!aberto && !marcado){
            aberto = true;

            if (minado){
                throw new ExplosaoException();
            }

            if (vizinhacaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        } else {
            return false;
        }
    }

    boolean vizinhacaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar(){
        minado = true;
    }
    public boolean isMarcado() {
        return marcado;
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

    long minasNaVizinhanca(){
        return vizinhos.stream().filter(v -> v.minado).count();
    }

    void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
    }

    public String toString() {
        if (marcado){
            return "x";
        } else if (aberto && minado){
            return "*";
        } else if (aberto && minasNaVizinhanca() > 0){
            return Long.toString(minasNaVizinhanca());
        } else if (aberto) {
            return " ";
        } else {
            return "?";
        }
    }
}

