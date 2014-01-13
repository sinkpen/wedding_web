/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

/**
 *
 * @author sinkpen
 */
public class Person {
    private String name;
    private boolean isGoing;
    private boolean isNotGoing;
    
    public Person() {}
    
    public Person(String name) {
        this.name = name;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * @return the isGoing
     */
    public boolean isGoing() {
        return isGoing;
    }

    /**
     * @param isGoing the isGoing to set
     */
    public void setIsGoing(boolean isGoing) {
        this.isGoing = isGoing;
    }

    /**
     * @return the isNotGoing
     */
    public boolean isNotGoing() {
        return isNotGoing;
    }

    /**
     * @param isNotGoing the isNotGoing to set
     */
    public void setIsNotGoing(boolean isNotGoing) {
        this.isNotGoing = isNotGoing;
    }
}
