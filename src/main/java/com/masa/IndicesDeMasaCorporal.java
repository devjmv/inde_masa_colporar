package com.masa;

public class IndicesDeMasaCorporal {

    public static double CalculateIMC(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("Altura y peso no pueden ser negativos ni cero");
        }
        return weight / Math.pow(height, 2);
    }

    public static String classifyIMC(double imc) {
        if (imc < 16)
            return "Delgadez Severa";
        if (imc >= 16 && imc < 16.99)
            return "Delgadez moderada";
        if (imc >= 17 && imc < 18.49)
            return "Delgadez aceptable";
        if (imc >= 18.5 && imc < 24.99)
            return "Normal Ideal";
        if (imc >= 25 && imc < 29.99)
            return "Sobrepeso";
        if (imc >= 30 && imc < 34.99)
            return "Obeso de tipo I";
        if (imc >= 35 && imc < 39.99)
            return "Obeso de tipo II";
        return "Obeso de tipo III";

    }

    public static void main(String[] args) {
        double weight = 70;
        double height = 1.58;

        try {
            double imc = CalculateIMC(weight, height);
            String classification = classifyIMC(imc);

            System.out.println("Altura: " + weight + " kg");
            System.out.println("Peso: " + height + " m");
            System.out.println("IMC: " + imc);
            System.out.println("Clasificacion: " + classification);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
