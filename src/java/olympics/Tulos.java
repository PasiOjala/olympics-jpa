/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olympics;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Pasi
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(
                    name = "Tulos.haeKaikki",
                    query = "select t from Tulos t order by t.yearOfEvent,t.positionNumber"
            ),

            @NamedQuery(
                    name = "Tulos.haeVäliltä",
                    query = "select t from Tulos t "
                    + "where t.yearOfEvent between :start and :end"
                    + " order by t.yearOfEvent,t.positionNumber"
            )

        }
)
//@Table(name=TulosGeneraattori.getTauluForTulos())
public class Tulos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int yearOfEvent;
    private String event;
    private String athlete;
    private String medal;
    private String country;
    private String resultTime;
    private int positionNumber;

    public Tulos(int yearOfEvent, String event, String athlete, String medal, String country, String resultTime) {
        this.yearOfEvent = yearOfEvent;
        this.event = event;
        this.athlete = athlete;
        this.medal = medal;
        this.country = country;
        this.resultTime = resultTime;
        switch (medal) {
            case "GOLD":
                this.positionNumber = 1;
                break;
            case "SILVER":
                this.positionNumber = 2;
                break;
            case "BRONZE":
                this.positionNumber = 3;
                break;
        }
    }

    public int getYearOfEvent() {
        return yearOfEvent;
    }

    public void setYearOfEvent(int yearOfEvent) {
        this.yearOfEvent = yearOfEvent;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAthlete() {
        return athlete;
    }

    public void setAthlete(String athlete) {
        this.athlete = athlete;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResultTime() {
        return resultTime;
    }

    public void setResultTime(String resultTime) {
        this.resultTime = resultTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tulos)) {
            return false;
        }
        Tulos other = (Tulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "olympics.Tulos[ id=" + id + " ]";
    }

    public Tulos() {
    }

}
