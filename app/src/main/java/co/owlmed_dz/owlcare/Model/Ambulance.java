package co.owlmed_dz.owlcare.Model;

import android.media.Image;

/**
 * Created by DUALCOMPUTER on 1/11/2017.
 */

public class Ambulance {

    String Nom;
    String Prenom;
    String services;
    String adresse;
    int phone;
    String email;
    Image image;

    public Ambulance(String nom, String prenom, String services, String adresse, int phone, String email, Image image) {
        Nom = nom;
        Prenom = prenom;
        services = services;
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

    public String getservices() {
        return services;
    }

    public void setservices(String services) {
        services = services;
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
