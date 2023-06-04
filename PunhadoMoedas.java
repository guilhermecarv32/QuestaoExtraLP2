package pontoExtra;

public class PunhadoMoedas {

	public static final double[] valoresPossiveis = {0.05, 0.10, 0.25, 0.50, 0.75, 1};

	private double valor;

	private int quantidade;

	public PunhadoMoedas(double valor, int quantidade) {

		boolean valorValido = false;

		for (int i = 0; i < valoresPossiveis.length; i++)

			if (valor == valoresPossiveis[i]) {
				valorValido = true; 
				break; 
			}

		if (valorValido) {
			this.valor = valor;
			this.quantidade = quantidade;
		}

		else {
			this.valor = 0;
			this.quantidade = 0;
		}
	}

	public double getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getTotalPunhado() {
		return valor * quantidade;
	}
}