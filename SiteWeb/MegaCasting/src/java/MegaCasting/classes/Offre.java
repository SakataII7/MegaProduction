package MegaCasting.classes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ikiuchi
 */
public class Offre {
    
    protected Long Identifiant;
    protected String Intitule;
    protected String Reference;
    protected Date DatePublication;
    protected Date DateDebutContrat;
    protected int DureeDiffusion;
    protected int NbPostes;
    protected String DescriptionPoste;
    protected String DescriptionProfil;
    protected String Telephone;
    protected String Email;

    

    public long getIdentifiant() {
        return Identifiant;
    }

    public void setIdentifiant(long Identifiant) {
        this.Identifiant = Identifiant;
    }

    public String getIntitule() {
        return Intitule;
    }

    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public Date getDatePublication() {
        return DatePublication;
    }

    public void setDatePublication(Date DatePublication) {
        this.DatePublication = DatePublication;
    }

    public Date getDateDebutContrat() {
        return DateDebutContrat;
    }

    public void setDateDebutContrat(Date DateDebutContrat) {
        this.DateDebutContrat = DateDebutContrat;
    }

    public int getDureeDiffusion() {
        return DureeDiffusion;
    }

    public void setDureeDiffusion(int DureeDiffusion) {
        this.DureeDiffusion = DureeDiffusion;
    }

    public int getNbPostes() {
        return NbPostes;
    }

    public void setNbPostes(int NbPostes) {
        this.NbPostes = NbPostes;
    }

    public String getDescriptionPoste() {
        return DescriptionPoste;
    }

    public void setDescriptionPoste(String DescriptionPoste) {
        this.DescriptionPoste = DescriptionPoste;
    }

    public String getDescriptionProfil() {
        return DescriptionProfil;
    }

    public void setDescriptionProfil(String DescriptionProfil) {
        this.DescriptionProfil = DescriptionProfil;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String Telephone) {
        this.Telephone = Telephone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.Identifiant);
        hash = 79 * hash + Objects.hashCode(this.Intitule);
        hash = 79 * hash + Objects.hashCode(this.Reference);
        hash = 79 * hash + Objects.hashCode(this.DatePublication);
        hash = 79 * hash + Objects.hashCode(this.DateDebutContrat);
        hash = 79 * hash + this.DureeDiffusion;
        hash = 79 * hash + this.NbPostes;
        hash = 79 * hash + Objects.hashCode(this.DescriptionPoste);
        hash = 79 * hash + Objects.hashCode(this.DescriptionProfil);
        hash = 79 * hash + Objects.hashCode(this.Telephone);
        hash = 79 * hash + Objects.hashCode(this.Email);
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.Identifiant != other.Identifiant) {
            return false;
        }
        if (!Objects.equals(this.Intitule, other.Intitule)) {
            return false;
        }
        if (!Objects.equals(this.Reference, other.Reference)) {
            return false;
        }
        if (!Objects.equals(this.DatePublication, other.DatePublication)) {
            return false;
        }
        if (!Objects.equals(this.DateDebutContrat, other.DateDebutContrat)) {
            return false;
        }
        if (this.DureeDiffusion != other.DureeDiffusion) {
            return false;
        }
        if (this.NbPostes != other.NbPostes) {
            return false;
        }
        if (!Objects.equals(this.DescriptionPoste, other.DescriptionPoste)) {
            return false;
        }
        if (!Objects.equals(this.DescriptionProfil, other.DescriptionProfil)) {
            return false;
        }
        if (!Objects.equals(this.Telephone, other.Telephone)) {
            return false;
        }
        if (!Objects.equals(this.Email, other.Email)) {
            return false;
        }
        return true;
    }   
}

