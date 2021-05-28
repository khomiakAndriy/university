package com.university;

import com.university.dao.CommandUtil;
import com.university.dao.CommandUtilImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static CommandUtil lectorDao = CommandUtilImpl.getLectorDaoImpl();

    public static void main(String[] args) {
        showBeginScreen();
        int a = 0;
        a = getUserInput();

        while (a != 6) {
            switch (a) {
                case 1:

                    lectorDao.showDepartmentChief(new Scanner(System.in).nextLine());
                    showBeginScreen();
                    break;
                case 2:
                    System.out.println("Please enter department name:");
                    lectorDao.showDepartmentStatisticByDepName(new Scanner(System.in).nextLine());
                    showBeginScreen();
                    break;
                case 3:
                    System.out.println("Please enter department name:");
                    lectorDao.showAverageSalaryByDepartmentName(new Scanner(System.in).nextLine());
                    showBeginScreen();
                    break;
                case 4:
                    System.out.println("Please enter department name:");
                    lectorDao.showLectorsCountByDepartmentName(new Scanner(System.in).nextLine());
                    showBeginScreen();
                    break;
                case 5:
                    System.out.println("Please enter search keyword:");
                    lectorDao.searchByKeyword(new Scanner(System.in).nextLine());
                    showBeginScreen();
                    break;
                default:
                    System.out.println("Please make correct choice:");
                    break;
            }
            a = getUserInput();
        }
    }

    private static int getUserInput() {
        int a =0;
        try{
             a = new Scanner(System.in).nextInt();
        } catch (InputMismatchException ignore){
            System.out.println("You must enter a number");
        }
        return a;
    }

    private static void showBeginScreen() {
        System.out.println();
        System.out.println("Hello! Its university console app. Please choose a command:");
        System.out.println("Current departments name: Economical department, Law department, Medical department");
        System.out.println("1. Who is head of department");
        System.out.println("2. Show department statistic");
        System.out.println("3. Show the average salary for department");
        System.out.println("4. Show count of employee for department");
        System.out.println("5. Global search by keyword");
        System.out.println("6. End");
        System.out.println();
    }
}
