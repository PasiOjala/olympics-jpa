/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Pasi
 */
@Named
@ApplicationScoped
public class TheYears {
    private final List<Integer> years=new ArrayList<>();

    public List<Integer> getYears() {
        return years;
    }

    public TheYears() {
        for (int i = 1900; i < 2020; i++) {
            years.add(i);
        }
    }
    public static void main(String[] args) {
        TheYears ty=new TheYears();
        ty.getYears().stream().forEach((y) -> {
            System.out.println(y);
        });
    }
}
