/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import java.util.List;

/**
 *
 * @author sinkpen
 */
public class Invite {
    private List<Person> people;
    private String code;
    private String requests;
    
    public Invite() {}
    
    public Invite(String code, List<Person> people) {
        this.code = code;
        this.people = people;
    }

    /**
     * @return the m_People
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * @param m_People the m_People to set
     */
    public void setPeople(List<Person> m_People) {
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

    /**
     * @return the requests
     */
    public String getRequests() {
        return requests;
    }

    /**
     * @param requests the requests to set
     */
    public void setRequests(String requests) {
        this.requests = requests;
    }
}
