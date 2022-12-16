package org.example;

import java.lang.reflect.Field;

public class JavaStrings {

    public static void main(String [] args) throws Exception {
        String nome = "Thadeu de Russo e Carmo";
        nome.toUpperCase();
        System.out.println(nome);

        alteraString(nome);

        System.out.println(nome);
    }

    public static void alteraString(String nome) throws Exception {
        Field value = String.class.getDeclaredField("value");
        //1
         value.setAccessible(true);
        // 2
         byte[] charsDaString = (byte[]) value.get(nome);
        // 3
         charsDaString[0] = 't';
         charsDaString[1] = 'H';
         charsDaString[2] = 'A';
         charsDaString[3] = 'D';
         charsDaString[4] = 'E';


    }
}
