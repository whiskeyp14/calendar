package ddp2.Calendar;

/** calendar 
 * show calendar by year and Month
 * print to display
*/
public class Calendar {
    // initiate
    private int month, year;
    private int[] mdays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private String[] months = {" ", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    public Calendar(int year) {
        this.year = year;
    }

    public Calendar(int year, int month) {
        this.month = month;
        this.year = year;
    }

    public void print(){
        // flag
        if (this.month == 0){
            // print all month in a year
            // calculate the day from 1800 to n year
            // + 3 for normalize the first day of January 1800
            int dayByYear = 3 + getDay();

            // change the February to 29 if detect the leap year
            if (leapYear()) this.mdays[2] = 29;

            // print calendar
            for (int i = 1; i <= 12; i++){
                // select month
                this.month = i;

                // calculate the first day
                int dayOne = (dayByYear + getMonthDay()) % 7;

                // print calendar format
                System.out.printf("%s %d\n", months[month], year);
                System.out.println(" S  M  T  W  T  F  S");

                // print the day
                for (int j = 0; j < dayOne; j++) {
                    System.out.print("   ");
                }
                for (int j = 1; j <= this.mdays[month]; j++) {
                    System.out.printf("%2d ", j);
                    // print as new week
                    if (((j + dayOne) % 7 == 0) || (j == this.mdays[month]))
                        System.out.println();
                }
                System.out.println();
            }
            // normalize
            this.month = 0;
        }

        else {
            // print by month and year
            // calculate the first day
            // + 3 for normalize the first day of January 1800
            int dayOne = (3 + getDay() + getMonthDay()) % 7;
            if (leapYear()) this.mdays[2] = 29;

            // print Calendar format
            System.out.format("%s %d\n", months[month], year);
            System.out.println(" S  M  T  W  T  F  S");

            // print the day
            for (int i = 0; i < dayOne; i++) {
                System.out.print("   ");
            }
            for (int i = 1; i <= this.mdays[month]; i++) {
                System.out.printf("%2d ", i);
                if (((i + dayOne) % 7 == 0) || (i == this.mdays[month])) System.out.println();
            }
        }
    }

    public boolean leapYear() {
        // return true if n year is a leapYear
        return (this.year % 4) == 0 && ((this.year % 100) != 0 || (this.year % 400) == 0);
    }

    private int getDay() {
        int day, i;

        day = 0;
        // calculate day from 1800 to n year
        for (i = 1800; i < this.year; i++) {
            if (leapYear()) day++;
            day += 365;
        }
        return day;
    }

    private int getMonthDay() {
        int day, i;

        day = 0;
        // calculate day from day 0 to the end of n month
        for (i = 1; i < this.month; i++) {
            if ((i == 2) && leapYear()) day++;
            else day += this.mdays[i];
        }
        return day;
    }
}