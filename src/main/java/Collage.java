import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import sun.security.pkcs11.Secmod;

import java.util.ArrayList;
import java.util.Random;

public class Collage {

    private Random rand = new Random();

    public Collage(){
        //initializing Everything
        ArrayList<Student> students = new ArrayList<Student>();

        Student s1 = new Student("Codey ",44,new DateTime(),"Codey Estrada");   students.add(s1);
        Student s2 = new Student("Fabien",22,new DateTime(),"Whitfield");       students.add(s2);
        Student s3 = new Student("Horton",54,new DateTime(),"Mullins.H");       students.add(s3);
        Student s4 = new Student("Corben",31,new DateTime(),"Corben Shepard");  students.add(s4);
        Student s5 = new Student("Arlene",24,new DateTime(),"Lennon.A");        students.add(s5);

        ArrayList<Module> modules = new ArrayList<Module>();
        for (int i = 0; i< 10 ;i++) {
            modules.add(new Module(("ct" + rand.nextInt(9999))));
        }
        ArrayList<Course> courses = new ArrayList<Course>();
        for (int i = 0; i< 5 ;i++) {
            courses.add(new Course(("courses:" + rand.nextInt(999)),new DateTime(),new DateTime()));
        }
        Shuffle shuff = new Shuffle(0,4);


        //filling the module and course
        for (int i = 0; i< 10 ;i++) {
            int set[] = shuff.setOfRandomNum(2);
            Module temp = modules.get(i);
            temp.addStudent(students.get(set[0]));
            temp.addStudent(students.get(set[1]));
            modules.set(i,temp);
        }

        shuff.newRange(0,9);
        for (int i = 0; i < 5; i++){
            int set[] = shuff.setOfRandomNum(4);
            Course temp = courses.get(i);

            for(int j =0; j<4; j++){
                temp.addModule(modules.get(set[j]));
                Module x = modules.get(set[j]);
                x.addCourse(temp);
                modules.add(set[j], x);
            }
            temp.addStudentsFromModules();
            courses.set(i,temp);
        }

        printCourse(courses);

        printStudent(students);
    }

    public void printModule(ArrayList<Module> modules){
        for(int i = 0; i < modules.size();i++){
            Module temp = modules.get(i);
            System.out.println("ID|Module Name");
            System.out.println(""+temp.getId()+": "+temp.getName());
            /*printStudent(temp.getStudents());*/
        }
    }

    public void printStudent(ArrayList<Student> students){
        System.out.println("ID|Student Name\t\tAge");
        for(int i = 0; i<students.size();i++){
            Student temp = students.get(i);
            System.out.println(""+temp.getId()+": "+temp.getName()+" \t\t\t"+temp.getAge());
            printModule(temp.getModules());
        }
        System.out.println("\n");
    }

    public void printCourse(ArrayList<Course> courses){
        for(int i = 0; i < courses.size();i++){
            System.out.println("\n"+"ID|Name");
            Course temp = courses.get(i);
            System.out.println(""+temp.getCourseId()+": "+temp.getName() +"\n");

            ArrayList<Student> students = temp.getStudents();
            System.out.println("ID|Student Name\t\tAge");
            for(int j = 0; j<students.size();j++){
                Student studentTemp = students.get(j);
                System.out.println(""+studentTemp.getId()+": "+studentTemp.getName()+" \t\t"+studentTemp.getAge());
            }
            System.out.println("\n");

            printModule(temp.getModules());
            System.out.println("______________________________________________");
        }
    }
}
