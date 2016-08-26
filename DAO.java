package com.animalsinzoo;


	import java.sql.*;
	import java.util.ArrayList;
import java.util.Scanner;

	public class DAO {
		
		static final String DB_URL= "jdbc:mysql://127.0.0.1:3306/?user=root&autoReconnect=true&useSSL=false";
		static final String USER = "root";
		static final String PASSWORD = "sesame";

		static Connection CONN = null;
		static Statement STMT = null;
		static PreparedStatement PREP_STMT = null;
		static ResultSet RES_SET= null;
		
		static Scanner sc = new Scanner(System.in);
		
		
		
		boolean menuCorrect = false;
		
		public static void connToDB(){
			
			try {
				System.out.println("Trying to connect to the Database...");
				CONN=DriverManager.getConnection(DB_URL, USER, PASSWORD);
				System.out.println("Connected to the database.");
			} catch (SQLException e) {
				System.out.println("Failed to connect to the database.");
				e.printStackTrace();
			}
		}

		public static void readFromDB(){
			
			connToDB();
			ArrayList<Animal> ourAnimals = new ArrayList<>();
			
			try{
				STMT = CONN.createStatement();
				RES_SET= STMT.executeQuery("SELECT * FROM zoo.animals_in_zoo;");
				
				while(RES_SET.next()){
					Animal animalDB = new Animal();
					animalDB.setAnimalID(RES_SET.getInt("animal_id"));
					animalDB.setAnimalSpecies(RES_SET.getString("animal_species"));
					animalDB.setAnimalName(RES_SET.getString("animal_name"));
					animalDB.setAnimalAge(RES_SET.getInt("animal_age"));
					animalDB.setAnimalWeight(RES_SET.getDouble("animal_weight"));
					
					ourAnimals.add(animalDB);
					
				}
				
				for (Animal animal : ourAnimals) {
					System.out.println(animal.toString());
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			
		}
		
		public static void writeToDB(){
			
			
//			try {
//				STMT = CONN.createStatement();
//				STMT.executeUpdate("INSERT INTO `zoo`.`animals_in_zoo`(animal_species, animal_name, animal_age, animal_weight)"+"VALUES('Eel','Eileen',25,350.50)");
//			
//			
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			Animal animalToAdd = new Animal();
			
			animalToAdd = aboutTheAnimal();
			
			connToDB();
			
			try {
				
				
				PREP_STMT = CONN.prepareStatement(insertToDB);
				
				PREP_STMT.setString(1, animalToAdd.getAnimalSpecies());
				
				PREP_STMT.setString(2, animalToAdd.getAnimalName());
				
				PREP_STMT.setInt(3, animalToAdd.getAnimalAge());
				
				PREP_STMT.setDouble(4, animalToAdd.getAnimalWeight());
				
				PREP_STMT.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Here is your updated list.");
			readFromDB();
			
		}
		
		
		private static String insertToDB = "INSERT INTO `zoo`.`animals_in_zoo`"
				+ "(animal_species, animal_name, animal_age, animal_weight)"
				+ "VALUES"
				+ "(?, ?, ?, ?)";
		
		
		
		public static Animal aboutTheAnimal(){
			Animal animalToAdd = new Animal();
			
			System.out.println("What animal species would you like to add?");
			animalToAdd.setAnimalSpecies(sc.nextLine());
			
			System.out.println("What is the name of the animal you are adding?");
			animalToAdd.setAnimalName(sc.nextLine());
			
			System.out.println("How old is the animal?");
			animalToAdd.setAnimalAge(Integer.parseInt(sc.nextLine()));
			
			System.out.println("How much does your animal weight?");
			animalToAdd.setAnimalWeight(Double.parseDouble(sc.nextLine()));
			
			return animalToAdd;
		}
		
		public static void deleteFromDB(){
			
			Animal animalToDelete = new Animal();
			
			animalToDelete = whichToDelete();
			
			connToDB();
			
			
			try {
				PREP_STMT = CONN.prepareStatement(deleteFromDB);
				PREP_STMT.setInt(1, animalToDelete.getAnimalID());
				PREP_STMT.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		private static String deleteFromDB = "DELETE FROM `zoo`.`animals_in_zoo` "
				+ "WHERE animal_id=?";
		
		public static Animal whichToDelete(){
			
			Animal animalToDelete = new Animal();
			System.out.println("Please enter the Animal ID you would like to delete.");
			animalToDelete.setAnimalID(Integer.parseInt(sc.nextLine()));
			
			return animalToDelete;
			
		}
		
		private static String updateToDB = "UPDATE `zoo`.`animals_in_zoo`SET"
                + " animal_id = ?,"
                + " animal_species = ?,"
                + " animal_name = ?," 
                + "animal_age = ?,"
                + "animal_weight = ?"
                + "WHERE animal_id = ?";

public static void updateToDatabase() {
            
            Animal animalToUpdate = new Animal();
            
            animalToUpdate = updateTheAnimal();
            
            connToDB();
            
            try {
                
                PREP_STMT = CONN.prepareStatement(updateToDB);
                
                PREP_STMT.setInt(1, animalToUpdate.getAnimalID());
                PREP_STMT.setString(2, animalToUpdate.getAnimalSpecies());
                PREP_STMT.setString(3, animalToUpdate.getAnimalName());
                PREP_STMT.setInt(4, animalToUpdate.getAnimalAge());
                PREP_STMT.setDouble(5, animalToUpdate.getAnimalWeight());
                PREP_STMT.setInt(6, animalToUpdate.getAnimalID());
                
                PREP_STMT.executeUpdate();
                
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } //updateToDatabase
		
		
public static Animal updateTheAnimal() {
            
            Animal animalToUpdate = new Animal();
            
            System.out.println("Enter the ID of the animal you want to update:");
            animalToUpdate.setAnimalID(Integer.parseInt(sc.nextLine()));
            
            System.out.println("What is the animal's species?");
            animalToUpdate.setAnimalSpecies(sc.nextLine());
            
            System.out.println("What is the animal's name?");
            animalToUpdate.setAnimalName(sc.nextLine());
            
            System.out.println("What is animal's age?");
            animalToUpdate.setAnimalAge(Integer.parseInt(sc.nextLine()));
            
            System.out.println("What is the animal's weight?");
            animalToUpdate.setAnimalWeight(Double.parseDouble(sc.nextLine()));
            
            return animalToUpdate;
            
        } //updateTheAnima
		
	} //class


