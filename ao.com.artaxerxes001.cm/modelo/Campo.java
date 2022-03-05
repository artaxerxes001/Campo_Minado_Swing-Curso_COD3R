package ao.com.artaxerxes001.cm.modelo;


import java.util.ArrayList;
import java.util.List;

import static ao.com.artaxerxes001.cm.modelo.CampoEvento.REINICIAR;

public class Campo {
//    cordenadas
private final int linha;
private final int coluna;

//    caracteristicas do campo
private boolean aberto = false;
private boolean minado = false;
private boolean marcado = false;

private List<Campo> vizinhos = new ArrayList<>();
private List<CampoObservador> observadores = new ArrayList<>();

//    contrutor esta com visibilidade do pacote
Campo(int linha, int coluna) {
    this.linha = linha;
    this.coluna = coluna;
}

public void registrarObservador(CampoObservador observador) {
    observadores.add(observador);
}

private void notificarObservadores(CampoEvento evento) {
    observadores.stream()
            .forEach(o -> o.eventoOcorreu(this, evento));
}

boolean adicionarVizinho(Campo vizinho) {
//        para verificar se o vizinho esta na diagonal verificamos se a linha e a coluna são diferentes se forem ele esta na diagonal
    boolean linhaDiferente = linha != vizinho.linha;
    boolean colunaDiferente = coluna != vizinho.coluna;
    boolean diagonal = linhaDiferente && colunaDiferente;

    int deltaLinha = Math.abs(linha - vizinho.linha);
    int deltaColuna = Math.abs(coluna - vizinho.coluna);
    int deltaGeral = deltaColuna + deltaLinha;

    if (deltaGeral == 1 && !diagonal) {
        vizinhos.add(vizinho);
        return true;
    } else if (deltaGeral == 2 && diagonal) {
        vizinhos.add(vizinho);
        return true;
    } else {
        return false;
    }

}

public void alternarMarcacao() {
    if (!aberto) {
        marcado = !marcado;
        if (marcado) {
            notificarObservadores(CampoEvento.MARCAR);
        } else {
            notificarObservadores(CampoEvento.DESMARCAR);

        }
    }
}

public boolean abrir() {
    if (!aberto && !marcado) {
        if (minado) {
            notificarObservadores(CampoEvento.EXPLODIR);
            return true;
// TODO implementar nova versão
        }
        setAberto(true);
        if (vizinhancaSegura()) {
            vizinhos.forEach(v -> v.abrir());
        }
    }
    return false;
}

public boolean vizinhancaSegura() {
    return vizinhos.stream().noneMatch(v -> v.minado);
}

public boolean isMarcado() {
    return marcado;
}

void minar() {
    minado = true;
}

public boolean isAberto() {
    return aberto;
}

void setAberto(boolean aberto) {
    this.aberto = aberto;
    if (aberto) notificarObservadores(CampoEvento.ABRIR);

}

public boolean isFechado() {
    return !aberto;
}

public boolean isMinado() {
    return minado;
}

public int getLinha() {
    return linha;
}

public int getColuna() {
    return coluna;
}

boolean objetivoAlcancado() {
    boolean desvendado = !minado && aberto;
    boolean protegido = minado && marcado;
    return desvendado || protegido;
}

public int minasNaVisinhanca() {
    return (int)vizinhos.stream().filter(v -> v.minado).count();
}

void reiniciar() {
    aberto = false;
    minado = false;
    marcado = false;
    notificarObservadores(CampoEvento.REINICIAR);
}


}
