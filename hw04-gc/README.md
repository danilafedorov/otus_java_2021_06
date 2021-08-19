Поиск оптимального хипа. Результаты запуска с разным 
размером хипа (По три запуска):

256MB
spend msec:15549, sec:15
spend msec:15533, sec:15
spend msec:15584, sec:15

1024MB
spend msec:9126, sec:9
spend msec:9123, sec:9
spend msec:9029, sec:9

1536MB
spend msec:8529, sec:8
spend msec:8482, sec:8
spend msec:8577, sec:8

2048MB
spend msec:8280, sec:8
spend msec:8301, sec:8
spend msec:8299, sec:8

1800MB

spend msec:7713, sec:7
spend msec:7774, sec:7
spend msec:7720, sec:7

1700MB
spend msec:7290, sec:7
spend msec:7285, sec:7
spend msec:7324, sec:7

1600MB
spend msec:8415, sec:8
spend msec:8443, sec:8
spend msec:8492, sec:8

1670MB
spend msec:8242, sec:8
spend msec:8146, sec:8
spend msec:8203, sec:8

1700MB
spend msec:7379, sec:7
spend msec:7282, sec:7
spend msec:7301, sec:7


Итог - оптимальный хип 1700MB


После оптимизации. В классе ru.calculator.Summator
Меняем все Integer на int + инициируем logValues с 
размером 6600000 (предотвращаем пересоздание массива).

1700MB
spend msec:1338, sec:1
spend msec:1355, sec:1
spend msec:1342, sec:1

1024MB
spend msec:1351, sec:1
spend msec:1363, sec:1
spend msec:1388, sec:1

400MB
spend msec:1111, sec:1
spend msec:1432, sec:1
spend msec:1145, sec:1

450MB
spend msec:1546, sec:1
spend msec:1533, sec:1
spend msec:1520, sec:1


600MB
spend msec:1332, sec:1
spend msec:1364, sec:1
spend msec:1338, sec:1

300MB
spend msec:1328, sec:1
spend msec:1727, sec:1
spend msec:1686, sec:1

Итог - размер хипа удалось уменьшить более чем в два раза. Оптимальный хип 
в диапазоне 400-600MB
