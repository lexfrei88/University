package by.epam.selection.entity;

/**
 * @author Alex Aksionchik 12/3/2017
 * @version 0.1
 */
public class Certificate extends BaseEntity {

    private Subject subject;
    private int score;

    public Certificate(Long id, Subject subject, int score) {
        super(id);
        this.subject = subject;
        this.score = score;
    }

    public Certificate(Subject subject, int score) {
        this.subject = subject;
        this.score = score;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

        Certificate that = (Certificate) o;

        if (score != that.score) {
            return false;
        }
        return subject != null ? subject.equals(that.subject) : that.subject == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "subject=" + subject +
                ", score=" + score +
                '}';
    }

}
