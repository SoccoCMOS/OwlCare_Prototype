package co.owlmed_dz.owlcare.Model;
import android.media.Image;

public class Nurse {

    String Nom;
    String Prenom;
    String metier;
    String adresse;
    int phone;
    String email;
    Image image;

    public Nurse(String nom, String prenom, String metier, String adresse, int phone, String email, Image image) {
        Nom = nom;
        Prenom = prenom;
        metier = metier;
        this.adresse = adresse;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getmetier() {
        return metier;
    }

    public void setmetier(String metier) {
        metier = metier;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
