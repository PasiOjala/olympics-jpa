/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;

/**
 *
 * @author Pasi
 */
@Named
@Singleton
public class TulosGeneraattori {

    private Part tiedosto;
    private String taulu="Tulos";
    private String prevEvent="";

    public Part getTiedosto() {
        return tiedosto;
    }

    public void setTiedosto(Part tiedosto) {
        this.tiedosto = tiedosto;
    }

    public String getTaulu() {
        return taulu;
    }

    public void setTaulu(String taulu) {
        this.taulu = taulu;
    }
    @PersistenceContext
    private EntityManager em;

    public String vie() throws IOException {
        ZipInputStream zs
                = new ZipInputStream(tiedosto.getInputStream());
        ZipEntry ze = null;
        while ((ze = zs.getNextEntry()) != null) {
            if (ze.getName().equals("o50k.txt")) {
                lueTulokset(zs);
            }
        }
        return null;
    }

    private void lueTulokset(InputStream is) {
        Scanner s = new Scanner(is,"UTF-8");
        String titlesDiscarded = s.nextLine();
        while (s.hasNextLine()) {
            String[] kentät = s.nextLine().split(";");
                    if (!kentät[1].equals("")){
                        prevEvent=kentät[1];
                    }
                    else {
                        kentät[1]=prevEvent;
                    }
                    lisääTulos(kentät);
        }
    }
    
     private void lisääTulos(String[] kentät) {
        
        Tulos temp=
                new Tulos(
                        new Integer(kentät[0]),
                        kentät[1],
                        kentät[2],
                        kentät[3],
                        kentät[4],
                        kentät[5]
                );
        em.persist(temp);
    }
     @Produces @Named("tulokset")
     public List<Tulos> palautaTuloksetKannasta(){
         return em.createNamedQuery("Tulos.haeKaikki", Tulos.class)
                 .getResultList();
     }
}
