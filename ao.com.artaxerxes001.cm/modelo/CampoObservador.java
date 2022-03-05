package ao.com.artaxerxes001.cm.modelo;

@FunctionalInterface
public interface CampoObservador {
    public void eventoOcorreu(Campo campo, CampoEvento evento);
}
