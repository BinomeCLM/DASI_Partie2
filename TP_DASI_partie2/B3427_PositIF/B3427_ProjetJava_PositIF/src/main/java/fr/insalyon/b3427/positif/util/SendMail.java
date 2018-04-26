package fr.insalyon.b3427.positif.util;

/**
 *
 * @author B3427
 */
public class SendMail {
    public static String expediteur = "contact@posit.if";
    public static void send(String pour, String sujet, String content){
        System.out.println("---Envoi du courriel---");
        System.out.println("Expediteur: "+expediteur);
        System.out.println("Pour: "+pour);
        System.out.println("Sujet: "+sujet);
        System.out.println("Corps:");
        System.out.println(content);
        System.out.println("-----------------------");
    }
}
