import java.util.*;

/**
 * The HospitalManagementSystem class is the main entry point for the hospital 
 * patient management system, providing an interactive menu for users to manage 
 * patients, including adding, removing, treating, and searching for patients 
 * based on severity.
 */
public class HospitalManagementSystem {

    /**
     * Main method which runs the hospital management system.
     * It presents the user with a menu of options and performs the selected actions.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager patientManager = new PatientManager();
        HospitalQueue hospitalQueue = new HospitalQueue();
        DischargeManager dischargeManager = new DischargeManager();
        
        // Load patients from the file
        TXTReader.loadPatients("patients.txt", patientManager, hospitalQueue);

        // Main loop to display the menu and handle user input
        while (true) {
            System.out.println("\n🏥 Hospital Patient Management System");
            System.out.println("1. Add Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Sort Patients by Severity");
            System.out.println("4. Search Patient by Severity (Binary Search)");
            System.out.println("5. Count Patients with Severity Above a Level");
            System.out.println("6. Treat Patient (Highest Severity First)");
            System.out.println("7. Undo Last Discharge");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Switch case to handle different user options
            switch (choice) {
                case 1:
                    // Add a new patient to the system
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Condition: ");
                    String condition = scanner.nextLine();
                    System.out.print("Enter Severity (1-10): ");
                    int severity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Patient newPatient = new Patient(name, condition, severity);
                    patientManager.addPatient(newPatient);
                    hospitalQueue.addPatient(newPatient);
                    System.out.println("✅ Patient added successfully.");
                    break;

                case 2:
                    // Display all the patients in the system
                    System.out.println("\n📋 All Patients:");
                    patientManager.displayPatients();
                    break;

                case 3:
                    // Sort patients by their severity
                    patientManager.sortPatientsBySeverity();
                    System.out.println("✅ Patients sorted by severity.");
                    break;

                case 4:
                    // Search for patients by severity using binary search
                    System.out.print("Enter severity to search: ");
                    int searchSeverity = scanner.nextInt();
                    List<Patient> foundPatients = patientManager.binarySearchBySeverity(searchSeverity);
                    
                    if (!foundPatients.isEmpty()) {
                        System.out.println("🔍 Patients with severity " + searchSeverity + ":");
                        for (Patient p : foundPatients) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 5:
                    // Count the number of patients with severity above a certain threshold
                    System.out.print("Enter severity threshold: ");
                    int threshold = scanner.nextInt();
                    int count = patientManager.countSeverePatients(threshold, 0);
                    System.out.println("📊 Patients with severity > " + threshold + ": " + count);
                    break;

                case 6:
                    // Treat the patient with the highest severity from the hospital queue
                    Patient treated = hospitalQueue.treatPatient();
                    if (treated != null) {
                        patientManager.removePatient(treated);
                        dischargeManager.dischargePatient(treated);
                        System.out.println("🩺 Treated: " + treated);
                    } else {
                        System.out.println("❌ No patients to treat.");
                    }
                    break;

                case 7:
                    // Undo the last patient discharge
                    Patient undone = dischargeManager.undoDischarge();
                    if (undone != null) {
                        patientManager.addPatient(undone);
                        hospitalQueue.addPatient(undone);
                        System.out.println("🔄 Re-admitted: " + undone);
                    } else {
                        System.out.println("❌ No discharges to undo.");
                    }
                    break;

                case 8:
                    // Exit the system
                    System.out.println("👋 Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    // Handle invalid menu choice
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}
