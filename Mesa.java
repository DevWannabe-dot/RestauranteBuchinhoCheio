public class Mesa {
    private int numeroMesa;
    private String data;
    private boolean reserva;

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    public int getNumeroMesa() {
        return numeroMesa;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }
    public boolean isReserva() {
        return reserva;
    }
}
