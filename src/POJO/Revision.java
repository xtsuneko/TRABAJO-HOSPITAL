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
        return Objects.equals(getId(), revision.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        String fecha = " ";
        String medico = " ";
        String cliente = " ";
        if (turno != null) {
            fecha = turno.getFechaTurno() + " " + turno.getHoraTurno();
            if (turno.getMedico() != null && turno.getMedico().getPersona() != null) {
                medico = turno.getMedico().getPersona().getNombre() + " " + turno.getMedico().getPersona().getApellido();
            }
            if (turno.getCliente() != null && turno.getCliente().getPersona() != null) {
                cliente = turno.getCliente().getPersona().getNombre() + " " + turno.getCliente().getPersona().getApellido();
            }
        }
        return "\nFicha medica:" +
                "\n\tID=" + this.getId() +
                "\n\tidTurno=" + idTurno +
                "\n\tfecha=" + fecha +
                "\n\tmedico=" + medico +
                "\n\tcliente=" + cliente +
                "\n\tdiagnostico='" + diagnostico + '\'' +
                "\n\ttratamiento='" + tratamiento;
    }
}
