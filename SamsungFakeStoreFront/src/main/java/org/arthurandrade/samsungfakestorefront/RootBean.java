package org.arthurandrade.samsungfakestorefront;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import lombok.Data;

@Data
@Named("rootBean")
@RequestScoped
public class RootBean {

    private String firstName;

    private String lastName;

}
