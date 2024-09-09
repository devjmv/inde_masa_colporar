package com.masa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class IndicesDeMasaCorporalTest {

    @Test
    void testCalculateIMC_ValidValues() {
        assertEquals(22.093170054286073, IndicesDeMasaCorporal.CalculateIMC(70, 1.78), 0.1);
        assertEquals(24.691358024691358, IndicesDeMasaCorporal.CalculateIMC(80, 1.80), 0.1);
        assertEquals(17.71541950113379, IndicesDeMasaCorporal.CalculateIMC(50, 1.68), 0.1);
    }

    @Test
    void testCalculateIMC_NegativeOrZeroValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            IndicesDeMasaCorporal.CalculateIMC(0, 1.75);
        }, "Altura y peso no pueden ser negativos ni cero");

        assertThrows(IllegalArgumentException.class, () -> {
            IndicesDeMasaCorporal.CalculateIMC(70, 0);
        }, "Altura y peso no pueden ser negativos ni cero");

        assertThrows(IllegalArgumentException.class, () -> {
            IndicesDeMasaCorporal.CalculateIMC(-70, 1.75);
        }, "Altura y peso no pueden ser negativos ni cero");

        assertThrows(IllegalArgumentException.class, () -> {
            IndicesDeMasaCorporal.CalculateIMC(70, -1.75);
        }, "Altura y peso no pueden ser negativos ni cero");
    }

    @Test
    void testClassifyIMC() {
        assertEquals("Delgadez Severa", IndicesDeMasaCorporal.classifyIMC(15));
        assertEquals("Delgadez moderada", IndicesDeMasaCorporal.classifyIMC(16.5));
        assertEquals("Delgadez aceptable", IndicesDeMasaCorporal.classifyIMC(17.5));
        assertEquals("Normal Ideal", IndicesDeMasaCorporal.classifyIMC(22));
        assertEquals("Sobrepeso", IndicesDeMasaCorporal.classifyIMC(27));
        assertEquals("Obeso de tipo I", IndicesDeMasaCorporal.classifyIMC(32));
        assertEquals("Obeso de tipo II", IndicesDeMasaCorporal.classifyIMC(37));
        assertEquals("Obeso de tipo III", IndicesDeMasaCorporal.classifyIMC(42));
    }

    @Test
    public void testMain() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        try {
            IndicesDeMasaCorporal.main(new String[]{});
        } catch (Exception e) {
            fail("No se esperaba una excepción");
        }

        System.setOut(originalOut);

        String output = outContent.toString().trim();

        // Dividir en líneas para comparar individualmente
        String[] expectedLines = {
            "Altura: 70.0 kg",
            "Peso: 1.58 m",
            "IMC: 28.040378144528116",
            "Clasificacion: Sobrepeso"
        };

        String[] outputLines = output.split("\\r?\\n");

        // Comparar las líneas de salida
        assertEquals(expectedLines.length, outputLines.length, "Número de líneas diferentes");

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i], outputLines[i], "Línea " + (i + 1) + " incorrecta");
        }
    }
}
