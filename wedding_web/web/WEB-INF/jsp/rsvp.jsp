<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leah Andrews and Steve Inkpen - Wedding 2014</title>
        <link rel="stylesheet" href="stylesheet.css" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet" type="text/css">
    </head>
    <body>
        
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
    </body>
</html>