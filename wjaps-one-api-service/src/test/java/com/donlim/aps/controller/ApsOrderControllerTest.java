package com.donlim.aps.controller;

import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.test.BaseUnitTest;
import com.changhong.sei.core.util.JsonUtils;
import com.donlim.aps.connector.ScmConnector;
import com.donlim.aps.entity.ScmXbDelivery;
import com.donlim.aps.service.ApsOrderService;
import com.donlim.aps.service.ScmXbDeliveryService;
import com.donlim.aps.util.CompanyEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现功能: Hello 单元测试
 */
public class ApsOrderControllerTest extends BaseUnitTest {
    @Autowired
    private ApsOrderController apsOrderController;
    @Test
    public void initApsOrder() {
//测试一下上传
        apsOrderController.updateOrderStatistic();
     /*   long l1 = System.currentTimeMillis();
        ResultData<String> result = apsOrderController.initApsOrder();
        long l2 = System.currentTimeMillis();*/
    }


    class Student {
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }

        public int getChineseScore() {
            return chineseScore;
        }

        public void setChineseScore(int chineseScore) {
            this.chineseScore = chineseScore;
        }

        public int getMathScore() {
            return mathScore;
        }

        public void setMathScore(int mathScore) {
            this.mathScore = mathScore;
        }

        public int getEnglishScore() {
            return englishScore;
        }

        public void setEnglishScore(int englishScore) {
            this.englishScore = englishScore;
        }

        private String name;
        private String className;
        private LocalDate birthDate;
        private int chineseScore;
        private int mathScore;
        private int englishScore;

        // 构造函数和get方法

        // 计算总分
        public int getTotalScore() {
            return chineseScore + mathScore + englishScore;
        }

        // 判断是否3科不到60
        public boolean isFailing() {
            return chineseScore < 60 || mathScore < 60 || englishScore < 60;
        }

        // 判断是否3科达到90
        public boolean isExcellent() {
            return chineseScore >= 90 && mathScore >= 90 && englishScore >= 90;
        }
    }


    class ClassInfo {
        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<Student> getStudentList() {
            return studentList;
        }

        public void setStudentList(List<Student> studentList) {
            this.studentList = studentList;
        }

        private String className;
        private List<Student> studentList;

        // 构造函数和get方法

        // 计算语文平均分
        public double getChineseAverage() {
            return studentList.stream().mapToInt(Student::getChineseScore).average().orElse(0.0);
        }

        // 获取语文分数大于90的人数
        public long getChineseExcellentCount() {
            return studentList.stream().filter(s -> s.getChineseScore() >= 90).count();
        }

        // 计算总分平均分
        public double getTotalAverage() {
            return studentList.stream().mapToInt(Student::getTotalScore).average().orElse(0.0);
        }

        // 获取3科不到60的人数
        public long getFailingCount() {
            return studentList.stream().filter(Student::isFailing).count();
        }

        // 获取3科达到90的人员名单
        public List<String> getExcellentStudents() {
            return studentList.stream().filter(Student::isExcellent).map(Student::getName).collect(Collectors.toList());
        }
    }

    @Test
    public void sayHello() {
        List<ClassInfo> classInfoList = new ArrayList<>();

 //帮我生成一些数据3个班级，每个班级10个学生，每个学生3科成绩
        for (int i = 1; i < 4; i++) {
            ClassInfo classInfo = new ClassInfo();
            classInfo.setClassName("班级" + i);
            List<Student> studentList = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                Student student = new Student();
                student.setCode("学号" + j);
                student.setName("学生" + j);
                student.setClassName("班级" + i);
                student.setBirthDate(LocalDate.now());
                student.setChineseScore(60 + j);
                student.setMathScore(60 + j);
                student.setEnglishScore(60 + j);
                studentList.add(student);
            }
            if(i==1){
                Student student = new Student();
                student.setCode("学号" + 11);
                student.setName("学生" + 11);
                student.setClassName("班级" + i);
                student.setBirthDate(LocalDate.now());
                student.setChineseScore(90);
                student.setMathScore(90);
                student.setEnglishScore(90);
                studentList.add(student);
            }if(i==2){
                //不合格的学生
                Student student = new Student();
                student.setCode("学号" + 12);
                student.setName("学生" + 12);
                student.setClassName("班级" + i);
                student.setBirthDate(LocalDate.now());
                student.setChineseScore(59);
                student.setMathScore(59);
                student.setEnglishScore(59);
                studentList.add(student);
            }
            classInfo.setStudentList(studentList);
            classInfoList.add(classInfo);
        }



        // 求出语文平均分最高的班级，以及语文成绩大于90分的人数
        ClassInfo classWithMaxChineseAverage = classInfoList.stream()
                .max(Comparator.comparingDouble(ClassInfo::getChineseAverage))
                .orElse(null);
        long chineseExcellentCount = classWithMaxChineseAverage.getChineseExcellentCount();
        System.out.println(String.format("班级 %s 的语文平均分最高，有 %d 个学生的语文成绩大于90分。", classWithMaxChineseAverage.getClassName(), chineseExcellentCount));

        // 求出总分平均分最差的班级，以及3科不到60的人数
        ClassInfo classWithMinTotalAverage = classInfoList.stream()
                .min(Comparator.comparingDouble(ClassInfo::getTotalAverage))
                .orElse(null);
        long failingCount = classWithMinTotalAverage.getFailingCount();
        System.out.println(String.format("班级 %s 总分平均分最差，在该班级中有 %d 个学生的3科不到60分。", classWithMinTotalAverage.getClassName(), failingCount));

        //求出总分平均分最高的班级，以及3科达到90分的人员名单。
        ClassInfo classWithMaxTotalAverage = classInfoList.stream()
                .max(Comparator.comparingDouble(ClassInfo::getTotalAverage))
                .orElse(null);
        List<String> excellentStudents = classWithMaxTotalAverage.getExcellentStudents();
        System.out.println(String.format("班级 %s 总分平均分最高，有 %d 个学生的3科成绩都达到90分,请单是 %s 。", classWithMaxTotalAverage.getClassName(), excellentStudents.size(),excellentStudents.toString()));

    }
}
