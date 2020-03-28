//package code;
//
//import code.College;
//import code.CollegeType;
//import code.Student;
//import code.TijdBlok;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Calendar;
//import java.util.Date;
//
//class StudentTest {
//    private Student st1;
//    private Student st2;
//    private Student st3;
//
//    private College college1;
//
//    private Calendar cal1;
//    private Calendar cal2;
//
//    private Date date1;
//    private Date date2;
//
//    @BeforeEach
//    public void settingStuff(){
//        st1 = new Student(1, "harry@hu.nl", "123", "Harry");
//        st2 = new Student(2, "henk@hu.nl", "123", "Henk");
//        st3 = new Student(3, "iris@hu.nl", "123", "Iris");
//
//        college1 = new College();
//        college1.setNaam("OOAD");
//        college1.setTijd(TijdBlok.ochtendBlok);
//        college1.setType(CollegeType.werkCollege);
//
//        cal1 = Calendar.getInstance();
//        cal1.set(Calendar.YEAR, 2020);
//        cal1.set(Calendar.MONTH, 1);
//        cal1.set(Calendar.DAY_OF_MONTH, 2);
//        date1 = cal1.getTime();
//
//        cal2 = Calendar.getInstance();
//        cal2.set(Calendar.YEAR, 2020);
//        cal2.set(Calendar.MONTH, 1);
//        cal2.set(Calendar.DAY_OF_MONTH, 2);
//        date2 = cal2.getTime();
//    }
//
//    @Test
//    public void everythingEmpty(){
//
//    }
//
//
//}