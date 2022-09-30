public class Converter {
    //зафинализированы переменные, в теории инф.отсутствует
    final double stepLength = 0.00075;
    final double stepkKal = 0.05;

    //рассчитывает дистанцию по шагам
     double totalDistance(int steps){
        return steps * stepLength; //убрал приведение типов
    }
    //рассчитывает килокаллории по шагам
    double totalKKal(int steps){
         return steps * stepkKal; //убрал приведение типов

    }
}
