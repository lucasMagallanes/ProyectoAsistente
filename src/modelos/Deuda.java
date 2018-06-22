package modelos;

public class Deuda {
	private int deudaID;
	private int deudorID;
	private int prestamistaID;
	private double importe;
	public int getDeudaID() {
		return deudaID;
	}
	public void setDeudaID(int deudaID) {
		this.deudaID = deudaID;
	}
	public int getDeudorID() {
		return deudorID;
	}
	public void setDeudorID(int deudorID) {
		this.deudorID = deudorID;
	}
	public int getPrestamistaID() {
		return prestamistaID;
	}
	public void setPrestamistaID(int prestamistaID) {
		this.prestamistaID = prestamistaID;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	@Override
	public String toString() {
		return "Deuda [deudaID=" + deudaID + ", deudorID=" + deudorID + ", prestamistaID=" + prestamistaID
				+ ", importe=" + importe + "]";
	}
	
	
	
}
