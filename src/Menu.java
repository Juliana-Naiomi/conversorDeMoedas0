import java.util.Scanner;

public class Menu {
    private final ParesDeMoedas[] paresDeMoedas = {
            new ParesDeMoedas("Real Brasileiro BRL", "Dólar Americano USD"),
            new ParesDeMoedas("Real Brasileiro BRL", "Boliviano Boliviano BOB"),
            new ParesDeMoedas("Dólar Americano USD", "Peso Argentino ARS"),
            new ParesDeMoedas("Peso Argentino ARS", "Peso Chileno CLP"),
            new ParesDeMoedas("Real Brasileiro BRL", "Peso Colombiano COP"),
            new ParesDeMoedas("Peso Argentino ARS", "Real Brasileiro BRL")
    };

    public void displayMenu(){
        Scanner opcao = new Scanner(System.in);
        int escolha = 0;
        ConsultaMoeda conversor = new ConsultaMoeda();

        String menu = """
        *********************************************************
                 Seja bem-vindo(a) ao Conversor de Moeda
        *********************************************************
                    
        Escolha uma opção válida:
        """;

        while (escolha != paresDeMoedas.length + 1){
            try {
                System.out.println(menu);
                for (int i = 0; i < paresDeMoedas.length; i++) {
                    System.out.println((i + 1) + ". " +
                            paresDeMoedas[i].fromMoeda() + " para >>>> " + paresDeMoedas[i].toMoeda());
                }
                System.out.println(paresDeMoedas.length + 1 + ". SAIR");
                System.out.println("\nOpção: ");

                escolha = opcao.nextInt();
                double valor;

                switch (escolha){
                    case 1:
                        System.out.println("Digite o valor");
                        valor = opcao.nextDouble();
                        Conversao usdBrl = conversor.buscaConversor("BRL", "USD", valor);
                        System.out.println("\nOpção 1:  Real Brasileiro para Dólar Americano");
                        System.out.println("Conversão: " + valor
                                + " [BRL] = " + usdBrl.conversion_result() + " [USD]");
                        System.out.println(" ");
                        break;
                    case 2:
                        System.out.println("Digite o valor");
                        valor = opcao.nextDouble();
                        Conversao brlBob = conversor.buscaConversor("BRL", "BOB", valor);
                        System.out.println("\nOpção 2. Real Brasileiro para Boliviano Boliviano");
                        System.out.println("Conversão: " + valor
                                + " BRL = " + brlBob.conversion_result() + " BOB");
                        System.out.println(" ");
                        break;
                    case 3:
                        System.out.println("Digite o valor");
                        valor = opcao.nextDouble();
                        Conversao usdArs = conversor.buscaConversor("USD", "ARS", valor);
                        System.out.println("\nOpção 3. Dólar Americano para Peso Argentino");
                        System.out.println("Conversão: " + valor
                                + " USD = " + usdArs.conversion_result() + " ARS");
                        System.out.println(" ");
                        break;
                    case 4:
                        System.out.println("Digite o valor");
                        valor = opcao.nextDouble();
                        Conversao arsClp = conversor.buscaConversor("ARS", "CLP", valor);
                        System.out.println("\nOpção 4. Peso Argentino para Peso Chileno");
                        System.out.println("Conversão: " + valor
                                + " ARS = " + arsClp.conversion_result() + " CLP");
                        System.out.println(" ");
                        break;
                    case 5:
                        System.out.println("Digite o valor");
                        valor = opcao.nextDouble();
                        Conversao brlCop = conversor.buscaConversor("BRL", "COP", valor);
                        System.out.println("\nOpção 5. Real Brasileiro para Peso Colombiano");
                        System.out.println("Conversão: " + valor
                                + " BRL = " + brlCop.conversion_result() + " COP");
                        System.out.println(" ");
                        break;
                    case 6:
                        System.out.println("Digite o valor");
                        valor = opcao.nextDouble();
                        Conversao arsBrl = conversor.buscaConversor("ARS", "BRL", valor);
                        System.out.println("\nOpção 6. Peso Argentino para Real Brasileiro");
                        System.out.println("Conversão: " + valor
                                + " ARS = " + arsBrl.conversion_result() + " BRL");
                        System.out.println(" ");
                        break;
                }

                if (escolha > paresDeMoedas.length + 1){
                    System.out.println("Opção inválida.");
                    System.out.println("Digite uma das opções do menu!");
                }

            } catch (Exception e) {
                System.out.println("Conversão não realizada, verifique se digitou o valor correto");
                break;
            }
        }
        System.out.println("Aplicação Finalizada.");
    }
}
