import java.util.Stack;

/**
 * The DischargeManager class is responsible for managing the discharge of patients 
 * and maintaining a stack of discharged patients for the ability to undo discharges.
 */
public class DischargeManager {

    // Stack to store the discharged patients
    Stack<Patient> dischargedStack = new Stack<>();

    /**
     * Discharges a patient by pushing them onto the discharged stack.
     *
     * @param patient the patient to be discharged
     */
    public void dischargePatient(Patient patient) {
        dischargedStack.push(patient); // O(1)
    }

    /**
     * Undoes the last discharge by popping the most recently discharged patient 
     * from the stack and returning it.
     * 
     * @return the patient that was discharged, or null if the stack is empty
     */
    public Patient undoDischarge() {
        if (!dischargedStack.isEmpty()) {
            return dischargedStack.pop();  // O(1)
        }
        return null;
    }

    /**
     * Checks if there are any discharged patients.
     * 
     * @return true if the discharged stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return dischargedStack.isEmpty(); // O(1)
    }
}
