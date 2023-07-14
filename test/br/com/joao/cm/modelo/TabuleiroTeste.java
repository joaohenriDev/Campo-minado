package test.br.com.joao.cm.modelo;

import br.com.joao.cm.modelo.Campo;
import br.com.joao.cm.modelo.Tabuleiro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroTeste {

    private Tabuleiro tabuleiro;

    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro(3, 3, 3);
    }

    @Test
    public void testGerarCampos() {
        assertEquals(9, tabuleiro.getCampos().size());
    }

    @Test
    public void testAssociarVizinhos() {
        Tabuleiro tabuleiro = new Tabuleiro(3, 3, 0);

        tabuleiro.getCampos().forEach(campo -> {
            int linha = campo.getLINHA();
            int coluna = campo.getCOLUNA();

            // Verifica se cada campo possui os vizinhos corretos
            tabuleiro.getCampos().forEach(vizinho -> {
                int linhaVizinho = vizinho.getLINHA();
                int colunaVizinho = vizinho.getCOLUNA();

                boolean vizinhoEsperado = (Math.abs(linha - linhaVizinho) <= 1) &&
                        (Math.abs(coluna - colunaVizinho) <= 1) &&
                        (linha != linhaVizinho || coluna != colunaVizinho);

                if (vizinhoEsperado) {
                    assertTrue(campo.adicionarVizinho(vizinho));
                } else {
                    assertFalse(campo.adicionarVizinho(vizinho));
                }
            });
        });
    }

    @Test
    public void testSortearMinas() throws Exception {
        int linhas = 3;
        int colunas = 3;
        int minas = 3;

        Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, minas);

        int minasSorteadas = 0;
        for (Campo campo : tabuleiro.getCampos()) {
            if (campo.isMinado()) {
                minasSorteadas++;
            }
        }

        assertEquals(minas, minasSorteadas);
    }


    @Test
    public void testObjetivoAlcancado() {

        // Marca todos os campos não minados
        tabuleiro.getCampos().stream()
                .filter(Campo::isMinado)
                .forEach(Campo::alternarMarcacao);

        assertFalse(tabuleiro.objetivoAlcancado());
    }

    @Test
    public void testObjetivoFalha() {

        // Marca apenas um campo não minado
        tabuleiro.getCampos().stream()
                .filter(campo -> !campo.isMinado())
                .findFirst()
                .ifPresent(Campo::alternarMarcacao);

        assertFalse(tabuleiro.objetivoAlcancado(), "O objetivo não falhou corretamente.");
    }

    @Test
    public void testReiniciar() {
        // Implemente testes para verificar se o método reiniciar() reinicia corretamente o estado do tabuleiro
    }

    @Test
    public void testToStringTabuleiro(){
        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);

        tabuleiro.abrir(1,1);
        tabuleiro.alternarMarcacao(4,4);
        tabuleiro.alternarMarcacao(4,5);

        System.out.println(tabuleiro);
    }
}

