package com.smartsoft.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        LocalDate todayDate = LocalDate.now();
        String s = todayDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println(s);

    }

}
