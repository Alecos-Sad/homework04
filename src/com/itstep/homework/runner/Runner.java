package com.itstep.homework.runner;

import com.itstep.homework.task.Homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Runner class, don't change it!
 *
 * @author kaa
 */
public class Runner {

    private final String TASK_PREFIX = "task";

    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Укажи номер задания: ");
            try {
                runTask(Integer.parseInt(bufferedReader.readLine()));
            } catch (NumberFormatException ex) {
                System.out.println("Возникла проблема преобразований, введи число");
                //System.out.println("Error: " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.out.println("Возникла проблема чтения из консоли");
            //System.out.println("Error: " + ex.getMessage());
        }
    }

    private void runTask(int taskNumber) {
        Method[] methodArray = Homework.class.getDeclaredMethods();
        List<Integer> taskNumberList = Arrays.stream(methodArray)
                .filter(item -> item.getName().startsWith(TASK_PREFIX))
                .map(item -> Integer.parseInt(item.getName().replace(TASK_PREFIX, "")))
                .collect(Collectors.toList());
        if (taskNumberList.contains(taskNumber)) {
            try {
                Homework.class.getMethod(TASK_PREFIX + taskNumber).invoke(new Homework());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                System.out.println("Произошла невиданная доселе бесятина");
                //System.out.println("Error: " + ex.getMessage());
            }
        } else {
            System.out.println("Задания с таким номером нет");
        }
    }
}
