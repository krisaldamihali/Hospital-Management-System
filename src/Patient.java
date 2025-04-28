/**
 * The Patient class represents a patient in the hospital management system.
 * It stores information about the patient's name, medical condition, and severity of the condition.
 */
public class Patient {

    /**
     * The name of the patient.
     */
    String name;

    /**
     * The medical condition of the patient.
     */
    String condition;

    /**
     * The severity level of the patient's condition.
     * This is an integer value where a higher number indicates more severity.
     */
    int severity;

    /**
     * Constructs a new Patient object with the specified name, condition, and severity.
     *
     * @param name The name of the patient.
     * @param condition The medical condition of the patient.
     * @param severity The severity level of the patient's condition.
     */
    public Patient(String name, String condition, int severity) {
        this.name = name;
        this.condition = condition;
        this.severity = severity;
    }

    /**
     * Returns a string representation of the patient, including their name, condition, and severity.
     *
     * @return A string representation of the patient's details.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Condition: " + condition + ", Severity: " + severity;
    }
}
