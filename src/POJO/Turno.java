package POJO;
import GENERICS.ClaseBaseBddGenerica;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turno extends ClaseBaseBddGenerica<Integer> {

    private int idCliente;
    private int idPersonal;
    private Cliente cliente;
    private Personal veterinario;
    private LocalDate fechaTurno;
    private LocalTime horaTurno;
    private String motivoConsulta;

    //----CONSTRUCTOR----


    public Turno(int idCliente, int idPersonal, LocalDate fechaTurno, LocalTime horaTurno, String motivoConsulta) {
        this.idCliente = idCliente;
        this.idPersonal = idPersonal;
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.motivoConsulta = motivoConsulta;
    }

    public Turno() {
        this.cliente= new Cliente();
        this.veterinario=new Personal();
    }

    //----GETTER Y SETTER----
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public LocalTime getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(LocalTime horaTurno) {
        this.horaTurno = horaTurno;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Personal getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Personal veterinario) {
        this.veterinario = veterinario;
    }


    @Override
    public String toString() {
        return "\nTurno:" +
                "\n\tidCliente=" + idCliente +
                "\n\tidPersonal=" + idPersonal +
                "\n\tcliente=" + cliente +
                "\n\tveterinario=" + veterinario +
                "\n\tfechaTurno=" + fechaTurno +
                "\n\thoraTurno=" + horaTurno +
                "\n\tmotivoConsulta='" + motivoConsulta;
    }
}
