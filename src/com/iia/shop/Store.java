package com.iia.shop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.iia.shop.entity.Vehicule;

public class Store {

	private static ArrayList<Vehicule> vehicules;
	private static Scanner sc;

	public static void main(String[] args) {
		Store.vehicules = new ArrayList<Vehicule>();

		assets();

		System.out.println("Gestion des v�hicules");
		System.out.println("1 - cr�er un v�hicule");
		System.out.println("2 - mettre � jour un v�hicule");
		System.out.println("3 - voir tous les v�hicules");
		System.out.println("4 - s�lectionner un v�hicule");
		System.out.println("5 - supprimer un v�hicule");

		Store.sc = new Scanner(System.in);

		System.out.println("Votre choix");
		int choice = Store.sc.nextInt();

		Vehicule vehicule;

		switch (choice) {
		case 1:
			vehicule = new Vehicule();

			setVehicule(vehicule);
			create(vehicule);
			break;
		case 2:
			System.out.println("Veuillez saisir l'id du v�hicule");
			vehicule = read(Store.sc.nextInt());

			displayVehicule(vehicule);
			setVehicule(vehicule);
			break;
		case 3:
			ArrayList<Vehicule> vehicules = readAll();

			for (Vehicule vehicule2 : vehicules) {
				displayVehicule(vehicule2);
			}
			break;

		case 4:
			System.out.println("Veuillez saisir l'id du v�hicule");
			vehicule = read(Store.sc.nextInt());

			displayVehicule(vehicule);

			break;

		case 5:
			System.out.println("Veuillez saisir l'id du v�hicule");
			delete(Store.sc.nextInt());
			break;
		default:
			break;
		}

		// fichier

		File dir = new File("C:\\Users\\Aurianne\\workspace");

		// pour lire un fichier ou un dossier sur le PC
		File file = new File(dir, "Voitures.txt");
		readFile(file);

		// pour creer un fichier et ecrire dedans
		File newFile = new File("C:\\Users\\Aurianne\\Voitures.txt");
		createFile(newFile, "ici mon texte que je veux enregistrer");

		// S�rialisation d'un objet dans un fichier
		File serialFile = new File("C:\\Users\\Aurianne\\voitures.txt");
		Vehicule vehicule1 = new Vehicule();
		vehicule1.setMarque("Alfa");
		vehicule1.setSpeed(150);
		Store.saveObject(serialFile, vehicule1);
		
		// D�s�rialisation d'un objet venant d'un fichier
		//Store.readObject(serialFile); // lit le fichier binaire
	}

	private static void create(Vehicule vehicule) {
		Store.vehicules.add(vehicule);
	}

	private static void delete(int index) {
		Store.vehicules.remove(index);
	}

	private static ArrayList<Vehicule> readAll() {
		return Store.vehicules;
	}

	private static Vehicule read(int index) {
		return Store.vehicules.get(index);
	}

	private static void displayVehicule(Vehicule vehicule) {
		System.out.println("Marque : " + vehicule.getMarque());
		System.out.println("Mod�le : " + vehicule.getModel());
		System.out.println("Couleur : " + vehicule.getColor());
		System.out.println("Ann�e : " + vehicule.getYear());
		System.out.println("Vitesse : " + vehicule.getSpeed());
		System.out.println("Prix : " + vehicule.getPrice());
	}

	private static void setVehicule(Vehicule vehicule) {
		System.out.println("Veuillez saisir la marque du v�hicule");
		vehicule.setMarque(Store.sc.next());

		System.out.println("Veuillez saisir l'ann�e du v�hicule");
		vehicule.setYear(Store.sc.nextInt());

		System.out.println("Veuillez saisir le mod�le du v�hicule");
		vehicule.setModel(Store.sc.next());

		System.out.println("Veuillez saisir la couleur du v�hicule");
		vehicule.setColor(Store.sc.next());

		System.out.println("Veuillez saisir le prix du v�hicule");
		vehicule.setPrice(Store.sc.nextDouble());
	}

	private static void assets() {
		Vehicule v1 = new Vehicule("peugeot", 2016, 50, "3008", "blanc", 20000);
		Vehicule v2 = new Vehicule("audi", 2016, 90, "A5", "noire", 47000);

		Store.vehicules.add(v1);
		Store.vehicules.add(v2);
	}

	// lire un fichier ou un dossier
	public static void readFile(File file) {
		try {
			FileReader reader = new FileReader(file);

			BufferedReader buffer = new BufferedReader(reader);

			while (buffer.ready()) {
				System.out.println(buffer.readLine());
			}

			buffer.close(); // on ferme le buffer

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// creer un fichier et ecrire dedans
	public static void createFile(File file, String value) {
		try {
			// FileWriter writer = new FileWriter(file, true);
			// si on met un boolean a true il faut faire une newLine sinon il
			// met le texte a la suite
			FileWriter writer = new FileWriter(file);

			BufferedWriter buffer = new BufferedWriter(writer);

			buffer.write(value); // ecrit le texte du createFile
			buffer.newLine(); // pour ecrire sur une deuxieme ligne
			buffer.write(value);

			buffer.close(); // on ferme le buffer

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// S�rialisation d'un objet dans un fichier
	public static void saveObject(File file, Vehicule vehicule) {
		try {
			FileOutputStream out = new FileOutputStream(file);

			ObjectOutputStream objectOutput = new ObjectOutputStream(out);

			objectOutput.writeObject(vehicule);

			objectOutput.close(); // on ferme le buffer

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public static void readObject(File file) {
		try {
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(in);

			Vehicule vehicule = (Vehicule) objectIn.readObject();

			objectIn.close();

			System.out.println("Marque de la voiture : " + Vehicule.getMarque());
			System.out.println("Vitesse de la voiture : " + Vehicule.getSpeed());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
