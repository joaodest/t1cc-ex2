package model;

public class Crypto {
	private int id ;
	private String rede;
	private String symbol;
	private double price;
	
	public Crypto() {
		
	}
	
	public Crypto(int id, String rede, String symbol, double price) {
		this.id = id;
		this.rede = rede;
		this.symbol = symbol;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getRede() {
		return rede;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}
	
	@Override
	public String toString() {
		return "Crypto [id= " + id + ", rede= " + rede + ", symbol= " + symbol + ", price= $" + price + " ]";
	}
}
