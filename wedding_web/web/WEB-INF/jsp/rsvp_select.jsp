<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leah Andrews and Steve Inkpen - Wedding 2014</title>
        <link rel="stylesheet" href="stylesheet.css" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>RSVP</h1>
        <form:form action="rsvp_confirm.htm" method="POST" modelAttribute="invite">
            
            <c:forEach items="${invite.people}" var="person" varStatus="status">     
            <fieldset>
                <legend>${person.name}</legend>
                <input type="hidden" name="people[${status.index}].name" value="${person.name}" />
                <input type="checkbox" name="people[${status.index}].isGoing"/>Accept with Pleasure<br>
                <input type="checkbox" name="people[${status.index}].isNotGoing"/>Decline with Regret
            </fieldset>
            </c:forEach>
            <br />
            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>