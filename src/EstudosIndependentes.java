import java.util.Random;

public class EstudosIndependentes {

    public static void main(String[] args) throws Exception {

        Arquivo arquivo = new Arquivo("resultados.csv");
        Calculadora calculadora = new Calculadora();
        Menu menu = new Menu();

        double[] valores;
        double resultado;

        char opcao;
        char opcao2;

        do {
            opcao = menu.mostraMenu(menu.menuPrincipal);
            switch (opcao) {

                case '1':
                    do {
                        opcao2 = menu.mostraMenu(menu.menuValores);
                        valores = coletaValores(opcao2);
                        if (valores != null) break;

                    } while (opcao2 != '0');
                    if (valores == null) break;

                    resultado = calculadora.media(valores);
                    System.out.println("A média dos valores é: " + resultado);
                    calculadora.salvarResultado(resultado, arquivo);
                    break;

                case '2':
                    do {
                        opcao2 = menu.mostraMenu(menu.menuValores);
                        valores = coletaValores(opcao2);
                        if (valores != null) break;

                    } while (opcao2 != 0);
                    if (valores == null) break;

                    resultado = calculadora.mediana(valores);
                    System.out.println("A mediana dos valores é: " + resultado);
                    calculadora.salvarResultado(resultado, arquivo);
                    break;

                case '3':
                    do {
                        opcao2 = menu.mostraMenu(menu.menuValores);
                        valores = coletaValores(opcao2);
                        if (valores != null) break;

                    } while (opcao2 != 0);
                    if (valores == null) break;
            
                    resultado = calculadora.desvioPadrao(valores);
                    System.out.println("O desvio padrão dos valores é: " + resultado);
                    calculadora.salvarResultado(resultado, arquivo);
                    break;

                case '0':
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != '0');
    }

    public static double[] coletaValores(char opcao) {

        double[] valores;
        int tamanhoVetor;

        switch (opcao) {
            case '1':
                tamanhoVetor = Entrada.leiaInt("Quantos valores você deseja inserir?: ");
                valores = new double[tamanhoVetor];
                for (int i = 0; i < tamanhoVetor; i++) {
                    valores[i] = Entrada.leiaDouble("Digite o valor " + (i + 1) + ": ");
                }
                return valores;
            case '2':
                tamanhoVetor = Entrada.leiaInt("Quantos valores randômicos deseja criar?: ");
                valores = new double[tamanhoVetor];
                int tamMin = Entrada.leiaInt("Qual o valor mínimo para os números randômicos?: ");
                int tamMax = Entrada.leiaInt("Qual o valor máximo para os números randômicos?: ");

                if (tamMax <= tamMin) {
                    System.out.println("Valor máximo precisa ser maior do que o valor mínimo.");
                    break;
                }

                Random gerador = new Random();
                for (int i = 0; i < tamanhoVetor; i++) {
                    valores[i] = gerador.nextInt((tamMax - tamMin) + 1) + tamMin;
                    System.out.println("Valor " + (i + 1) + " = " + valores[i]);
                }
                return valores;
            case '0':
                System.out.println("Retornando...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
        return null;
    }
}
