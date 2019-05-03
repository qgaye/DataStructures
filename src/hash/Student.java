package hash;

/**
 * @author qgaye
 * @date 2019/03/28
 */
public class Student {

    private int grade;
    private int clazz;

    private String firstName;
    private String lastName;

    public Student(int grade, int clazz, String firstName, String lastName) {
        this.grade = grade;
        this.clazz = clazz;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;

        hash = hash * B + ((Integer) grade).hashCode();
        hash = hash * B + ((Integer) clazz).hashCode();
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Student another = (Student) obj;
        return this.grade == another.grade &&
                this.clazz == another.clazz &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }
}
