import java.util.Scanner;

public class StepTracker {

    Scanner scanner = new Scanner(System.in);
    int goalSteps = 10000;
    Month[] dataDayInMonth; // массив, в котором хранятся объекты-месяцы

    // конструктор счетчика при создании объекта создается массив на 12 месяцев
    public StepTracker(){
        dataDayInMonth = new Month[12];
        for (int i = 0; i < dataDayInMonth.length; i++){
            dataDayInMonth[i] = new Month();
        }
    }

    class Month {
        // даже не помню зачем создавал конструктор ... видимо творческий порыв был, что-то в него накрутить
        // удалил конструктор
        int[] days = new int[30]; //массив с номерами дней
    }
//СОХРАНЯЕТ ПОЛЬЗОВАТЕЛЬСКИЙ ВВОД ШАГОВ
    public  void saveDaySteps(){
        // убрал инициализацию переменных
        // на первой стадии была задумка отлавливать вывод значения переменной ошибку в коде, когда переменной ничего
        // не присваивается. -1 - просто как индикатор. По ходу потом не понадобилось. Согласен. мой косяк ))) Грязно)
        int userInputMonth;
        int userInputDay;
        int userInputSteps;

        // ВВОД МЕСЯЦА
        while(true){
            System.out.println("Введите номер месяца: (значения 0-11)");
            userInputMonth = scanner.nextInt();
            if (userInputMonth < 0  || userInputMonth > 11){
                System.out.println("Введено некорректное значение");
                System.out.println("Повторите попытку ввода\n");
            } else {
                break;
            }
        }
        // ВВОД ДНЯ
        while(true){
            System.out.println("Введите день месяца: (значения 1-30)");
            userInputDay = scanner.nextInt();
            if (userInputDay < 1  || userInputDay > 30){
                System.out.println("Введено некорректное значение");
                System.out.println("Повторите попытку ввода\n");
            } else {
                break;
            }
        }
        // ВВОД ШАГОВ
        while(true){
            System.out.println("Введите количество шагов");
            userInputSteps = scanner.nextInt();
            if (userInputSteps < 0){
                System.out.println("Введено некорректное значение");
                System.out.println("Повторите попытку ввода\n");
            } else {
                break;
            }
        }
        //ЗАПИСЬ ЗНАЧЕНИЯ
        dataDayInMonth[(userInputMonth)].days[(userInputDay-1)] = userInputSteps;
        System.out.println("Запись прошла успешно!\n");
    }
//ВЫВОДИТ СТАТИСТИКУ ЗА МЕСЯЦ
    public void outputMonthStat(){
        int userInputMonth;
        while(true){
            System.out.println("За какой месяц вывести статистику? (значения 0-11)");
            userInputMonth = scanner.nextInt();
            if(userInputMonth < 0 || userInputMonth > 11){
                System.out.println("\nНекорректный ввод. Месяц с таким номером отсутствует.\nПовторите ввод:");
            } else {
                break;
            }
        }
        System.out.println("Статистика за " + userInputMonth + " месяц:");
        System.out.println("---------------------------------------------------");
        System.out.println("Количество пройденных шагов по дням:");
        allStepsMonth(userInputMonth);
        System.out.println("\n----------------------------------------------------");
        System.out.println("Общее количество шагов за месяц: " + totalStepsMonth(userInputMonth));
        System.out.println("----------------------------------------------------");
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps(userInputMonth));
        System.out.println("----------------------------------------------------");
        System.out.println("Среднее количество шагов: " + mediumSteps(userInputMonth));
        System.out.println("----------------------------------------------------");
        Converter converter = new Converter();
        System.out.println("Пройденная дистанция (в км): " + converter.totalDistance(totalStepsMonth(userInputMonth)));
        System.out.println("----------------------------------------------------");
        System.out.println("Количество сожжённых килокалорий: " + converter.totalKKal(totalStepsMonth(userInputMonth)));
        System.out.println("----------------------------------------------------");
        System.out.println("Лучшая серия: " + bestSeries(userInputMonth) + " (цель: " + goalSteps + " шагов)");
        System.out.println("----------------------------------------------------");
    }
// ВЫВОДИТ СТАТИСТИКУ ШАГИ - ДЕНЬ ВНУТРИ МЕСЯЦА
    public void allStepsMonth(int numMonth){
        for (int i = 0; i < dataDayInMonth[numMonth].days.length ; i++) {
            // убрал добавление запятой на последнем элементе массива
            if(i == (dataDayInMonth[numMonth].days.length-1)){
                System.out.print((i + 1) + " день: " + dataDayInMonth[numMonth].days[i]);
            } else {
                System.out.print((i + 1) + " день: " + dataDayInMonth[numMonth].days[i] + ", ");
            }
        }
    }
// РАССЧИТЫВАЕТ ОБЩЕЕ КОЛИЧЕСТВО ШАГОВ ЗА МЕСЯЦ
    public int totalStepsMonth(int numMonth){
        int total = 0;
        for (int i = 0; i < dataDayInMonth[numMonth].days.length; i++) {
            total = total + dataDayInMonth[numMonth].days[i];
        }
        return total;
    }
//РАССЧИТЫВАЕТ МАКС КОЛ-ВО ШАГОВ
    public int maxSteps(int numMonth){
        //заменил -1 на 0, если я правильно понял замечание
        //изначально по-моему я хотел -1 использовать как маркер для проверки корректности присвоения данных
        int max = 0;
        for (int i = 0; i < dataDayInMonth[numMonth].days.length; i++) {
            if (dataDayInMonth[numMonth].days[i] > max){
                max = dataDayInMonth[numMonth].days[i];
            }
        }
        return max;
    }
//РАССЧИТЫВАЕТ СРЕДНЕЕ ЗНАЧЕНИЕ ШАГОВ
    public int mediumSteps(int numMonth){
        return totalStepsMonth(numMonth)/30;
    }
//РАССЧИТЫВАЕТ ЛУЧШУЮ СЕРИЮ
    public int bestSeries(int numMonth) {
        /* Откровенно сказать, Кирилл, я запарился и невнимательно прочитал условие
        пропустил, что нужно вывести именно подряд идущие дни, оттуда и выдал такое решение :) */

        int totalBestDays = 0; // счётчик лучшей серии
        int countGoalDays = 0; // счётчик дней когда результат больше и равен цели
        for (int i = 0; i < dataDayInMonth[numMonth].days.length; i++) {
            if (dataDayInMonth[numMonth].days[i] >= goalSteps) {    // если факт больше цели
                countGoalDays++; // счетчик цели ++
                if (countGoalDays > totalBestDays) { // если счетчик цели больше чем счетчик лучшей серии
                    totalBestDays = countGoalDays; // записываем значение
                }
            } else { // если факт меньше цели, обнуляем счетчик цели
                countGoalDays = 0;
            }
        }
        return totalBestDays; // возврат лучшей серии
    }
//СОХРАНЯЕТ ПОЛЬЗОВАТЕЛЬСКИЙ ВВОД ЦЕЛЕВОГО КОЛ-ВА ШАГОВ
    public void saveGoalSteps(){
        while(true){
            System.out.println("Введите целевое количество шагов");
            // убрал временную переменную заменив на ранее объявленную
            goalSteps = scanner.nextInt();
            if (goalSteps < 0) {
                System.out.println("Введено некорректное значение");
                System.out.println("Повторите попытку ввода\n");
            } else {
                System.out.println("Запись прошла успешно!");
                System.out.println("Новое целевое значение = " + goalSteps);
                break;
            }
        }
    }
}
