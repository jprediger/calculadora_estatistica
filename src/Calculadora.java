
public class Calculadora {

    private int opc = 0, opcmed = 0;
    private Arquivo arquivo = new Arquivo("resultados.csv");

    public double media(double[] valores) {

        double soma = 0;
        this.opc = 1;

        for (int i = 0; i < valores.length; i++) {
            soma += valores[i];
        }
        salvarOp(valores);
        return valores.length > 0 ? soma / valores.length : 0;
    }

    public double mediana(double[] valores) {

        int n = valores.length;
        boolean trocou;
        this.opc = 2;

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
            this.opcmed = 1;
            salvarOp(valores);
            return valores[n / 2];
        } else {
            this.opcmed = 2;
            salvarOp(valores);
            return (valores[(n / 2) - 1] + valores[n / 2]) / 2.0;
        }
    }

    public double desvioPadrao(double[] valores) {

        double media = media(valores);
        double soma = 0;
        this.opc = 3;

        for (int i = 0; i < valores.length; i++) {
            soma += (Math.pow((valores[i] - media), 2));
        }

        salvarOp(valores);

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

    private void salvarOp(double[] valores) {

        arquivo.abrirEscrita();

        String aux = "", linha = "";

        switch (this.opc) {

            case 1: // operacao da media
                aux = "(";
                for (int i = 0; i < valores.length; i++) {
                    if (i < (valores.length - 1)) {
                        aux += (valores[i] + "+");
                    } else {
                        aux += valores[i];
                    }
                }
                linha = aux + ")" + " / " + valores.length;
                break;
            case 2: // operacao da mediana
                if (this.opcmed == 1) {
                    linha = valores.length + " / 2";
                } else if (this.opcmed == 2) {
                    linha = "((" + valores.length + " / 2) - 1)" + " + ((" + valores.length + " / 2) / 2)";
                }
                break;
            case 3:
                linha = "sqrt (";
                String linha2 = "(";
                for (int i = 0; i < valores.length; i++) {
                    linha2 += valores[i];
                    if (i < valores.length - 1) {
                        linha2 += "+";
                    }
                }
                linha2 += " / " + valores.length + ")";
                for (int j = 0; j < valores.length; j++) {
                    aux += "(" + valores[j] + " - " + linha2 + ")";
                    if (j < valores.length - 1) {
                        aux += " ^ 2 + "; // Adiciona o termo ^ 2 após o valor
                    }
                }
                linha += aux + ") / " + valores.length;
                break;

        }
        System.out.println(linha);
        arquivo.escreverLinha(linha);
        arquivo.fecharArquivo();
    }
}