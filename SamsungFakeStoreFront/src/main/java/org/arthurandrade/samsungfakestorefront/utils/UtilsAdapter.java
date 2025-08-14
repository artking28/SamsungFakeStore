package org.arthurandrade.samsungfakestorefront.utils;

import jakarta.inject.Named;

import java.util.Date;

// Esse arquivo cria um acesso indireto a 'Utils' já que métodos estáticos não são permitidos no XHTML
@Named("utilsAdapter")
public class UtilsAdapter {

    public String formatData(Date date) {
        System.out.println("Passou aqui");
        return Utils.formatData(date);
    }

    public Long seconds(Long n) {
        return Utils.seconds(n);
    }

    public Long minutes(Long n) {
        return Utils.minutes(n);
    }
}
