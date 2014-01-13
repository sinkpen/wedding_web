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
public class Invite {
    private Person [] people;
    private String code;
    
    public Invite() {}
    
    public Invite(String code, Person [] people) {
        this.code = code;
        this.people = people;
    }

    /**
     * @return the m_People
     */
    public Person[] getPeople() {
        return people;
    }

    /**
     * @param m_People the m_People to set
     */
    public void setPeople(Person[] m_People) {
        this.people = m_People;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }
}
