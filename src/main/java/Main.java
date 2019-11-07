import hibernate.test.HibernateUtil;
import hibernate.test.dto.entity.EmployeeEntity;
import org.hibernate.Session;

public class Main {

    public static void main(final String[] args) throws Exception {
        Session sessionOne = HibernateUtil.getSession();
        sessionOne.beginTransaction();

        // create a new employee instance
        EmployeeEntity emp = new EmployeeEntity();
        emp.setLastName("embouazza");
        emp.setFirstName("Rachid");
        emp.setEmail("embouazza_rachid@yahoo.fr");

        // save employee
        sessionOne.save(emp);

        // store the employee id generated for future use
        Integer empId = emp.getEmployeeId();
        sessionOne.getTransaction().commit();

        /**************************************************************************************************************/

        // let's open a new session to test load() methods
        Session sessionTwo = HibernateUtil.getSession();
        sessionTwo.beginTransaction();

        // first load() method example
        EmployeeEntity emp1 = (EmployeeEntity) sessionTwo.load(EmployeeEntity.class, empId);
        System.out.println(emp1.getLastName() + " - " + emp1.getFirstName());

        // Let's verify the entity name
        System.out.println(sessionTwo.getEntityName(emp1));

        sessionTwo.getTransaction().commit();

        /**************************************************************************************************************/
        Session sessionThree = HibernateUtil.getSession();
        sessionThree.beginTransaction();

        // second load() method example
        EmployeeEntity emp2 = (EmployeeEntity) sessionThree.load("hibernate.test.dto.entity.EmployeeEntity", empId);
        System.out.println(emp2.getLastName() + " - " + emp2.getFirstName());

        sessionThree.getTransaction().commit();

        /**************************************************************************************************************/
        Session sessionFour = HibernateUtil.getSession();
        sessionFour.beginTransaction();

        //third load() method example
        EmployeeEntity emp3 = new EmployeeEntity();
        sessionFour.load(emp3, empId);
        System.out.println(emp3.getLastName() + " - " + emp3.getFirstName());

        sessionFour.getTransaction().commit();
        HibernateUtil.shutdown();

    }
}
