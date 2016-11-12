package db_actions.controller;

import db_actions.model.Group;
import db_actions.model.Lesson;
import db_actions.model.Prepod;
import db_actions.model.Student;

import java.util.List;

/**
 * Created by Jack on 12.11.2016.
 */
public interface IjdbsActions<T> {

    List<T> getAll(T t);

    T getOne(T t);

    void updateInfo();

    List<Student> getStudentsByGroup(Group group);

    List<Group> getGroupsWhoLearnMath(Lesson math);

    List<Group> getGroupsWithAllLessons();

    Prepod getPrepodWithHighExp();

    Prepod getPrepodWithLowhExp();

    List<Prepod> gerPrepodsWithMore3yearsExp();

    List<Lesson> getGymanitaryLessons();

    double getAvaragePoint(Lesson fizika, Group group);

    double getAvaragePointAll(Lesson fizika, List<Group> groups);

    Group showGroupWithMore3WhoLernFilosofy();


}
