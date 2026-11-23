package POJO;

import GENERICS.ClaseBaseBddGenerica;

public class Revision extends ClaseBaseBddGenerica<Integer> {

    private int idTurno;
    private String diagnostico;
    private String tratamiento;
    private Turno turno;

    public Revision(int idTurno, String diagnostico, String tratamiento) {
        this.idTurno = idTurno;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public Revision() {
        this.turno = new Turno();
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

}
