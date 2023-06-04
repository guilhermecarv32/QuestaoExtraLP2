package pontoExtra;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PunhadoMoedasUtil {	
	
	private static final String caminhoArquivo = "lista-moedas.txt";
	public static void main(String[] args) {
		
		int op = 0;
		Scanner scanner = new Scanner(System.in);
		List<PunhadoMoedas> lista = new ArrayList<PunhadoMoedas>();

	            try {
	                FileReader leitorArquivo = new FileReader(caminhoArquivo);
	                Scanner fluxoLeitura = new Scanner(leitorArquivo);

	                while (fluxoLeitura.hasNextLine()) {

	                    String linha = fluxoLeitura.nextLine();
	                    String[] campos = linha.split("[|]");

	                    if (campos.length == 2) {
	                        double valor = Double.parseDouble(campos[0].replace(',', '.'));
	                        int quantidade = Integer.parseInt(campos[1]);
	                        PunhadoMoedas punhado = new PunhadoMoedas(valor, quantidade);
	                        lista.add(punhado);
	                    }
	                }

	                fluxoLeitura.close();
	                leitorArquivo.close();

	                System.out.println("Dados de punhados de moedas restaurados!");
	            } catch (Exception e) {
	                System.out.println("Erro: " + e.getMessage());
	            } 
		
		do {

			System.out.println("*******************MENU************************");
			System.out.println("*           Selecione uma opcao               *");
			System.out.println("* 1 - Cadastro de novo punhado de moedas      *");
			System.out.println("* 2 - Valor total em moedas                   *");
			System.out.println("* 3 - Valor total da moeda em mais quantidade *");
			System.out.println("* 4 - Encerrar e salvar em arquivo 'txt'      *");
			System.out.println("***********************************************");
			op = scanner.nextInt();

			switch(op) {

			case 1: 
				System.out.println("Valor das moedas do novo punhado: ");
				double valor = scanner.nextDouble();
				System.out.println("Quantidade de moedas desse novo punhado: ");
				int quantidade = scanner.nextInt();

				PunhadoMoedas punhado = new PunhadoMoedas(valor, quantidade);

				lista.add(punhado);
				System.out.println("Punhado de moedas cadastrado!");
				System.out.println();
				break;

			case 2:
				double valorTotal = 0;
				Iterator<PunhadoMoedas> itTotal = lista.iterator();
				
				while(itTotal.hasNext()) {
					PunhadoMoedas p = itTotal.next();
					valorTotal += p.getTotalPunhado();
				}
				
				System.out.println("Valor total em moedas: " + valorTotal);
				System.out.println();
				break;

			case 3: 
				int maisMoedas = 0;
			    double valorMaisMoedas = 0;
			    Iterator<PunhadoMoedas> it = lista.iterator();
			    
			    while (it.hasNext()) {
			        PunhadoMoedas punhadoAux = it.next();
			        if (punhadoAux.getQuantidade() > maisMoedas) {
			            maisMoedas = punhadoAux.getQuantidade();
			            valorMaisMoedas = punhadoAux.getValor();
			        }
			    }
			    
			    System.out.println("Valor da moeda com mais quantidade: " + valorMaisMoedas);
			    System.out.println();
			    break;
				
			}

		} while (op != 4);

		if(lista.size() == 0) {
			System.out.println("Nao ha nenhum punhado a ser registrado!");
		} else {

			try {
				PrintWriter escritorArquivo = new PrintWriter(caminhoArquivo);

				Iterator<PunhadoMoedas> ite = lista.iterator();

				while(ite.hasNext()) {
					PunhadoMoedas p = ite.next();
					escritorArquivo.printf("%f|%d\n", p.getValor(), p.getQuantidade());
				}

				escritorArquivo.close();

				System.out.println("Arquivo Salvo!");

			} catch (IOException e) {
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		scanner.close();
	}

}