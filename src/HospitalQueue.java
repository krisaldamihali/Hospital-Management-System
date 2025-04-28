import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * The HospitalQueue class manages a queue of patients, prioritizing them based on their severity level.
 * The patient with the highest severity is treated first. This class provides methods to add, 
 * treat (remove), and check the status of the queue.
 */
public class HospitalQueue {

    /**
     * A priority queue to store patients, sorted by severity in descending order.
     * The patient with the highest severity will be treated first.
     */
    PriorityQueue<Patient> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> -p.severity));

    /**
     * Adds a patient to the hospital queue.
     * The patient will be placed in the queue based on their severity.
     *
     * @param patient The patient to be added to the queue.
     */
    public void addPatient(Patient patient) {
        priorityQueue.offer(patient); // O(log n)
    }

    /**
     * Treats (removes) the patient with the highest severity from the queue.
     * This method returns the treated patient, or null if the queue is empty.
     *
     * @return The treated patient or null if the queue is empty.
     */
    public Patient treatPatient() {
        if (!priorityQueue.isEmpty()) {
            return priorityQueue.poll(); // O(log n)
        }
        return null;
    }

    /**
     * Checks if the hospital queue is empty.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return priorityQueue.isEmpty(); // O(1)
    }
}
