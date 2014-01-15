<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="rsvp_box">
    <h1>RSVP</h1>

    <div class="error_message">${errorMessage}</div>

    <p>Please enter your code which can be found on your invite.</p>

    <br>

    <form action="/rsvp_select.htm" method="GET">
        Code: <input type="text" name="code" />
        <input type="submit" value="Submit" />
    </form>
    <br>
</div>
