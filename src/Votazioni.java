package progettoVotazione;
import java.util.Scanner;
import java.util.Random;

public class Votazioni {
	
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	
	private static Scanner scanner = new Scanner(System.in);
	
	private final static int maxVoters = 100;
	
	private static String[] voters = new String[maxVoters];
	private static int[] votes = new int[maxVoters];
	
	private static int validVoters = 0;
	
	private final static String adminLogin = "admin";
	
	
	public static void main(String[] args) {
		
		boolean isLoggedIn = true;
		
		while(isLoggedIn) {
			System.out.println("Inserisci il tuo nome" 
					+ "\nScrivi 'quit' per uscire dal programma");
			
			String name = scanner.next();
			char ch = name.charAt(0);
			if(ch == 'q' || ch == 'Q') {
				isLoggedIn = false;
				System.out.println(ANSI_GREEN + "Uscita dal programma effettuata" + ANSI_RESET);
			} else {
				if(isAdmin(name)) {adminCommands();} 
				else {userCommands();}
			}
			
		}
		
		scanner.close();
	}
	
	private static boolean isAdmin(String name) {
		return name.equals(adminLogin);
	}
	
	private static void adminCommands() {
		System.out.println(ANSI_GREEN + "Sei entrato come amministratore" + ANSI_RESET);
		boolean isValid = false;
		
		while(!isValid) {		
			System.out.println("1. Inserisci un nuovo partecipante "
					+ "\n2. Visualizza la lista dei partecipanti"
					+ "\n3. Visualizza statistiche voti"
					+ "\n4. Valori di test"
					+ "\nquit per fare il log-out");
			
			if(scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				switch(choice) {
				case 1:
					addVoter();
					break;
				case 2:
					showVoters();
					break;
				case 3:
					showStats();
					break;
				case 4:
					testFnc();
					break;
				default:
					System.out.println(ANSI_RED + "Numero non valido" + ANSI_RESET);
					break;
				}
			} else {
				char ch = scanner.next().charAt(0);
				if(ch == 'q' || ch == 'Q') {
					isValid = true;
					System.out.println(ANSI_GREEN + "Log-out effettuato" + ANSI_RESET);
				} else {
					scanner.nextLine();
					System.out.println("Inserisci un numero valido");					
				}
			}
		}
	}
	
	
	private static void addVoter() {
        if(validVoters >= 100) {
            System.out.println("Superato il limite di partecipanti(" + maxVoters + ")");
            return;
        }
        
        boolean isValid = false;
        while(!isValid) {
            System.out.println("Inserisci nome partecipante" 
            		+ "\nquit per annullare comando");

            String voterName = scanner.next();
            voterName = voterName.strip(); //remove trailing and leading whitespace
            
            
			if(voterName.equalsIgnoreCase("quit")) {
				isValid = true;
				System.out.println(ANSI_GREEN + "Comando annullato" + ANSI_RESET);
			} else {
	            if(!voterExists(voterName)) {
	                voters[validVoters] = voterName;
	                System.out.println(ANSI_GREEN + "\nPartecipante " + voters[validVoters] + " inserito\n" + ANSI_RESET);
	                validVoters++;
	                isValid = true;
	            } else {
	                System.out.println(ANSI_RED + "\nPartecipante già esistente\n" + ANSI_RESET);
	            }
			}
        }
    }

