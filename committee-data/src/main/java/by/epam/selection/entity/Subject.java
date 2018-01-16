package by.epam.selection.entity;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class Subject extends BaseEntity {

    private String subjectName;

    public Subject() {
    }

    public Subject(Long id) {
        super(id);
    }

    public Subject(Long subjectId, String subjectName) {
        super(subjectId);
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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

        Subject subject = (Subject) o;

        return subjectName != null ? subjectName.equals(subject.subjectName) : subject.subjectName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subjectName != null ? subjectName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                '}';
    }

}
