<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link rel="stylesheet" href="stylesheet.css" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Tangerine" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="wrapper">
            <hr />
            <tiles:insertAttribute name="header" />
            <hr />
            <tiles:insertAttribute name="menu" />
            <hr />
            <tiles:insertAttribute name="body" />
            <hr />
            <tiles:insertAttribute name="footer" />
        </div>
    </body>
</html>