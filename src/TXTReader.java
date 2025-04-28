import java.io.*;

/**
 * The TXTReader class handles the reading and writing of patient data from and to a text file.
 * It is responsible for loading patient information into the system from a file and saving updated
 * patient data back to the file.
 */
public class TXTReader {

    /**
     * Loads patients from a specified text file and adds them to the provided patient manager and hospital queue.
     * The patient data in the file should be formatted with the following structure: 
     * "Name - Condition - Severity" for each patient.
     *
     * @param filename The name of the text file containing the patient data.
     * @param patientManager The patient manager where the patients will be added.
     * @param hospitalQueue The hospital queue where the patients will be added based on severity.
     */
    public static void loadPatients(String filename, PatientManager patientManager, HospitalQueue hospitalQueue) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) { // O(n)
                String[] data = line.split(" - "); // Splitting the data by " - "
                if (data.length == 3) {
                    String name = data[0].trim();
                    String condition = data[1].trim();
                    int severity = Integer.parseInt(data[2].trim());

                    // Create a new patient and add to the manager and hospital queue
                    Patient p = new Patient(name, condition, severity);// O(1) amortized for each add
                    patientManager.addPatient(p); 
                    hospitalQueue.addPatient(p);
                }
            }
            System.out.println("✅ Patients loaded from Hospital Database.");
        } catch (IOException e) {
            System.out.println("❌ Error reading the file: " + e.getMessage()); // O(1)
        }
    }
}
