Неверное решение - по условию, нужно использовать mutex (т.е. любой объект, например new Object), а также методы wait и notify для обеспечения поочерёдной работы потоков. 