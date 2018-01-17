package by.epam.selection.entity;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class Faculty extends BaseEntity {

    private String facultyName;
    private int studentLimit;

    public Faculty(Long facultyId) {
        super(facultyId);
    }

    public Faculty(Long facultyId, String facultyName, int studentLimit) {
        super(facultyId);
        this.facultyName = facultyName;
        this.studentLimit = studentLimit;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Faculty faculty = (Faculty) o;

        if (studentLimit != faculty.studentLimit) {
            return false;
        }
        return facultyName != null ? facultyName.equals(faculty.facultyName) : faculty.facultyName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (facultyName != null ? facultyName.hashCode() : 0);
        result = 31 * result + studentLimit;
        return result;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "ID=" + getId() +
                ", facultyName='" + facultyName + '\'' +
                ", studentLimit=" + studentLimit +
                "}";
    }

}
