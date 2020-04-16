package org.itri.view.sleepingInfo.dao;

import org.zkoss.admin.util.Util;

import java.time.*;
import java.util.*;

public class SleepingInfoDao {
    static Integer[] issues = new Integer[Util.MONTHS.length];
    static Integer[] tasks = new Integer[Util.MONTHS.length];
    static String line = "Send payment today,Post Banner on official website, Meeting with BD team, Setting Github repository, Calling manager, Book flight for business trip";
    static String[] todoList = line.split(", ");
    static List<SleepingInfoRaw> recentActivityList = new LinkedList<>();
    static List<SleepingInfoRaw> activityList = new LinkedList<>();
    static List<SleepingInfo> progressSummary = new LinkedList<>();
    
    // Cards
    static String[] TITLES = {"Left sleep", "Right sleep", "Site", "Stand"};
    

    // DataGrid
    static String[] NAMES = {"Matthew", "Lucas", "John", "Peter", "Eric", "Kevin", "Grace", "Paul"};
    static String[] TASKS = {"draft design", "study GDPR", "release product", "layout pages", "main page", "help others", "packaging products"};
    static String[] PROJECTS = {"MedicalPro", "Real Home", "Digital Agency", "Elite Force"};
    
    static String[] POSTS = {"Left sleep", "Right sleep", "Site", "Stand"};

    static{
        for (int i = 0 ; i < Util.MONTHS.length ; i++) {
            issues[i] = Util.nextInt(0, 50);
        }

        for (int i = 0 ; i < Util.MONTHS.length ; i++) {
            tasks[i] = Util.nextInt(0, 50);
        }

        for (int i = 0 ; i < 4 ; i++) {
            SleepingInfoRaw activity = new SleepingInfoRaw();
            
            activity.setPost(POSTS[i]);
            activity.setCount(50);
//            activity.setTotalTime(toDate(LocalDateTime.now().plusDays(Util.nextInt(1, 10))));
            
//            activity.setOwner(NAMES[i]);
//            activity.setTask(TASKS[i]);
//            activity.setProject(PROJECTS[i]);
            activity.setCompletedTime(toDate(LocalDateTime.now().minusDays(Util.nextInt(1, 10))));
            recentActivityList.add(activity);
        }
        for (int i = 0 ; i < 5 ; i++) {
            SleepingInfoRaw activity = new SleepingInfoRaw();
            
//            activity.setPost(POSTS[i]);
//            activity.setValue(30);
//            activity.setTotalTime(toDate(LocalDateTime.now().plusDays(Util.nextInt(1, 10))));
            
            activity.setOwner(NAMES[i]);
            activity.setTask(TASKS[i]);
            activity.setProject(PROJECTS[i % PROJECTS.length]);
            activity.setDueDate(toDate(LocalDateTime.now().plusDays(Util.nextInt(1, 10))));
            activityList.add(activity);
        }
        initProgressSummary();
    }

    private static void initProgressSummary() {
        for (int i = 0 ; i < 4 ; i++){
            SleepingInfo p = new SleepingInfo(NAMES[i]);
            p.setTitle(TITLES[i]);
            p.setPersent(25);
            
            p.setIncompleted(Util.nextInt(10, 20));
            p.setCompleted(Util.nextInt(0, 10));
            progressSummary.add(p);
        }
    }

    public static Date toDate(LocalDateTime dateTime){
        return java.sql.Timestamp.valueOf(dateTime);
    }

    public static Integer[] queryIssues(){
        return issues;
    }

    public static Integer[] queryTasks(){
        return tasks;
    }

    public static String[] getTodoList() {
        return todoList;
    }

    public static List<SleepingInfoRaw> getRecentActivityList() {
        return recentActivityList;
    }

    public static List<SleepingInfoRaw> getActivityList() {
        return activityList;
    }

    public static List<SleepingInfo> getProgressSummary() {
        return progressSummary;
    }
}
