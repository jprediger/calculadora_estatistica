
public class Calculadora {

    public double media(double[] valores){

        double soma = 0;
        for (int i = 0; i < valores.length; i++) {
            soma += valores[i];
        }
        return valores.length > 0 ? soma / valores.length : 0;
    }

    public double mediana(double[] valores){

        int n = valores.length;
        boolean trocou;

        // ORGANIZA O ARRAY EM ORDEM CRESCENTE
        do {
            trocou = false;
            for (int i = 1; i < n; i++) {
                if (valores[i - 1] > valores[i]) {
                    double temp = valores[i - 1];
                    valores[i - 1] = valores[i];
                    valores[i] = temp;
                    trocou = true;
                }
            }
        } while (trocou);

        // CONFERE SE O NÚMERO DE ITENS NO ARRAY É IMPAR OU PAR E FINALIZA O CÁLCULO
        if (n % 2 == 1) {
            return valores[n / 2];
        } else {
            return (valores[(n / 2) - 1] + valores[n / 2]) / 2.0;
        }
    }

    public double desvioPadrao(double[] valores) {

        double media = media(valores);
        double soma = 0;

        for (int i = 0; i < valores.length; i++) {
            soma += (Math.pow((valores[i] - media), 2));
        }
        
        System.out.println(valores.length);
        return Math.sqrt(soma / valores.length);
    }

    public void salvarResultado(double resultado, Arquivo arquivo) {
        String linha;
        arquivo.abrirEscrita();
        linha = resultado + ";";
        arquivo.escreverLinha(linha);
        arquivo.fecharArquivo();
    }
}