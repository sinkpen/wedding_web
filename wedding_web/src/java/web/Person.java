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
    private boolean going;
    
    public Person() {}
    
    public Person(String name) {
        this.name = name;
        this.going = true;
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
        return going;
    }

    /**
     * @param isGoing the isGoing to set
     */
    public void setGoing(boolean going) {
        this.going = going;
    }
}
