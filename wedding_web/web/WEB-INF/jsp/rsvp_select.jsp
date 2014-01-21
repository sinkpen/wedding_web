<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="rsvp_box">
<h1>RSVP</h1>

<div class="error_message">${errorMessage}</div>

<form:form action="rsvp_confirm.htm" method="POST" modelAttribute="invite">

    <c:forEach items="${invite.people}" var="person" varStatus="status">     
    <fieldset  style="padding: 10px 10px 10px 10px; margin-bottom: 10px">
        <legend>${person.name}</legend>
        <input type="hidden" name="people[${status.index}].name" value="${person.name}" />
        <label>
            <input type="checkbox" name="people[${status.index}].isGoing"/>
            Accept with Pleasure
        </label>
        <br>
        <label>
            <input type="checkbox" name="people[${status.index}].isNotGoing"/>
            Decline with Regret
        </label>
    </fieldset>
    </c:forEach>
    
    Special requests:<br />
    <form:textarea rows="5" style="padding-top: 10px; margin-bottom:20px; width:100%;" path="requests" />
    
    <input type="submit" value="Submit" />
</form:form>
</div>