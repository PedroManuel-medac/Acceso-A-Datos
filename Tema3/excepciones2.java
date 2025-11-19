public class excepciones2 {
    public static void main(String[] args) {
        int dividiendo = 10;
        int divisor = 0;

        try{
        int resultado = dividiendo / divisor;
        System.out.println(resultado);
    
       }catch(ArithmeticException e){
        System.out.println("Erro: " + e.getMessage());
       }
    }
}
