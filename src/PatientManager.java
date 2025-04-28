import java.util.*;
import java.io.*;

/**
 * This class manages the patient records, including adding, removing, displaying, and sorting patients,
 * as well as performing a binary search for patients by severity.
 */
public class PatientManager {
    
    // List to store patient records
    LinkedList<Patient> patientRecords = new LinkedList<>();
    
    // Flag to check if the patient list is sorted
    private boolean isSorted = false;

    /**
     * Checks if the patient list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    public boolean isListEmpty() {
        return patientRecords.isEmpty(); // O(1)
    }

    /**
     * Adds a new patient to the patient records list and saves the list to the file.
     * 
     * @param patient the patient to be added.
     */
    public void addPatient(Patient patient) {
        patientRecords.add(patient);  // O(1) for adding to the list (amortized time)
        isSorted = false;
        savePatientsToFile("patients.txt"); // O(n) (in the worst case, writing all patients)
    }

    /**
     * Removes a patient from the patient records list and saves the updated list to the file.
     * 
     * @param patient the patient to be removed.
     */
    public void removePatient(Patient patient) {
        patientRecords.remove(patient); // O(n) (in the worst case, linear time)
        isSorted = false;
        savePatientsToFile("patients.txt"); // O(n) (in the worst case, writing all patients)
    }

    /**
     * Sorts the patients by their severity in descending order.
     * (Selection Sort)
     */
    public void sortPatientsBySeverity() {
        int n = patientRecords.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) { // O(n^2) (selection sort)
                if (patientRecords.get(j).severity > patientRecords.get(maxIdx).severity) {
                    maxIdx = j;
                }
            }
            Collections.swap(patientRecords, i, maxIdx);  // O(1) for each swap
        }
        isSorted = true;
        savePatientsToFile("patients.txt"); // O(n)
    }

    /**
     * Searches for patients with the specified severity using binary search.
     * 
     * @param severity the severity level to search for.
     * @return a list of patients that match the specified severity.
     */
    public List<Patient> binarySearchBySeverity(int severity) {
        List<Patient> foundPatients = new ArrayList<>();
        
        // Ensure the list is sorted before performing the search
        if (!isSorted) {
            System.out.println("❌ List is not sorted! Please use Option 3 first.");
            return foundPatients; // O(1) if not sorted, return empty list
            
            
            
        }

        int left = 0;
        int right = patientRecords.size() - 1;
        int foundIndex = -1;

        // Perform binary search to find the first matching patient
        while (left <= right) { // O(log n) for binary search
            int mid = left + (right - left) / 2;
            int midSeverity = patientRecords.get(mid).severity;

            if (midSeverity == severity) {
                foundIndex = mid;
                break;
            } else if (midSeverity > severity) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // If a patient is found, check adjacent patients with the same severity
        if (foundIndex != -1) {
            foundPatients.add(patientRecords.get(foundIndex));

            // Check patients to the left (O(n) in the worst case)
            int leftNeighbor = foundIndex - 1;
            while (leftNeighbor >= 0 && patientRecords.get(leftNeighbor).severity == severity) {
                foundPatients.add(patientRecords.get(leftNeighbor));
                leftNeighbor--;
            }

            // Check patients to the right (O(n) in the worst case)
            int rightNeighbor = foundIndex + 1;
            while (rightNeighbor < patientRecords.size() && patientRecords.get(rightNeighbor).severity == severity) {
                foundPatients.add(patientRecords.get(rightNeighbor));
                rightNeighbor++;
            }
        } else {
            System.out.println("❌ No patients found with severity " + severity); // O(1)
        }
        return foundPatients;
    }

    /**
     * Counts the number of patients with severity above a given threshold, starting from a specific index.
     * 
     * @param threshold the severity threshold.
     * @param index the starting index for the count.
     * @return the number of patients with severity greater than the threshold.
     */
    public int countSeverePatients(int threshold, int index) {
        if (index >= patientRecords.size()) return 0; // O(1) check
        int count = (patientRecords.get(index).severity > threshold) ? 1 : 0;
        return count + countSeverePatients(threshold, index + 1);   // O(n) due to recursion
    }

    /**
     * Displays all patients in the patient records list.
     */
    public void displayPatients() {
        if (patientRecords.isEmpty()) {
            System.out.println("No patients available."); // O(1)
            return;
        }
        for (Patient p : patientRecords) { // O(n)
            System.out.println(p);
        }
    }

    /**
     * Saves the patient records to a file.
     * 
     * @param filename the name of the file where patients will be saved.
     */
    public void savePatientsToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Patient patient : patientRecords) {  // O(n)
                bw.write(patient.name + " - " + patient.condition + " - " + patient.severity);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error writing to the file: " + e.getMessage()); // O(1)
        }
    }
}