    private static boolean voterExists(String name) {
        for (String votee : voters) {
            if(votee != null) {
                if (votee.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
	private static void showVoters() {
		if(!isEmpty(voters)) {
			System.out.println("\nVoti di ogni partecipante:");
			for (int i = 0; i < voters.length; i++) {
				if(voters[i] != null)
					System.out.println((i + 1) + ". " + voters[i] + " - " + votes[i] + " voti");
			}
			System.out.println(""); //linebreak
		} else {
			System.out.println(ANSI_RED + "\nNessun partecipante presente\n" + ANSI_RESET);
		}
	}
	
	private static boolean isEmpty(String[] array) {
		for(String s : array)
			if(s != null) return false;
		
		return true;
	}
	
	private static void showStats() {
		if(isEmpty(voters)) {
			System.out.println(ANSI_RED + "\nNessun partecipante presente\n" + ANSI_RESET);
			return;
		}
		
		System.out.println(ANSI_GREEN + "Statistiche votazioni:" + ANSI_RESET);
		
		String highestVotee = voters[0];
		String lowestVotee = voters[0];
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		int highestIndex = 0;
		int lowestIndex = 0;
		
		int sum = 0;
		
		for (int i = 0; i < voters.length; i++) {

			if(voters[i] != null) {
				if(votes[i] > max) {
					max = votes[i];
					highestIndex = i;
					highestVotee = voters[i];
				}
				if(votes[i] < min) {
					min = votes[i];
					lowestIndex = i;
					lowestVotee = voters[i];
				}

				sum += votes[i];
			}
		}
		
		
		System.out.println("Il partecipante con il maggior numero di voti è " + highestVotee + " il numero: " + (highestIndex+1)
				+ "\nIl partecipante con il minor numero di voti è " + lowestVotee + " il numero: " + (lowestIndex+1)
				+ "\nLa somma dei voti: " + sum + "\n");
	}
	
	private static void userCommands() {
		System.out.println(ANSI_GREEN + "Benvenuto fai la tua scelta" + ANSI_RESET);
		boolean isValid = false;
		
		while(!isValid) {			
			System.out.println("1. Vota partecipante"
					+ "\n2. per visualizzare la lista dei partecipanti"
					+ "\nquit per fare il log-out");
			
			if(scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				switch(choice) {
				case 1:
					addVote();
					break;
				case 2:
					showVoters();
					break;
				default:
					System.out.println(ANSI_RED + "Numero non valido" + ANSI_RESET);
					break;
				}
			} else {
				char ch = scanner.next().charAt(0);
				if(ch == 'q' || ch == 'Q') {
					isValid = true;
					System.out.println(ANSI_GREEN + "Log-out effettuato" + ANSI_RESET);
				} else {
					scanner.nextLine();
					System.out.println(ANSI_RED + "\nInserisci un numero valido\n" + ANSI_RESET);					
				}
			}
		}
	}
	
	
	private static void addVote() {
		
		if(isEmpty(voters)) {
			System.out.println(ANSI_RED + "\nNessun partecipante presente\n" + ANSI_RESET);
			return;
		}
		
		System.out.println("Scegliere numero partecipante a cui aggiungere il vostro voto."
				+ "\nScrivi 'quit' per annullare comando");
		
		boolean isValid = false;
		while(!isValid) {
			if(scanner.hasNextInt()) {
				int voterIndex = scanner.nextInt() - 1; //list displayed to console starts at 1
				
				if(voterIndex >= 0 && voterIndex < validVoters) {
					if(voters[voterIndex] != null) { //check if voter actually exists in data
						votes[voterIndex]++;
						isValid = true;
						System.out.println(ANSI_GREEN + "\nAggiunto il vostro voto a " + voters[voterIndex] + "\n" + ANSI_RESET);
					} else {
						System.out.println(ANSI_RED + "\nPartecipante non esistente\n" + ANSI_RESET);
					}
				} else {
					System.out.println(ANSI_RED + "\nPartecipante non esistente\n" + ANSI_RESET);
				}
				
			} else {
				char ch = scanner.next().charAt(0);
				if(ch == 'q' || ch == 'Q') {
					System.out.println(ANSI_GREEN + "Comando annullato" + ANSI_RESET);
					isValid = true;
				} else {
					scanner.nextLine();
					System.out.println(ANSI_RED + "\nInserisci un numero valido\n" + ANSI_RESET);					
				}
			}
		}
	}
	
	private static void testFnc() { //add some test values for rapid testing
		Random rng = new Random();
		
		voters[0] = "Marta";
		votes[0] = rng.nextInt(10);
		
		voters[1] = "Paolo";
		votes[1] = rng.nextInt(10);
		
		voters[2] = "Francesca";
		votes[2] = rng.nextInt(10);
		
		voters[3] = "Simone";
		votes[3] = rng.nextInt(10);
		
		voters[4] = "Maurizio";
		votes[4] = rng.nextInt(10);
		
		voters[5] = "Cinzia";
		votes[5] = rng.nextInt(10);
		
		validVoters = 6;
		
		System.out.println(ANSI_GREEN + "\nValori di test aggiunti\n" + ANSI_RESET);
	}

}
