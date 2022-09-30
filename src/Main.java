import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0){
            //заменил if-else на switch. В теории о нём ничего не сказано
            //тернарный оператор неудобен в данной программе, если речь о лаконичности кода
            switch (userInput){
                case 1 : stepTracker.saveDaySteps(); break;
                case 2 : stepTracker.outputMonthStat(); break;
                case 3 : stepTracker.saveGoalSteps(); break;
                default: System.out.println("Такого номера пункта не существует. Повторите ввод:");
            }
            printMenu();
            userInput = scanner.nextInt();
        }
        System.out.println("Выход из программы!");
    }
    //метод вывода меню в цикле
    public static void printMenu(){
        System.out.println("***** МЕНЮ *****"); //убрал символ переноса строки, отступ был сделан специально)))
        System.out.println("Введите номер пункта, что вы хотите сделать:");
        System.out.println( "1 - Ввести количество шагов за день. \n" +
                            "2 - Напечатать статистику за месяц. \n" +
                            "3 - Изменить цель по количеству шагов за день. \n" +
                            "0 - Выйти из приложения");

    }
}
