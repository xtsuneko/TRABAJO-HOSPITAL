package POJO;

import GENERICS.ClaseBaseBddGenerica;

import java.util.Objects;

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

    public Revision(boolean activo, Integer id, int idTurno, String diagnostico, String tratamiento) {
        super(activo, id);
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


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return idTurno == revision.idTurno && Objects.equals(diagnostico, revision.diagnostico) && Objects.equals(tratamiento, revision.tratamiento) && Objects.equals(turno, revision.turno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTurno, diagnostico, tratamiento, turno);
    }

    @Override
    public String toString() {
        return "\nFicha medica:" +
                "\n\tidTurno=" + idTurno +
                "\n\tdiagnostico='" + diagnostico + '\'' +
                "\n\ttratamiento='" + tratamiento;
    }
}
