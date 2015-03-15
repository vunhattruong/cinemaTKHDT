package model;

public class Seat {
	private int idSeat;
	private String typeSeat;
	private double priceSeat;
	
	public Seat() {
		
	}
	
	public Seat(int idSeat, String typeSeat, double priceSeat) {
		this.idSeat = idSeat;
		this.typeSeat = typeSeat;
		this.priceSeat = priceSeat;
	}

	public int getIdSeat() {
		return idSeat;
	}

	public void setIdSeat(int idSeat) {
		this.idSeat = idSeat;
	}

	public String getTypeSeat() {
		return typeSeat;
	}

	public void setTypeSeat(String typeSeat) {
		this.typeSeat = typeSeat;
	}

	public double getPriceSeat() {
		return priceSeat;
	}

	public void setPriceSeat(double priceSeat) {
		this.priceSeat = priceSeat;
	}
	
	
}
